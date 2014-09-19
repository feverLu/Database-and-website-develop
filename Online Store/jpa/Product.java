package cs5200.jpa;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import cs5200.jpa.Review;

@Entity
@Table(name = "Product")
@NamedQueries({
	@NamedQuery(name = "Product.findByAll", query = "SELECT p FROM Product p"),
	@NamedQuery(name = "Product.findByName", query = "SELECT p FROM Product p WHERE p.name=:name"),
	@NamedQuery(name = "Product.findById", query = "SELECT p FROM Product p WHERE p.id=:id")
	})
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String name;
	private float price;
	private String description;
	
	@ManyToOne()
	private Person seller;
	@OneToMany(cascade=CascadeType.PERSIST)
	private List<Review> reviews;
	@OneToMany()
	private List<ProductRecord> productRecords;
	
	public Product() {
		super();
	}
	public Product(String name) {
		this.name = name;
	}
	public Product(String name, float price) {
		this.name = name;
		this.price = price;
	}
	public Product(String name, float price, String description) {
		this.name = name;
		this.price = price;
		this.description = description;
	}
	
	public int getId() {
		return id;
	}
	public void setInt(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Person getSeller() {
		return seller;
	}
	public void setSeller(Person seller) {
		this.seller = seller;
	}
	public List<Review> getReview() {
		return reviews;
	}
	public void setReview(List<Review> reviews) {
		this.reviews = reviews;
	}
	public List<ProductRecord> getProductRecord() {
		return productRecords;
	}
	public void setProductRecord(List<ProductRecord> productRecords) {
		this.productRecords = productRecords;
	}
	
	public String toString() {
		String str = "\t" + name + "\t" + price + "\t" + description 
				+ "\t" + "\n";
		if(reviews != null) {
			for (Review review : reviews) {
				str += "\t" + review +"\n";
			}
		}
		if(productRecords != null) {
			for (ProductRecord productRecord: productRecords) {
				str += "\t" + productRecord + "\n";
			}
		}
		return str;
	}
}
