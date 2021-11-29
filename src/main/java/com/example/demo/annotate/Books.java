package com.example.demo.annotate;

//Sample for multi value annotation:
//Custom annotation declaration
@interface books_data
{
 // Multiple variable declarations
	String book_name() default "Effective Java";
	 
    // Declaring the default values
    int book_price() default 30;
    String author() default "Anji";
}
public class Books {
	 // Main driver method
    public static void main(String[] args)
    {
        // Print statement
        System.out.println(
            "example of multi value Annotation.");
    }

}
