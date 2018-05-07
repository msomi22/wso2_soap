/**
 * 
 */
package au.edu.marrowa.unilib.bean;

/**
 * @author peter
 *
 */
public class Response {
	
	private String message;
	private String description;
	
	public Response(){
		message = "";
		description = "";
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Response [message=" + message + ", description=" + description
				+ "]";
	}

}
