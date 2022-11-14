package com.demo.collections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;

import com.javaee.bookstore.Book;

public class BooksDao {
	private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;
   
     
    public BooksDao(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }
     
	/*
	 * protected void connect() throws SQLException { if (jdbcConnection == null ||
	 * jdbcConnection.isClosed()) { try { Class.forName("com.mysql.jdbc.Driver"); }
	 * catch (ClassNotFoundException e) { throw new SQLException(e); }
	 * jdbcConnection = DriverManager.getConnection( jdbcURL, jdbcUsername,
	 * jdbcPassword); System.out.println("Connection Sucessful!"); } }
	 * 
	 * protected void disconnect() throws SQLException { if (jdbcConnection != null
	 * && !jdbcConnection.isClosed()) { jdbcConnection.close(); } }
	 */
    public void connect() throws SQLException {
    	System.out.println("BookDAO->connect(): jdbcConnection 1: " + this.jdbcConnection);
		if (jdbcConnection == null || jdbcConnection.isClosed()) {
			try {
			
				Class.forName("com.mysql.jdbc.Driver");
		    	System.out.println("BookDAO->connect(): jdbcConnection 2: " + this.jdbcConnection);
		    	System.out.println("BookDAO->connect(): jdbcURL: " + this.jdbcURL);
		    	System.out.println("BookDAO->connect(): jdbcUsername: " + this.jdbcUsername);
		    	System.out.println("BookDAO->connect(): jdbcPassword: " + this.jdbcPassword);
				jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		    	System.out.println("BookDAO->connect(): jdbcConnection 3: " + this.jdbcConnection);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	protected void disconnect() throws SQLException {
		if (jdbcConnection != null && !jdbcConnection.isClosed()) {
			jdbcConnection.close();
		}
	}
    public boolean insertBook(Book book) throws SQLException {
        String sql = "INSERT INTO book (title, author, price) VALUES (?, ?, ?)";
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, book.getTitle());
        statement.setString(2, book.getAuthor());
        statement.setFloat(3, book.getPrice());
         
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }
     
    /*public List<Books> listAllBooks() throws SQLException {
        List<Books> listBook = new ArrayList<>();
        
         
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
            listBook.add(book);
            
           //System.out.println(book.getId()+" "+book.getAuthor()+" "+book.getTitle()+" "+book.getAuthor()+" "+book.getPrice());
           // System.out.println(book);
           
        }
        //Iterator
       /* Iterator<Books> iterator=listBook.iterator();
        System.out.println();
        while(iterator.hasNext()) {
        	System.out.println(iterator.next());
        }*/
        
        //ListIterator
        /*ListIterator<Books> listiterator=listBook.listIterator();
        
        while(listiterator.hasNext()) {
        	System.out.println(listiterator.next());
        }
        */
        
        //forEach- Java 8
//        listBook.forEach(System.out::println);
        
        //Stream.forEach()
        //listBook.stream().forEach((c) -> System.out.println(c));
        
        
        //System.out.println(listBook.size());
//        for(int i=0;i<listBook.size();i++) {
//        	System.out.println(listBook.toString());
//        }
        //System.out.println(book);
         
//        resultSet.close();
//        statement.close();
//         
//        disconnect();
//         
//        return listBook;*/
    	
    	/*LinkedList<Books> listBook = new LinkedList<Books>();
        
        
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
            listBook.add(book);
            
         
           
        }
        listBook.forEach(System.out::println);
       
       
        resultSet.close();
        statement.close();
         
        disconnect();
         
        return listBook;*/
/*LinkedList<Books> listBook = new LinkedList<Books>();
        
        
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
            listBook.add(book);
            
         
           
        }
        listBook.forEach(System.out::println);
       
       
        resultSet.close();
        statement.close();
         
        disconnect();
         
        return listBook;
    }*/
    	public HashMap<Integer, Books> listBooks() throws SQLException{
    		
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
    	        //display hashmap values using for
    	        /*for(Integer i:map.keySet()) {
    	        	Books bk=map.get(i);
    	        	System.out.println(bk.toString());
    	        }*/
    	        
    	        //lambda expressions
    	        //map.forEach((key, value)->System.out.println(value));
    	        
    	        // Iterator
    	        Iterator<Entry<Integer,Books>> it=map.entrySet().iterator();
    	        while(it.hasNext()) {
    	        	Map.Entry<Integer, Books> set=(Map.Entry<Integer, Books>) it.next();
    	        	System.out.println(set.getKey()+" = "+set.getValue());
    	        }
    	        resultSet.close();
    	        statement.close();
    	         
    	        //disconnect();
    		return map;
    		
    	}
    
     
    public boolean deleteBook(Book book) throws SQLException {
        String sql = "DELETE FROM book where book_id = ?";
         
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, book.getId());
         
        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;     
    }
     
    public boolean updateBook(Book book) throws SQLException {
        String sql = "UPDATE book SET title = ?, author = ?, price = ?";
        sql += " WHERE book_id = ?";
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, book.getTitle());
        statement.setString(2, book.getAuthor());
        statement.setFloat(3, book.getPrice());
        statement.setInt(4, book.getId());
         
        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;     
    }
     
    public Book getBook(int id) throws SQLException {
        Book book = null;
        String sql = "SELECT * FROM book WHERE book_id = ?";
         
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);
         
        ResultSet resultSet = statement.executeQuery();
         
        if (resultSet.next()) {
            String title = resultSet.getString("title");
            String author = resultSet.getString("author");
            float price = resultSet.getFloat("price");
             
            book = new Book(id, title, author, price);
        }
         
        resultSet.close();
        statement.close();
         
        return book;
    }
}
