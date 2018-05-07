package au.edu.marrowa.unilib.bean;

import java.io.Serializable;
import java.util.UUID;

public class StorableBean implements Serializable{


	private String uuid;

	public StorableBean(){
		uuid = UUID.randomUUID().toString().toUpperCase();
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = -5573512807117420849L;
}
