package cs5200.jpa;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.*;

@Entity
public class CreditCard implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String number;
	private String securityNumber;
	private CardType type;
	private Date expiryDate;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	Buyer buyer;
	
	public CreditCard() {
		super();
	}
	public CreditCard(String number, String securityNumber, CardType type, Date expiryDate) {
		this.number = number;
		this.securityNumber = securityNumber;
		this.type = type;
		this.expiryDate = expiryDate;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getSecurityNumber() {
		return securityNumber;
	}
	public void setSecurityNumber(String securityNumber) {
		this.securityNumber = securityNumber;
	}
	public CardType getCardType() {
		return type;
	}
	public void setCardType(CardType type) {
		this.type = type;
	}
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	public Buyer getBuyer() {
		return buyer;
	}
	public void setBuyer(Buyer buyer) {
		this.buyer = buyer;
	}
	
	public String toString() {
		String str = number + securityNumber + type + expiryDate.toString() + "\n";
		return str;
	}
	
	
	
	
	
	
	
	

}
