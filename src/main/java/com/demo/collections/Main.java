package com.demo.collections;

import java.sql.SQLException;

public class Main {

	public static void main(String[] args) throws SQLException {
		BooksDao books=new BooksDao("jdbc:mysql://localhost:3306/Bookstore","root","root");
		//books.connect();
		books.listBooks();
		
		

	}

}
