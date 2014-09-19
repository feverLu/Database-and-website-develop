package cs5200.jpa;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import cs5200.jpa.Product;

@Entity
@Table(name = "Review")
@NamedQueries({
	@NamedQuery(name = "Review.findByAll", query = "SELECT r FROM Review r"),
	@NamedQuery(name = "Review.findById", query = "SELECT r FROM Review r WHERE r.id=:id"),
	@NamedQuery(name = "Review.findByProduct", query = "SELECT r FROM Review r WHERE r.product=:product")
	})
public class Review implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private int stars;
	private String reviews;
	private Date dateOfReview;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	Product product;
	@ManyToOne(cascade = CascadeType.PERSIST)
	Person buyer;
	
	public Review() {
		super();
	}
	public Review(Product products, Buyer buyers, int stars) {
		this.product = products;
		this.buyer = buyers;
		this.stars = stars;
	}
	public Review(Product products, Buyer buyers, String reviews) {
		this.product = products;
		this.buyer = buyers;
		this.reviews = reviews;
	}
	public Review(Product products, Buyer buyers, int stars, String reviews) {
		this.product = products;
		this.buyer = buyers;
		this.stars = stars;
		this.reviews = reviews;
	}
	public Review(Product products, Buyer buyers, int stars, String reviews, Date dateOfReview) {
		this.product = products;
		this.buyer = buyers;
		this.stars = stars;
		this.reviews = reviews;
		this.dateOfReview = dateOfReview;
	}
	
	public Review(String reviews) {
		this.reviews = reviews;
		dateOfReview = new Date(System.currentTimeMillis());
	}
	
	public Review(Product products, int stars) {
		this.product = products;
		this.stars = stars;
		dateOfReview = new Date(System.currentTimeMillis());
	}
	public Review(Product products, String reviews) {
		this.product = products;
		this.reviews = reviews;
		dateOfReview = new Date(System.currentTimeMillis());
	}
	public Review(Product products, int stars, String reviews) {
		this.product = products;
		this.stars = stars;
		this.reviews = reviews;
		dateOfReview = new Date(System.currentTimeMillis());
	}
	public Review(Product products, int stars, String reviews, Date dateOfReview) {
		this.product = products;
		this.stars = stars;
		this.reviews = reviews;
		this.dateOfReview = dateOfReview;
		dateOfReview = new Date(System.currentTimeMillis());
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getStar() {
		return stars;
	}
	public void setStar(int stars) {
		this.stars = stars;
	}
	public String getReview() {
		return reviews;
	}
	public void setReview(String reviews) {
		this.reviews = reviews;
	}
	public Date getDateOfReview() {
		return dateOfReview;
	}
	public void setDateOfReview(Date dateOfReviews) {
		this.dateOfReview = dateOfReviews;
	}
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product products) {
		this.product = products;
	}
	public Person getBuyer() {
		return buyer;
	}
	public void setBuyer(Person buyers) {
		this.buyer = buyers;
	}
	
	public String toString() {
		String str = stars + reviews + "\n";
		if (dateOfReview != null)
			str +=  dateOfReview.toString();
		return str;
	}
}

