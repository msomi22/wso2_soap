package au.edu.marrowa.unilib.bean;

import java.util.Date;

public class Student extends StorableBean{

	private String full_name;
	private String student_id; 
	private String pin;
	private Date reg_date;

	public Student(){
		full_name = "";
		student_id = "";
		pin = "";
		reg_date = new Date();
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
	 * @return the reg_date
	 */
	public Date getReg_date() {
		return reg_date;
	}



	/**
	 * @param reg_date the reg_date to set
	 */
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}



	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Student [full_name=" + full_name + ", student_id=" + student_id
				+ ", pin=" + pin + ", reg_date=" + reg_date + "]";
	}



	/**
	 * 
	 */
	private static final long serialVersionUID = 5701292527046166541L;
}
