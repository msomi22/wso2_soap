package au.edu.marrowa.unilib.service.bean;

public class SRequest {
	
	private String book_isbn;
	private String student_id;
	private String pin;
	
	public SRequest(){
		book_isbn = "";
		student_id = "";
		pin = "";
	}


	/**
	 * @return the book_isbn
	 */
	public String getBook_isbn() {
		return book_isbn;
	}


	/**
	 * @param book_isbn the book_isbn to set
	 */
	public void setBook_isbn(String book_isbn) {
		this.book_isbn = book_isbn;
	}


	public String getStudent_id() {
		return student_id;
	}

	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	@Override
	public String toString() {
		return "SRequest [book_isbn=" + book_isbn + ", student_id=" + student_id
				+ ", pin=" + pin + "]";
	}


}
