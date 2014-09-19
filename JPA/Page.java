package edu.neu.cs5200.jga.jpa.cms;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Page
 *
 */
@Entity

public class Page implements Serializable {
	   
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String name;
	@OneToMany(fetch=FetchType.EAGER)
	private List<Region> regions;
	private static final long serialVersionUID = 1L;

	public Page() {
		this(null, null);
	}
	public Page(String name) {
		this(name, null);
	}
	public Page(String name, List<Region> regions) {
		this.name = name;
		this.regions = regions;
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
	public List<Region> getRegions() {
		return regions;
	}
	public void setRegions(List<Region> regions) {
		this.regions = regions;
	}
	public String toString() {
		String str = name + "\n";
		if(regions != null)
			for(Region region : regions) {
				str += "\t\t" + region + "\n";
			}
		return str;
	}
}
