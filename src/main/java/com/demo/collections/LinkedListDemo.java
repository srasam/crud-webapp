package com.demo.collections;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LinkedListDemo {
	private Connection jdbcConnection;
	 public List<Books> listAllBooks() throws SQLException {
		 LinkedList<Books> listBook = new LinkedList<Books>();
		 //BooksDao bookDao=new BooksDao();
	        
	        
	        String sql = "SELECT * FROM book";
	        //bookDao.connect();
	         
	        
	         
	        Statement statement = jdbcConnection.createStatement();
	        ResultSet resultSet = statement.executeQuery(sql);
	         
	        while (resultSet.next()) {
	            int id = resultSet.getInt("book_id");
	            String title = resultSet.getString("title");
	            String author = resultSet.getString("author");
	            float price = resultSet.getFloat("price");
	             
	            Books book = new Books(id, title, author, price);
	            listBook.add(book);
	            
	         
	           
	        }
	        listBook.forEach(System.out::println);
	       
	       
	        resultSet.close();
	        statement.close();
	         
	      //bookDao.disconnect();
	         
	        return listBook;
	    }
	 
       

}
