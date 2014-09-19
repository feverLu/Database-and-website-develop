package cs5200.jpa;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class Address implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private int streetNumber;
	private String street;
	private String city;
	private String state;
	private String postcode;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	Buyer buyer;
	
	public Address(){
		super();
	}
	public Address(int streetNumber, String street, String city, String state, String postcode) {
		this.streetNumber = streetNumber;
		this.street = street;
		this.city = city;
		this.state = state;
		this.postcode = postcode;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getStreetNumber() {
		return streetNumber;
	}
	public void setStreetNumber(int streetNumber) {
		this.streetNumber = streetNumber;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getSate() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostCode(String postcode) {
		this.postcode = postcode;
	}
	public Buyer getBuyer() {
		return buyer;
	}
	public void setBuyer(Buyer buyer) {
		this.buyer = buyer;
	}
	
	public String toString() {
		String str = streetNumber + "\t" + street + "\t" + city 
				+ "\t" + state + "\t" + postcode + "\n";
		return str;
	}
	

	
	
	
	
	
	
	
	
	
}
