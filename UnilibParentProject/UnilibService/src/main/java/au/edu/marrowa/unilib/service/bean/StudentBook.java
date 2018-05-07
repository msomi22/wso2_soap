/**
 * 
 */
package au.edu.marrowa.unilib.service.bean;

/**
 * @author peter
 *
 */
public class StudentBook {
	
	private String category;
	private String student_id;
	private String student_name;
	private String add_date;
	
	
	private String title;
	private String authors;
	private String isbn;
	private String publisher;
	private String published_date;
	private String status;


	/**
	 * 
	 */
	public StudentBook() {
		category = "";
		student_id = "";
		student_name = "";
		add_date = "";
		
		title = "";
		authors = "";
		isbn = "";
		publisher = "";
		published_date = "";
		status = "";
	}


	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}


	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}


	/**
	 * @return the student_id
	 */
	public String getStudent_id() {
		return student_id;
	}


	/**
	 * @param student_id the student_id to set
	 */
	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}


	/**
	 * @return the student_name
	 */
	public String getStudent_name() {
		return student_name;
	}


	/**
	 * @param student_name the student_name to set
	 */
	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}


	/**
	 * @return the add_date
	 */
	public String getAdd_date() {
		return add_date;
	}


	/**
	 * @param add_date the add_date to set
	 */
	public void setAdd_date(String add_date) {
		this.add_date = add_date;
	}


	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}


	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}


	/**
	 * @return the authors
	 */
	public String getAuthors() {
		return authors;
	}


	/**
	 * @param authors the authors to set
	 */
	public void setAuthors(String authors) {
		this.authors = authors;
	}


	/**
	 * @return the isbn
	 */
	public String getIsbn() {
		return isbn;
	}


	/**
	 * @param isbn the isbn to set
	 */
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}


	/**
	 * @return the publisher
	 */
	public String getPublisher() {
		return publisher;
	}


	/**
	 * @param publisher the publisher to set
	 */
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}


	/**
	 * @return the published_date
	 */
	public String getPublished_date() {
		return published_date;
	}


	/**
	 * @param published_date the published_date to set
	 */
	public void setPublished_date(String published_date) {
		this.published_date = published_date;
	}


	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}


	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}


	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StudentBook [category=" + category + ", student_id="
				+ student_id + ", student_name=" + student_name + ", add_date="
				+ add_date + ", title=" + title + ", authors=" + authors
				+ ", isbn=" + isbn + ", publisher=" + publisher
				+ ", published_date=" + published_date + ", status=" + status
				+ "]";
	}

}
