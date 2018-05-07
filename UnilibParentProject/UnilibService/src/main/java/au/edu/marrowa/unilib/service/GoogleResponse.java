/**
 * 
 */
package au.edu.marrowa.unilib.service;

/**
 * @author peter
 *
 */
public class GoogleResponse {
	
	private String isbn;
	private String title;
	private String publisher;
	private String publishedDate;
	private String authors;
	private String averageRating;
	
	private String country;
	private String isEbook;
	private String saleability;

	/**
	 * 
	 */
	public GoogleResponse() {
		isbn = "";
		title = "";
		publisher = "";
		publishedDate = "";
		authors = "";
		averageRating = "";
		country = "";
		isEbook = "";
		saleability = "";
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
	 * @return the publishedDate
	 */
	public String getPublishedDate() {
		return publishedDate;
	}

	/**
	 * @param publishedDate the publishedDate to set
	 */
	public void setPublishedDate(String publishedDate) {
		this.publishedDate = publishedDate;
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
	 * @return the averageRating
	 */
	public String getAverageRating() {
		return averageRating;
	}

	/**
	 * @param averageRating the averageRating to set
	 */
	public void setAverageRating(String averageRating) {
		this.averageRating = averageRating;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the isEbook
	 */
	public String getIsEbook() {
		return isEbook;
	}

	/**
	 * @param isEbook the isEbook to set
	 */
	public void setIsEbook(String isEbook) {
		this.isEbook = isEbook;
	}

	/**
	 * @return the saleability
	 */
	public String getSaleability() {
		return saleability;
	}

	/**
	 * @param saleability the saleability to set
	 */
	public void setSaleability(String saleability) {
		this.saleability = saleability;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GoogleResponse [isbn=" + isbn + ", title=" + title
				+ ", publisher=" + publisher + ", publishedDate="
				+ publishedDate + ", authors=" + authors + ", averageRating="
				+ averageRating + ", country=" + country + ", isEbook="
				+ isEbook + ", saleability=" + saleability + "]";
	}

}
