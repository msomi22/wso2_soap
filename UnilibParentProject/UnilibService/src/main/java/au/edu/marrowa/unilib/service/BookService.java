package au.edu.marrowa.unilib.service;

import au.edu.marrowa.unilib.bean.Book;
import au.edu.marrowa.unilib.bean.Borrowing;
import au.edu.marrowa.unilib.bean.Student;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONException;

import au.edu.marrowa.unilib.bean.Response;
import au.edu.marrowa.unilib.persistence.BookDAO;
import au.edu.marrowa.unilib.persistence.StudentDAO;
import au.edu.marrowa.unilib.service.bean.LibBook;
import au.edu.marrowa.unilib.service.bean.LibStudent;
import au.edu.marrowa.unilib.service.bean.SRequest;
import au.edu.marrowa.unilib.service.bean.StudentBook;


public class BookService {

	private static BookDAO bookDAO;
	private static StudentDAO studentDAO;

	private String[] statusArr = {"available", "on_loan",  "back_order"}; 
	private List<String> status = Arrays.asList(statusArr);

	private String[] catArr = {"is_borrowing", "success_requested",  "cleared"}; 
	private List<String> b_category = Arrays.asList(catArr); 

	static{
		bookDAO = BookDAO.getInstance();
		studentDAO = StudentDAO.getInstance();
	}

	/**
	 * 
	 * @param book
	 * @return
	 */
	public Response addBook(LibBook libook) {
		Response response = new Response();

		if(!status.contains(libook.getStatus())){
			response.setMessage("error");
			response.setDescription("Incorrect status! Try either of " + status.toString()); 
			return response;

		}else if(bookDAO.getBookByISBN(libook.getIsbn()) != null){
			response.setMessage("error");
			response.setDescription("A Book with ISBN " + libook.getIsbn() + " already exist!"); 
			return response;
		}else{

			Book book1 = new Book(); 
			book1.setTitle(libook.getTitle());
			book1.setAuthors(libook.getAuthors());
			book1.setPublisher(libook.getPublisher());
			book1.setPublished_date(libook.getPublished_date());  
			book1.setStatus(libook.getStatus());
			book1.setIsbn(libook.getIsbn()); 
			book1.setLast_updated(new Date(0)); 
			book1.setUuid(new Book().getUuid()); 


			if(bookDAO.putBook(book1)){
				response.setMessage("success");
				response.setDescription("Book was added successfully!"); 
				return response;
			}else{
				response.setMessage("error");
				response.setDescription("Book was not added!");
				return response;
			}
		}

	}
	/**
	 * 
	 * @param book
	 * @param isbn
	 * @return
	 */
	public Response updateBook(LibBook libook){
		Response response = new Response();

		if(bookDAO.getBookByISBN(libook.getIsbn()) == null){
			response.setMessage("error");
			response.setDescription("Book with ISBN " + libook.getIsbn() + " was not found, maybe you place an order!"); 
			return response;
		}else{

			Book book1 = bookDAO.getBookByISBN(libook.getIsbn());
			book1.setTitle(libook.getTitle());
			book1.setAuthors(libook.getAuthors());
			book1.setPublisher(libook.getPublisher());
			book1.setStatus(libook.getStatus());
			book1.setPublished_date(libook.getPublished_date()); 
			book1.setLast_updated(new Date(0)); 

			if(bookDAO.updateBook(book1)){ 
				response.setMessage("success");
				response.setDescription("Book was updated successfully!"); 
				return response;
			}else{
				response.setMessage("error");
				response.setDescription("Book was not updated!");
				return response;
			}
		}
	}
	/**
	 * 
	 * @param isbn
	 * @return
	 */
	public Response deleteBook(String isbn){
		Response response = new Response();

		if(bookDAO.deleteBook(isbn)){
			response.setMessage("success");
			response.setDescription("Book was deleted successfully!"); 
			return response;
		}else{
			response.setMessage("error");
			response.setDescription("Book was not deleted!");
			return response;
		}
	}
	/**
	 * 
	 * @param query
	 * @return
	 */
	public Object getBookByISBN(String isbn){
		Response response = new Response();
		if(bookDAO.getBookByISBN(isbn) == null){
			response.setMessage("error");
			response.setDescription("Book with ISBN " + isbn + " was not found, maybe you place an order!"); 
			return response;
		}else{
			return bookDAO.getBookByISBN(isbn);
		}
	}

	/**
	 * 
	 * @return
	 */
	public Object getBooks(){
		Response response = new Response();
		if(bookDAO.getBooks().isEmpty()){
			response.setMessage("error");
			response.setDescription("No book is available in the database!");
			return response;
		}else{
			return bookDAO.getBooks();
		}

	}

	//*********************************************************************** 






	/**
	 * 
	 * @param book
	 * @return
	 */
	public Response addStudent(LibStudent l_student){
		Response response = new Response();

		if(studentDAO.getStudent(l_student.getStudent_id()) != null){
			response.setMessage("error");
			response.setDescription("A student with Id " + l_student.getStudent_id() + " already exist!"); 
			return response;
		}else if(studentDAO.getStudent(l_student.getStudent_id(), l_student.getPin()) != null){ 
			response.setMessage("error");
			response.setDescription("Pin " + l_student.getPin() + " is in use by another student!"); 
			return response;
		}else{

			Student student = new Student();
			student.setUuid(new Student().getUuid()); 
			student.setFull_name(l_student.getFull_name());
			student.setPin(l_student.getPin());
			student.setStudent_id(l_student.getStudent_id()); 


			if(studentDAO.putStudent(student)){
				response.setMessage("success");
				response.setDescription("Student added successfully"); 
				return response;
			}else{
				response.setMessage("error");
				response.setDescription("An error occured while adding student!");
				return response;
			}
		}
	}


	/**
	 * 
	 * @param studentid
	 * @return
	 */
	public Object getStudent(String studentId){
		Response response = new Response();
		if(studentDAO.getStudent(studentId) == null){
			response.setMessage("error");
			response.setDescription("Student with Id " + studentId + " was not found!");
			return response;

		}else{
			return studentDAO.getStudent(studentId);
		}

	}

	/**
	 * 
	 * @param student_id
	 * @param category
	 * @return
	 */
	public Object getBookList(String student_id, String cat){
		Response response = new Response();

		if(studentDAO.getStudent(student_id) == null){
			response.setMessage("error");
			response.setDescription("Student with Id " + student_id + " was not found!");
			return response;

		}else if(!b_category.contains(cat)){
			response.setMessage("error");
			response.setDescription("Category " + cat +" not found!");
			return response;

		}else if(studentDAO.getBookList(studentDAO.getStudent(student_id).getUuid(), cat).isEmpty()){
			
			response.setMessage("error");
			response.setDescription("Student with Id " + student_id + " has no books under category " + cat +"!");
			return response;

		}else{

			List<StudentBook> studentBookList = new ArrayList<>();

			List<Borrowing> borrowingList = new ArrayList<>();
			borrowingList = studentDAO.getBookList(studentDAO.getStudent(student_id).getUuid(), cat);
			
			

			//TODO
			for(Borrowing borrow : borrowingList){
				
				StudentBook studentBook = new StudentBook();
				studentBook.setCategory(borrow.getCategory());
				studentBook.setStudent_id(borrow.getStudent_id()); 
				studentBook.setStudent_name(studentDAO.getStudent(student_id).getFull_name());  
				studentBook.setAdd_date(borrow.getAdd_date().toString());

				Book book = bookDAO.getBookById(borrow.getBook_id());
				studentBook.setIsbn(book.getIsbn());
				studentBook.setTitle(book.getTitle());
				studentBook.setAuthors(book.getAuthors());
				studentBook.setPublisher(book.getPublisher());
				studentBook.setPublished_date(book.getPublished_date()); 
				studentBook.setStatus(book.getStatus()); 

				studentBookList.add(studentBook);

			}
		
			
			return studentBookList; 
		}

	}











	//************************************************************************************** 

	/**
	 * 
	 * @param book
	 * @return
	 */
	public Response borrowBook(SRequest borrow){
		Response response = new Response();

		if(studentDAO.getStudent(borrow.getStudent_id(), borrow.getPin()) == null){ 
			response.setMessage("error");
			response.setDescription("Student not found/authentication failure!");
			return response;

		}else if(studentDAO.getStudentBook(studentDAO.getStudent(borrow.getStudent_id()).getUuid(), 
				bookDAO.getBookByISBN(borrow.getBook_isbn()).getUuid()) != null){ 
			response.setMessage("error");
			response.setDescription("The student has the book already!");
			return response;

		}else if(bookDAO.getBookByISBN(borrow.getBook_isbn(), "on_loan") != null){  
			response.setMessage("error");
			response.setDescription("This book already borrowed!"); 
			return response;

		}else if(bookDAO.getBookByISBN(borrow.getBook_isbn(), "back_order") != null){  
			response.setMessage("error");
			response.setDescription("This book is in 'back order' state!"); 
			return response;

		}else{

			if(studentDAO.borrowBook(studentDAO.getStudent(borrow.getStudent_id()).getUuid(), bookDAO.getBookByISBN(borrow.getBook_isbn()).getUuid())){

				//TODO
				Book book = bookDAO.getBookByISBN(borrow.getBook_isbn());
				book.setStatus("on_loan"); 
				bookDAO.updateBook(book);


				response.setMessage("success");
				response.setDescription("Book borrowed successfully!"); 
				return response;
			}else{
				response.setMessage("error");
				response.setDescription("An error occured, try again later!");
				return response;
			}

		}
	}
	/**
	 * 
	 * @param book
	 * @param isbn
	 * @return
	 */
	public Response returnBook(SRequest borrow){
		Response response = new Response();

		if(studentDAO.getStudent(borrow.getStudent_id(), borrow.getPin()) == null){ 
			response.setMessage("error");
			response.setDescription("Student not found/authentication failure!");
			return response;

		}else if(studentDAO.getStudentBook(studentDAO.getStudent(borrow.getStudent_id()).getUuid(), bookDAO.getBookByISBN(borrow.getBook_isbn()).getUuid()) == null){
			response.setMessage("error");
			response.setDescription("No book borrowed by the student!");
			return response;

		}else{

			if(studentDAO.returnBook(studentDAO.getStudent(borrow.getStudent_id()).getUuid(), bookDAO.getBookByISBN(borrow.getBook_isbn()).getUuid())){

				//TODO
				Book book = bookDAO.getBookByISBN(borrow.getBook_isbn());
				book.setStatus("available"); 
				bookDAO.updateBook(book);

				response.setMessage("success");
				response.setDescription("Book returned successfully!"); 
				return response;
			}else{
				response.setMessage("error");
				response.setDescription("An error occured, try again later!");
				return response;
			}

		}
	}
	/**
	 * 
	 * @param studentid
	 * @param isbn
	 * @return
	 * @throws JSONException 
	 */
	public Response orderBook(SRequest borrow){
		Response response = new Response();
		if(studentDAO.getStudent(borrow.getStudent_id(), borrow.getPin()) == null){ 
			response.setMessage("error");
			response.setDescription("Student not found/authentication failure!");
			return response;

		}else if(bookDAO.getBookByISBN(borrow.getBook_isbn()) != null){  
			response.setMessage("error");
			response.setDescription("This book is already available in the library!");
			return response;

		}else{

			String google_response = "";
			String isbn = borrow.getBook_isbn();

			String api_key = "AIzaSyBftQydwYJVQWnMErWE7qIJu3avs_oTLWQ";//Peter's Google Book api key 
			String isbn_url = "https://www.googleapis.com/books/v1/volumes?q=isbn:"+isbn+"&key="+api_key; 

			google_response = ApacheHttpRestClient.googleBooksApiCall(google_response, isbn_url);

			Object obj = new Object();
			try {
				obj = ApacheHttpRestClient.processResponse(google_response, isbn);
			} catch (JSONException e) {
				e.printStackTrace();
			}



			if(obj instanceof GoogleResponse){


				GoogleResponse gr = new GoogleResponse();
				gr = (GoogleResponse)obj;

				if(Double.parseDouble(gr.getAverageRating()) > 0 
						&& !gr.getSaleability().equalsIgnoreCase("NOT_FOR_SALE")){ 

					String studentId = "";
					studentId = studentDAO.getStudent(borrow.getStudent_id()).getUuid();


					//TODO
					Book book = new Book();
					book.setStatus("back_order"); 
					book.setTitle(gr.getTitle());
					book.setAuthors(gr.getAuthors());
					book.setPublisher(gr.getPublisher()); 
					book.setPublished_date(gr.getPublishedDate()); 
					book.setIsbn(borrow.getBook_isbn()); 
					book.setLast_updated(new Date(0)); 
					book.setUuid(new Book().getUuid()); 

					if(bookDAO.putBook(book)){
						
						studentDAO.orderBook(studentId, book.getUuid()); 

						response.setMessage("success");
						response.setDescription("Book ordered successfully!"); 
						return response;


					}else{
						response.setMessage("error");
						response.setDescription("An error occured, try again later!");
						return response;
					}

				}else{

					response.setMessage("error");
					response.setDescription("Book AverageRating is " + gr.getAverageRating() + 
							", Saleability is " + gr.getSaleability() + ", Country is " + gr.getCountry() + ", thus not allowed!");
					return response;


				}
			}else{

				response = (Response)obj;
				//response.setMessage("error");
				//response.setDescription("The Book ISBN not validated by Google Books!");
				return response;
			}
		}
	}


	/**
	 * 
	 * @param borrow
	 * @return
	 * @throws JSONException 
	 */
	public boolean validGoogleBook(SRequest borrow) {  
		String response = "";
		String isbn = "9780385474542";//Chinua Achebe , Things Fall Apart 

		isbn = borrow.getBook_isbn();

		String api_key = "AIzaSyBftQydwYJVQWnMErWE7qIJu3avs_oTLWQ";//Peter's Google Book api key 
		String isbn_url = "https://www.googleapis.com/books/v1/volumes?q=isbn:"+isbn+"&key="+api_key; 

		response = ApacheHttpRestClient.googleBooksApiCall(response, isbn_url);

		Object obj = new Object();
		try {
			obj = ApacheHttpRestClient.processResponse(response, isbn);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if(obj instanceof Response){
			return false;

		}else if(obj instanceof GoogleResponse){
			return true;

		}else{
			return false;
		}

	}


}