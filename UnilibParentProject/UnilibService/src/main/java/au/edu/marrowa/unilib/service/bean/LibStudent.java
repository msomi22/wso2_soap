/**
 * 
 */
package au.edu.marrowa.unilib.service.bean;

/**
 * @author peter
 *
 */
public class LibStudent {
	
	private String full_name;
	private String student_id; 
	private String pin;
	
	public LibStudent(){
		full_name = "";
		student_id = "";
		pin = "";
	}

	/**
	 * @return the full_name
	 */
	public String getFull_name() {
		return full_name;
	}

	/**
	 * @param full_name the full_name to set
	 */
	public void setFull_name(String full_name) {
		this.full_name = full_name;
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
	 * @return the pin
	 */
	public String getPin() {
		return pin;
	}

	/**
	 * @param pin the pin to set
	 */
	public void setPin(String pin) {
		this.pin = pin;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LibStudent [full_name=" + full_name + ", student_id="
				+ student_id + ", pin=" + pin + "]";
	}

}
