package au.edu.marrowa.unilib.service.bean;


public class LibBook {
	
	private String title;
	private String authors;
	private String isbn;
	private String publisher;
	private String published_date;
	private String status;// available, on_loan,  back_order 

	public LibBook(){
		
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
		return "LibBook [title=" + title + ", authors=" + authors + ", isbn="
				+ isbn + ", publisher=" + publisher + ", published_date="
				+ published_date + ", status=" + status + "]";
	}
	
}
