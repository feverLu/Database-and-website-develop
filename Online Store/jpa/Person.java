package cs5200.jpa;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.*;


/**
 * Entity implementation class for Entity: Person
 */
@Entity
@NamedQueries ({
	@NamedQuery(name="Person.findByAll", query = "SELECT p FROM Person p"),
	@NamedQuery(name="Person.findById", query="SELECT p FROM Person p WHERE p.id=:id"),
	@NamedQuery(name="Person.findByName", query="SELECT p FROM Person p WHERE p.firstName=:firstName AND p.lastName=:lastName"),
	@NamedQuery(name="Person.findByLognamePasscode", query = "SELECT p FROM Person p WHERE p.logName =:logName AND p.passcode =:passcode")
})
public class Person implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	//set the primary key
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name = "FIRST_NAME", nullable = false, length = 40)
	private String firstName;
	
	@Column(name = "LAST_NAME", nullable = false, length = 40)
	private String lastName;
	
	@Column(name = "DATE_OF_BIRTH", nullable = true)
	private Date dateOfBirth;
	
	@Column(name = "LOG_NAME", nullable = false, length = 20)
	private String logName;
	
	@Column(name = "PASSCODE", nullable = false, length = 20)
	private String passcode;
	
	//constructors
	public Person() {
		super();
	}
	public Person(String firstName, String lastName, String logName, String passcode) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.logName = logName;
		this.passcode = passcode;
	}
	public Person(String firstName, String lastName, String logName, String passcode, Date dateOfBirth) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.logName = logName;
		this.passcode = passcode;
		this.dateOfBirth = dateOfBirth;
	}
	
	//manipulations on the Person table
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getLogName () {
		return logName;
	}
	public void setLogName(String logName) {
		this.logName = logName;
	}
	public String getPasscode() {
		return passcode;
	}
	public void setPasscode(String passcode) {
		this.passcode = passcode;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public String toString() {
		String str = firstName + "\t" + lastName + "\t" + logName 
				+ "\t" + passcode + "\n";
		return str;
	}
}
