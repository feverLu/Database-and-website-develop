package edu.neu.cs5200.jga.jpa.cms;

import java.io.Serializable;
import java.lang.String;
import java.util.Date;
import java.util.List;

import javax.enterprise.inject.Default;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Website
 *
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Website.findAll",     query="SELECT w FROM Website w"),
	@NamedQuery(name="Website.findByName",  query="SELECT w FROM Website w WHERE w.name=:name")
})
public class Website implements Serializable {
	   
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String name;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;
	private String author;
	private List<Page> pages;
	private static final long serialVersionUID = 1L;

	public Website() {
		super();
	}
	
	public Website(String name) {
		this(name, null, null);
	}
	
	public Website(String name, String author) {
		this(name, author, null);
	}
	
	public Website(String name, String author, List<Page> pages) {
		super();
		this.name = name;
		this.author = author;
		this.pages = pages;
		this.created = new Date();
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}   
	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}   
	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public List<Page> getPages() {
		return pages;
	}

	public void setPages(List<Page> pages) {
		this.pages = pages;
	}
	public String toString() {
		String str = name + "\n";
		for(Page page : pages) {
			str += "\t" + page + "\n";
		}
		return str;
	}
}
