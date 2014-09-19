package cs5200.jpa;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class Administrator extends Person implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Administrator() {
		super();
	}
	public Administrator(String firstName, String lastName, String logName, String passcode) {
		super(firstName, lastName, logName, passcode);
	}
	public String toString() {
		String str = "";
		return str;
	}
}
