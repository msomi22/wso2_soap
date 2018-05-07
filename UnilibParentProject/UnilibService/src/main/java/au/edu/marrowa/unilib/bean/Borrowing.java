/**
 * 
 */
package au.edu.marrowa.unilib.bean;

import java.util.Date;

/**
 * @author peter
 *
 */
public class Borrowing extends StorableBean{


	private String category;// is_borrowing, success_requested
	private String book_id;
	private String student_id;
	private Date add_date;

	public Borrowing(){
		category = "";
		book_id = "";
		student_id = "";
		add_date = new Date();
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getBook_id() {
		return book_id;
	}

	public void setBook_id(String book_id) {
		this.book_id = book_id;
	}

	public String getStudent_id() {
		return student_id;
	}

	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}

	public Date getAdd_date() {
		return add_date;
	}

	public void setAdd_date(Date add_date) {
		this.add_date = add_date;
	}

	@Override
	public String toString() {
		return "Borrowing [category=" + category + ", book_id=" + book_id
				+ ", student_id=" + student_id + ", add_date=" + add_date
				+ "]";
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 2936756215679484280L;
}
