package cs5200.jpa;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "Seller")
@NamedQuery(name="Seller.findByAll", query="SELECT s FROM Seller s")
public class Seller extends Person implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//	private int rate;
	
	//constraints
	@OneToMany()
	private List<Product> products;
	@OneToMany(cascade = CascadeType.PERSIST)
	private List<BankAccount> bankAccounts;

	
	//constructors
	public Seller() {
		super();
	}
	public Seller(String firstName, String lastName, String logName, String passcode) {
		super(firstName, lastName, logName, passcode);
	}
	public Seller(String firstName, String lastName, String logName, String passcode, Date dateOfBirth) {
		super(firstName, lastName, logName, passcode, dateOfBirth);
	}
//	public Seller(String firstName, String lastName, String logName, String passcode, int rate) {
//		super(firstName, lastName, logName, passcode);
//		this.rate = rate;
//	}
//	public Seller(String firstName, String lastName, String logName, String passcode, Date dateOfBirth, int rate) {
//		super(firstName, lastName, logName, passcode, dateOfBirth);
//		this.rate = rate;
//	}
	
	//functions
//	public int getRate() {
//		return rate;
//	}
//	public void setRate(int rate) {
//		this.rate = rate;
//	}
	public List<Product> getProduct() {
		return products;
	}
	public void setProduct(List<Product> products) {
		this.products = products;
	}
	public List<BankAccount> getBankAccount() {
		return bankAccounts;
	}
	public void setBankAccount(List<BankAccount> bankAccounts) {
		this.bankAccounts = bankAccounts;
	}

	
	public String toString() {
//		String str = rate + "\n";
		String str = "";
		if(products != null) {
			for (Product product : products) {
				str += "\t\t" + product + "\n";
			}
		}
		if(bankAccounts != null) {
			for(BankAccount bankAccount : bankAccounts) {
				str += "\t\t" + bankAccount + "\n";
			}
		}
		return str;
	}
}
