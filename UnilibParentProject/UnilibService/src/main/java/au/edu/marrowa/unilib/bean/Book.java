/**
 * 
 */
package au.edu.marrowa.unilib.bean;

import java.util.Date;





/**
 * @author peter
 *
 */
public class Book extends StorableBean{

	int id;
	private String title;
	private String authors;
	private String isbn;
	private String publisher;
	private String published_date;
	private String status;// available, on_loan,  back_order   
	private Date last_updated;
	private Date log_date;

	public Book(){
		id = 0;
		title = "";
		authors = "";
		isbn = "";
		publisher = "";
		published_date = "";
		status = "";
		last_updated = new Date(0);
		log_date = new Date(0);
	}
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
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
	 * @return the last_updated
	 */
	public Date getLast_updated() {
		return last_updated;
	}

	/**
	 * @param last_updated the last_updated to set
	 */
	public void setLast_updated(Date last_updated) {
		this.last_updated = last_updated;
	}

	/**
	 * @return the log_date
	 */
	public Date getLog_date() {
		return log_date;
	}

	/**
	 * @param log_date the log_date to set
	 */
	public void setLog_date(Date log_date) {
		this.log_date = log_date;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", authors=" + authors
				+ ", isbn=" + isbn + ", publisher=" + publisher
				+ ", published_date=" + published_date + ", status=" + status
				+ ", last_updated=" + last_updated + ", log_date=" + log_date
				+ "]";
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -4670257622369265646L;
}
