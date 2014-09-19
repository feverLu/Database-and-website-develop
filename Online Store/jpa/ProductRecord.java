package cs5200.jpa;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class ProductRecord implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private int quantity;
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	private Product product;
	@ManyToOne(cascade=CascadeType.PERSIST)
	private Receipt receipt;
	
	public ProductRecord() {
		super();
	}
	public ProductRecord(int quantity) {
		this.quantity = quantity;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Receipt getReceipt() {
		return receipt;
	}
	public void setReceipt(Receipt receipt) {
		this.receipt = receipt;
	}
	 public String toString() {
		 String str = "\\t" + quantity + "\n";
		 return str;
	 }
	
	
	
	
	
	
	
	
	
	

}
