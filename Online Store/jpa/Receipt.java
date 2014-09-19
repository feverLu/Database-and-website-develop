package cs5200.jpa;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Receipt
 */
@Entity
@Table(name = "Receipt")
public class Receipt implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	//Set the primary key
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name = "DATE_OF_RECEIT", nullable = true)
	private Date dateOfReceipt;
	
	//Set the foreign key
	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinColumn(name = "Buyer_ID", referencedColumnName = "id", nullable = false)
	private Buyer buyer;
	
	@OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	private List<ProductRecord> productRecords = new ArrayList<ProductRecord>();
	
	public Receipt(){
		super();
	}
	
	public Receipt(Date dateOfReceipt) {
		this.dateOfReceipt = dateOfReceipt;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Date getDateOfReceipt() {
		return dateOfReceipt;
	}
	
	public void setDateOfReceipt(Date dateOfReceipt) {
		this.dateOfReceipt = dateOfReceipt;
	}
	
	public Buyer getBuyer() {
		return buyer;
	}
	
	public void setBuyer(Buyer buyer) {
		this.buyer = buyer;
	}
	
	public List<ProductRecord> getProductRecord() {
		return productRecords;
	}
	
	public void setProductRecord(List<ProductRecord> productRecords) {
		this.productRecords = productRecords;
	}
	
	public String toString() {
		String str = "\t" + dateOfReceipt + "\t";
		if (buyer != null) {
			str += buyer.toString();
		}
		if(productRecords != null) {
			for (ProductRecord productRecord : productRecords) {
				str += "\t" + productRecord + "\n";
			}
		}
		return str;
	}
}
