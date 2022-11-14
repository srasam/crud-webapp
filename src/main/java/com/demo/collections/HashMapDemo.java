package com.demo.collections;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.javaee.bookstore.Book;
import com.javaee.bookstore.BookDao;

public class HashMapDemo {
	
	public HashMap<Integer, Books> listBooks(){
		
		HashMap<Integer,Books> map=new HashMap<Integer,Books>();
		 String sql = "SELECT * FROM book";
         
	        connect();
	         
	        Statement statement = jdbcConnection.createStatement();
	        ResultSet resultSet = statement.executeQuery(sql);
	         
	        while (resultSet.next()) {
	            int id = resultSet.getInt("book_id");
	            String title = resultSet.getString("title");
	            String author = resultSet.getString("author");
	            float price = resultSet.getFloat("price");
	             
	            Books book = new Books(id, title, author, price);
	            map.put(id, book);
	        }
		return map;
		
	}
	
	

}
