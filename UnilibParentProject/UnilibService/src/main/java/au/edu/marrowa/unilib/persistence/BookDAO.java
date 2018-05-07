package au.edu.marrowa.unilib.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.BeanProcessor;
import org.apache.log4j.Logger;

import au.edu.marrowa.unilib.bean.Book;

public class BookDAO extends DbConnection{

	private static BookDAO bookDAO;
	private Logger logger = Logger.getLogger(this.getClass());
	private BeanProcessor beanProcessor = new BeanProcessor();

	/**
	 * 
	 * @return
	 */
	public static BookDAO getInstance(){

		if(bookDAO == null){
			bookDAO = new BookDAO();		
		}
		return bookDAO;
	}


	public BookDAO() {
		super();
	}
	
	
	/**
	 * @return
	 */
	public List<Book> getBooks(){
		List<Book> books = null;
		try(   
				Connection conn = getJdbcConnection(); 
				PreparedStatement  pstmt = conn.prepareStatement("SELECT * FROM Book ;");   
				ResultSet rset = pstmt.executeQuery();
				) {

			books = beanProcessor.toBeanList(rset, Book.class);

		} catch(SQLException e){
			logger.error("SQL Exception when getting all Books");
			logger.error(e.getMessage()); 
			System.out.println(e.getMessage());
		}

		return books;
	}
	/**
	 * 
	 * @param isbn
	 * @return
	 */
	public Book getBookByISBN(String isbn){
		Book book = null;
		ResultSet rset = null;
		try(
				Connection conn = getJdbcConnection(); 
				PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Book WHERE isbn =?;");       
				){
			pstmt.setString(1, isbn);
			rset = pstmt.executeQuery();
			while(rset.next()){
				book  = beanProcessor.toBean(rset,Book.class);
			}
		}catch(SQLException e){
			logger.error("SQL Exception when getting Book with isbn " + isbn);
			logger.error(e.getMessage()); 
			System.out.println(e.getMessage());
		}
		return book; 
	}
	
	
	//String status;// available, on_loan,  back_order  
	/**
	 * 
	 * @param isbn
	 * @param status
	 * @return
	 */
	public Book getBookByISBN(String isbn, String status){
		Book book = null;
		ResultSet rset = null;
		try(
				Connection conn = getJdbcConnection(); 
				PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Book WHERE isbn =? AND status =?;");       
				){
			pstmt.setString(1, isbn);
			pstmt.setString(2, status);
			rset = pstmt.executeQuery();
			while(rset.next()){
				book  = beanProcessor.toBean(rset,Book.class);
			}
		}catch(SQLException e){
			logger.error("SQL Exception when getting Book with isbn " + isbn);
			logger.error(e.getMessage()); 
			System.out.println(e.getMessage());
		}
		return book; 
	}
	
	/**
	 * 
	 * @param uuid
	 * @return
	 */
	public Book getBookById(String uuid){
		Book book = null;
		ResultSet rset = null;
		try(
				Connection conn = getJdbcConnection(); 
				PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Book WHERE uuid =?;");       
				){
			pstmt.setString(1, uuid);
			rset = pstmt.executeQuery();
			while(rset.next()){
				book  = beanProcessor.toBean(rset,Book.class);
			}
		}catch(SQLException e){
			logger.error("SQL Exception when getting Book with uuid " + uuid);
			logger.error(e.getMessage()); 
			System.out.println(e.getMessage());
		}
		return book; 
	}
	
	
	
	/**
	 * 
	 * @param book
	 * @return
	 */
	public boolean putBook(Book book){
		boolean success = true;
		try(   Connection conn = getJdbcConnection(); 
				PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Book" 
						+"(id,uuid,title,authors,isbn,publisher,published_date,status,last_updated) VALUES (?,?,?,?,?,?,?,?,?);"); 
				){
			pstmt.setInt(1, book.getId()); 
			pstmt.setString(2, book.getUuid());
			pstmt.setString(3, book.getTitle());
			pstmt.setString(4, book.getAuthors());
			pstmt.setString(5, book.getIsbn());
			pstmt.setString(6, book.getPublisher());
			pstmt.setString(7, book.getPublished_date());
			pstmt.setString(8, book.getStatus());
			pstmt.setTimestamp(9, new Timestamp(new Date().getTime()));   
			pstmt.executeUpdate();
		}catch(SQLException e){
			logger.error("SQL Exception trying to put book " + book);
			logger.error(e.getMessage()); 
			System.out.println(e.getMessage());
			success = false;
		}
		return success;
	}
	/**
	 * 
	 * @param book
	 * @return
	 */
	public boolean updateBook(Book book){
		boolean success = true;
		try (  Connection conn = getJdbcConnection(); 
				PreparedStatement pstmt = conn.prepareStatement("UPDATE Book SET title =?, authors = ?, isbn =? ,"
						+ "publisher =?, published_date =? ,status =? ,last_updated =? WHERE uuid = ?;");
				) {           			 	            
			pstmt.setString(1, book.getTitle());
			pstmt.setString(2, book.getAuthors());
			pstmt.setString(3, book.getIsbn());
			pstmt.setString(4, book.getPublisher());
			pstmt.setString(5, book.getPublished_date());
			pstmt.setString(6, book.getStatus());
			pstmt.setTimestamp(7, new Timestamp(new Date().getTime()));   
			pstmt.setString(8, book.getUuid());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			logger.error("SQL Exception when updating book " + book);
			logger.error(e.getMessage()); 
			System.out.println(e.getMessage());
			success = false;
		} 
		return success;
	}
	/**
	 * 
	 * @param isbn
	 * @return
	 */
	public boolean deleteBook(String isbn){
		boolean success = true; 
		try(
				Connection conn = getJdbcConnection(); 
				PreparedStatement pstmt = conn.prepareStatement("DELETE FROM Book WHERE isbn =?;");       
				){
			pstmt.setString(1, isbn);
			pstmt.executeUpdate();

		}catch(SQLException e){
			logger.error("SQL Exception when deletting book  with isbn" + isbn);
			logger.error(e.getMessage()); 
			System.out.println(e.getMessage());
			success = false;
		}
		return success;
	}
	

}
