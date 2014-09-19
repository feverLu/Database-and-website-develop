package cs5200.jpa;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "BankAccount")
@NamedQuery(name="BankAccount.findBySeller", query="SELECT b FROM BankAccount b WHERE b.seller=:seller")
public class BankAccount implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String number;
	private float amount;
	private String routing;
	
	@ManyToOne()
	Seller seller;
	
	public BankAccount() {
		super();
	}
	
	public BankAccount(String number) {
		this.number = number;
	}
	public BankAccount(String number, String routing) {
		this.number = number;
		this.routing = routing;
	}
	public BankAccount(String number, float amount, String routing) {
		this.number = number;
		this.amount = amount;
		this.routing = routing;
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
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public String getRouting() {
		return routing;
	}
	public void setRouting(String routing) {
		this.routing = routing;
	}
	public Seller getSeller() {
		return seller;
	}
	public void setSeller(Seller seller) {
		this.seller = seller;
	}
	
	public String toString() {
		String str = number + "\t" + amount + "\t" + routing + "\n";
		return str;
	}
}
