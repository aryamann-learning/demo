drop table books;
    
CREATE TABLE books (
    isbn LONG NOT NULL,
    book_name VARCHAR(50) NOT NULL,
    author_name VARCHAR(50) NOT NULL,
    book_type VARCHAR(50) NOT NULL,
    release_date TIMESTAMP
);