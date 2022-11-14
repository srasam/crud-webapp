package com.javaee.bookstore;

import java.sql.SQLException;

public class TestDBcontents{

	public static void main(String[] args) throws SQLException {
		BookDAO b=new BookDAO("jdbc:mysql://localhost:3306/Bookstore","root","root");
b.connect();
    b.listAllBooks();
	    
		

	}

}
