package au.edu.marrowa.unilib.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.BeanProcessor;
import org.apache.log4j.Logger;

import au.edu.marrowa.unilib.bean.Borrowing;
import au.edu.marrowa.unilib.bean.Student;

public class StudentDAO extends DbConnection{

	private static StudentDAO studentDAO;
	private Logger logger = Logger.getLogger(this.getClass());
	private BeanProcessor beanProcessor = new BeanProcessor();

	/**
	 * 
	 * @return
	 */
	public static StudentDAO getInstance(){
		if(studentDAO == null){
			studentDAO = new StudentDAO();		
		}
		return studentDAO;
	}

	public StudentDAO() {
		super();
	}

	/**
	 * 
	 * @param student_id
	 * @param pin
	 * @return
	 */
	public Student getStudent(String student_id, String pin){
		Student student = null;
		ResultSet rset = null;
		try(
				Connection conn = getJdbcConnection(); 
				PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Student WHERE student_id =? AND pin =?;");       
				){
			pstmt.setString(1, student_id);
			pstmt.setString(2, pin);
			rset = pstmt.executeQuery();
			while(rset.next()){
				student  = beanProcessor.toBean(rset, Student.class);
			}
		}catch(SQLException e){
			logger.error("SQL Exception when getting Student with student_id " + student_id + " and pin " + pin);
			logger.error(e.getMessage()); 
			System.out.println(e.getMessage());
		}
		return student; 
	}
	
	/**
	 * 
	 * @param student_id
	 * @return
	 */
	public Student getStudent(String student_id){
		Student student = null;
		ResultSet rset = null;
		try(
				Connection conn = getJdbcConnection(); 
				PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Student WHERE student_id =?;");       
				){
			pstmt.setString(1, student_id);
			rset = pstmt.executeQuery();
			while(rset.next()){
				student  = beanProcessor.toBean(rset, Student.class);
			}
		}catch(SQLException e){
			logger.error("SQL Exception when getting Student with student_id " + student_id);
			logger.error(e.getMessage()); 
			System.out.println(e.getMessage());
		}
		return student; 
	}
	
	/**
	 * 
	 * @param student
	 * @return
	 */
	public boolean putStudent(Student student){
		boolean success = true;
		try(   Connection conn = getJdbcConnection(); 
				PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Student" 
						+"(uuid,full_name,student_id,pin) VALUES (?,?,?,?);"); 
				){
			pstmt.setString(1, new Student().getUuid()); 
			pstmt.setString(2, student.getFull_name()); 
			pstmt.setString(3, student.getStudent_id());
			pstmt.setString(4, student.getPin());
			pstmt.executeUpdate();
		}catch(SQLException e){
			logger.error("SQL Exception trying to put Student " + student);
			logger.error(e.getMessage()); 
			System.out.println(e.getMessage());
			success = false;
		}
		return success;
	}


	
	
	//**********************************************************************************************

	/**
	 * 
	 * @param student_id
	 * @param book_id
	 * @return
	 */
	public Borrowing getStudentBook(String student_id, String book_id){
		Borrowing borrowing = null;
		ResultSet rset = null;
		try(
				Connection conn = getJdbcConnection(); 
				PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Borrowing WHERE student_id =? AND book_id =?;");       
				){
			pstmt.setString(1, student_id);
			pstmt.setString(2, book_id);
			rset = pstmt.executeQuery();
			while(rset.next()){
				borrowing  = beanProcessor.toBean(rset,Borrowing.class);
			}
		}catch(SQLException e){
			logger.error("SQL Exception when getting Borrowing with student_id " + student_id + " and book_id " +book_id);
			logger.error(e.getMessage()); 
			System.out.println(e.getMessage());
		}
		return borrowing; 
	}
	
	/**
	 * 
	 * @param book_id
	 * @return
	 */
	public Borrowing getStudentBook(String book_id){
		Borrowing borrowing = null;
		ResultSet rset = null;
		try(
				Connection conn = getJdbcConnection(); 
				PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Borrowing WHERE book_id =?;");       
				){
			pstmt.setString(1, book_id);
			rset = pstmt.executeQuery();
			while(rset.next()){
				borrowing  = beanProcessor.toBean(rset,Borrowing.class);
			}
		}catch(SQLException e){
			logger.error("SQL Exception when getting Borrowing with  book_id " +book_id);
			logger.error(e.getMessage()); 
			System.out.println(e.getMessage());
		}
		return borrowing; 
	}
	/**
	 * 
	 * @param student_id
	 * @param book_id
	 * @return
	 */
	public boolean borrowBook(String student_id, String book_id){
		boolean success = true;
		try(   Connection conn = getJdbcConnection(); 
				PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Borrowing (uuid,category,book_id,student_id) VALUES (?,?,?,?);"); 
				){
			pstmt.setString(1, new Borrowing().getUuid()); 
			pstmt.setString(2, "is_borrowing"); 
			pstmt.setString(3, book_id);
			pstmt.setString(4, student_id);
			pstmt.executeUpdate();
		}catch(SQLException e){
			logger.error("SQL Exception trying to order book for student_id " + student_id + " and book_id " + book_id);
			logger.error(e.getMessage()); 
			System.out.println(e.getMessage());
			success = false;
		}
		return success;
	}
	/**
	 * 
	 * @param student_id
	 * @param book_id
	 * @return
	 */
	public boolean returnBook(String student_id, String book_id){
		boolean success = true;
		try(   Connection conn = getJdbcConnection(); 
				PreparedStatement pstmt = conn.prepareStatement("UPDATE Borrowing SET category =? WHERE student_id =? AND book_id =?;"); 
				){
			pstmt.setString(1, "cleared"); 
			pstmt.setString(2, student_id);
			pstmt.setString(3, book_id);
			pstmt.executeUpdate();
		}catch(SQLException e){
			logger.error("SQL Exception trying to clear book for student_id " + student_id + " and book_id " + book_id);
			logger.error(e.getMessage()); 
			System.out.println(e.getMessage());
			success = false;
		}
		return success;
	}
	/**
	 * 
	 * @param student_id
	 * @param book_id
	 * @return
	 */
	public boolean orderBook(String student_id, String book_id){
		boolean success = true;
		try(   Connection conn = getJdbcConnection(); 
				PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Borrowing (uuid,category,book_id,student_id) VALUES (?,?,?,?);"); 
				){
			pstmt.setString(1, new Borrowing().getUuid()); 
			pstmt.setString(2, "success_requested"); 
			pstmt.setString(3, book_id);
			pstmt.setString(4, student_id);
			pstmt.executeUpdate();
		}catch(SQLException e){
			logger.error("SQL Exception trying to order book for student_id " + student_id + " and book_id " + book_id);
			logger.error(e.getMessage()); 
			System.out.println(e.getMessage());
			success = false;
		}
		return success;
	}
	
	
	/**
	 * 
	 * @param student_id
	 * @param category
	 * @return
	 */
	public List<Borrowing> getBookList(String student_id, String category) {
		List<Borrowing> borrowingList = new ArrayList<>();
		try(
				Connection conn = getJdbcConnection(); 
				PreparedStatement psmt= conn.prepareStatement("SELECT * FROM Borrowing WHERE "
						+ "student_id = ? AND category = ?;");
				) {
			psmt.setString(1, student_id);
			psmt.setString(2, category);
			try(ResultSet rset = psmt.executeQuery();){
				borrowingList = beanProcessor.toBeanList(rset, Borrowing.class);
			}
		} catch (SQLException e) {
			logger.error("SQLException when trying Borrowing List for student_id " + student_id + " and category " + category);
			logger.error(e.getMessage()); 
			System.out.println(e.getMessage());
		}
		return borrowingList;
	}
	
	
	
	
}
