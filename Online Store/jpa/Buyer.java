package cs5200.jpa;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "Buyer")
@NamedQuery(name="Buyer.findByAll", query = "SELECT b FROM Buyer b")
public class Buyer extends Person implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//constraints
	@OneToMany()
	private List<Receipt> recipits;
	@OneToMany(cascade = CascadeType.PERSIST) 
	private List<Address> addresses;
	@OneToMany(cascade = CascadeType.PERSIST)
	private List<CreditCard> creditCards;
	@OneToMany(cascade = CascadeType.PERSIST)
	private List<Review> reviews;
	@OneToMany(cascade = CascadeType.PERSIST)
	private List<Receipt> receipts;
	
	//constructors
	public Buyer() {
		super();
	}
	public Buyer(String firstName, String lastName, String logName, String passcode) {
		super(firstName, lastName, logName, passcode);
	}
	public Buyer(String firstName, String lastName, String logName, String passcode, Date dateOfBirth) {
		super(firstName, lastName, logName, passcode, dateOfBirth);
	}
	
	//functions
//	public List<Receipt> getOrder() {
//		return recipits;
//	}
//	public void setOrder(List<Receipt> recipits) {
//		this.recipits = recipits;
//	}
//	public List<Review> getReview() {
//		return reviews;
//	}
//	public void setReview(List<Review> reviews) {
//		this.reviews = reviews;
//	}
	public List<Address> getAddress() {
		return addresses;
	}
	public void setAddress(List<Address> addresses) {
		this.addresses = addresses;
	}
	public List<CreditCard> getCreditCard() {
		return creditCards;
	}
	public void setCreditCard(List<CreditCard> creditCards) {
		this.creditCards = creditCards;
	}
	public List<Review> getReview() {
		return reviews;
	}
	public void setReview(List<Review> reviews) {
		this.reviews = reviews;
	}
	public List<Receipt> getReceipt() {
		return receipts;
	}
	public void setReceipt(List<Receipt> receipts) {
		this.receipts = receipts;
	}
	
	public String toString() {
		String str = "";
//		if (recipits != null) {
//			str += "\t\t" + recipits + "\n";
//		}
//		if(reviews != null) {
//			str += "\t\t" + reviews + "\n";
//		}
		if(addresses != null) {
			str += "\t\t" + addresses + "\n";
		}
		if(creditCards != null) {
			str += "\t\t" + creditCards + "\n";
		}
		return str;
	}
	
	
	
	
	
	
	
	
	
	
}
