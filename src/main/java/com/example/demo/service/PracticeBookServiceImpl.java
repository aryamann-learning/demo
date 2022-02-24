package com.example.demo.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.constants.DemoConstants;
import com.example.demo.dto.Book;
import com.example.demo.entity.BookEntity;
import com.example.demo.repository.PracticeBookRepository;
import com.example.demo.utils.DateUtils;

@Service
public class PracticeBookServiceImpl implements PracticeBookService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	DateFormat dateFormat = new SimpleDateFormat(DemoConstants.SRC_DATE_FORMATTER);

	@Autowired
	PracticeBookRepository practiceBookRepository;

	@Override
	public List<Book> getBooks(String source) throws IOException, ParseException {
		List<Book> books = null;
		switch (source) {
		case "excel":
			// call excel method
			books = getBooksFromExcel();
			break;
		case "text":
			// call text method
			books = getBooksFromText();
			break;
		case "db":
			// call db method
			books = getBooksFromDb();
			break;
		}

		return books;
	}

	private List<Book> getBooksFromExcel() throws IOException, ParseException {
		List<Book> books = new ArrayList<>();
		FileInputStream fis = new FileInputStream(new File(DemoConstants.BOOK_EXCEL_PATH));
		// creating workbook instance that refers to .xlsx file
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		// creating a Sheet object to retrieve the object
		XSSFSheet sheet = wb.getSheetAt(0);
		Iterator<Row> iterator = sheet.iterator();
		iterator.next();
		while (iterator.hasNext()) {
			Row row = iterator.next();
			long isbn = (long) row.getCell(0).getNumericCellValue();
			logger.info("isbn=" + isbn);
			String bookName = row.getCell(1).getStringCellValue();
			String authorName = row.getCell(2).getStringCellValue();
			String bookType = row.getCell(3).getStringCellValue();
			Date releaseDate = DateUtils.toDate(row.getCell(4).getStringCellValue(), DemoConstants.SRC_DATE_FORMATTER,
					DemoConstants.DES_DATE_FORMATTER);
			Book book = new Book();
			book.setIsbn(isbn);
			book.setBookName(bookName);
			book.setAuthorName(authorName);
			book.setBookType(bookType);
			book.setReleaseDate(releaseDate);
			books.add(book);
		}
		Collections.sort(books, new SortByauthorName());
		wb.close();
		fis.close();
		System.out.println(ZoneId.systemDefault());
		return books;

	}

	private List<Book> getBooksFromText() throws FileNotFoundException, ParseException {
		List<Book> books = new ArrayList<Book>();
		BufferedReader br = null;
		try {
			File file = new File(DemoConstants.BOOK_TEXT_PATH);
			FileReader fileReader = new FileReader(file);
			br = new BufferedReader(fileReader);
			String line = br.readLine();
			line = br.readLine();
			while (line != null) {
				String[] book = line.split(File.separator + DemoConstants.PIPE_SEPERATOR);
				Book bookObj = new Book();
				bookObj.setIsbn(Integer.parseInt(book[0]));
				bookObj.setBookName(book[1]);
				bookObj.setAuthorName(book[2]);
				bookObj.setBookType(book[3]);
				bookObj.setReleaseDate(
						DateUtils.toDate(book[4], DemoConstants.SRC_DATE_FORMATTER, DemoConstants.DES_DATE_FORMATTER));
				books.add(bookObj);
				line = br.readLine();
			}
			Collections.sort(books, new SortByauthorName());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return books;
	}

	private List<Book> getBooksFromDb() {

		List<BookEntity> list = practiceBookRepository.findAll(Sort.by(Direction.ASC, "authorName"));
		List<Book> books = new ArrayList<>();
		for (BookEntity bookEntity : list) {
			books.add(bookEntity.toDto());
		}
		return books;
	}

	@Override
	public void updateBook(String source, Long isbn, Book book) {
		switch (source) {
		case "excel":
			updateBookInExcel(isbn, book);
			break;
		case "text":
			updateBookInText(isbn, book);
			break;
		case "db":
			updateBookInDb(isbn, book);
			break;

		default:
			break;
		}
	}

	private void updateBookInText(Long isbn, Book book) {

	}

	private void updateBookInExcel(Long isbn, Book book) {

	}

	private void updateBookInDb(Long isbn, Book book) {
		if (!isbn.equals(book.getIsbn())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Pathvariable isbn doesn't match requestbody isbn");
		} else {
			Optional<BookEntity> bookEntity = practiceBookRepository.findById(isbn);
			if (bookEntity.isPresent()) {
				BookEntity enBook = new BookEntity();
				enBook.setIsbn(book.getIsbn());
				enBook.setAuthorName(book.getAuthorName());
				enBook.setBookName(book.getBookName());
				enBook.setBookType(book.getBookType());
				enBook.setReleaseDate(book.getReleaseDate());
				practiceBookRepository.save(enBook);
			} else {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "isbn does not exist");
			}
		}
	}

	@Override
	public void createBook(Book book, String source) throws IOException {
		switch (source) {
		case "db":
			saveToDb(book);
			break;
		case "text":
			saveToText(book);
			break;
		case "excel":
			saveToExcel(book);
			break;
		default:
			break;
		}
	}

	private void saveToExcel(Book book) throws IOException {
		File file = new File(DemoConstants.BOOK_EXCEL_PATH);
		try (FileInputStream fis = new FileInputStream(file);
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				FileOutputStream fos = new FileOutputStream(file);) {
			XSSFSheet sheet = wb.getSheetAt(0);
			int lastRow = sheet.getLastRowNum();
			Row row = sheet.createRow(++lastRow);
			addCellValue(row, book.getIsbn(), 0);
			addCellValue(row, book.getBookName(), 1);
			addCellValue(row, book.getAuthorName(), 2);
			addCellValue(row, book.getBookType(), 3);
			addCellValue(row, book.getReleaseDate(), 4);
			wb.write(fos);
		}
	}

	private void addCellValue(Row row, Object obj, int index) {
		if (obj instanceof String)
			row.createCell(index).setCellValue((String) obj);

		else if (obj instanceof Integer)
			row.createCell(index).setCellValue((Integer) obj);

		else if (obj instanceof Long)
			row.createCell(index).setCellValue((Long) obj);

		else if (obj instanceof Date)
			row.createCell(index).setCellValue(dateFormat.format(obj));
	}

	private void saveToText(Book book) throws IOException {
		File file = new File(DemoConstants.BOOK_TEXT_PATH);

		try (FileWriter fileWriter = new FileWriter(file, true); BufferedWriter bw = new BufferedWriter(fileWriter);) {
			DateFormat dateFormat = new SimpleDateFormat(DemoConstants.SRC_DATE_FORMATTER);
			bw.write((String.valueOf(book.getIsbn())));
			bw.write(DemoConstants.PIPE_SEPERATOR);
			bw.write(book.getBookName());
			bw.write(DemoConstants.PIPE_SEPERATOR);
			bw.write(book.getAuthorName());
			bw.write(DemoConstants.PIPE_SEPERATOR);
			bw.write(book.getBookType());
			bw.write(DemoConstants.PIPE_SEPERATOR);
			bw.write(dateFormat.format(book.getReleaseDate()));
		} catch (NullPointerException ne) {
			ne.printStackTrace();
		}
	}

	private void saveToDb(Book book) {
		BookEntity en = new BookEntity();
		en.setAuthorName(book.getAuthorName());
		en.setBookName(book.getBookName());
		en.setBookType(book.getBookType());
		en.setIsbn(book.getIsbn());
		en.setReleaseDate(book.getReleaseDate());
		practiceBookRepository.save(en);
	}

	@Override
	public void deleteBook(String source, Long isbn) {
		switch (source) {
		case "excel":
			deleteFromExcel(isbn);
			break;
		case "text":
			deleteFromText(isbn);
			break;
		case "db":
			deleteFromDb(isbn);
			break;
		default:
			break;
		}
	}

	private void deleteFromExcel(Long isbn) {

		File file = new File("D:\\Data\\tempBooks.xlsx");
		try (FileInputStream fis = new FileInputStream(file); XSSFWorkbook wb = new XSSFWorkbook(fis);) {

			XSSFSheet sheet = wb.getSheetAt(0);
			XSSFRow rerow = sheet.getRow(2);
			sheet.removeRow(rerow);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void deleteFromText(Long isbn) {

		String file = DemoConstants.BOOK_TEXT_PATH;

		try (FileReader fileReader = new FileReader(file);
				FileWriter fileWriter = new FileWriter(file, false);
				BufferedReader br = new BufferedReader(fileReader);
				BufferedWriter bw = new BufferedWriter(fileWriter)) {
			String line = null;
			while ((line = br.readLine()) != null) {
				if (line.contains(String.valueOf(isbn))) {
					line.replaceAll(line, "");
				}
				PrintWriter writer = new PrintWriter(file);
				writer.append(line);
				writer.flush();
				writer.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void deleteFromDb(Long isbn) {
		practiceBookRepository.deleteById(isbn);
	}

}
