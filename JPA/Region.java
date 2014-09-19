package edu.neu.cs5200.jga.jpa.cms;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Region
 *
 */
@Entity
@NamedQuery(name="Region.findByName", query="SELECT r FROM Region r WHERE r.name=:name")
public class Region implements Serializable {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String name;
	private List<Widget> widgets;
	
	@OneToMany(mappedBy = "parentRegion", cascade = CascadeType.ALL, orphanRemoval=true)
	private List<Region> childRegions;
	@ManyToOne(cascade = CascadeType.ALL)
	private Page page;
	@ManyToOne(cascade = CascadeType.ALL)
	private Region parentRegion;
	private static final long serialVersionUID = 1L;

	public Region() {
		super();
	}
	
	public Region(String name) {
		this(name, null, null);
	}

	public Region(String name, List<Widget> widgets) {
		this(name, widgets, null);
	}

	public Region(String name, List<Widget> widgets, List<Region> children) {
		super();
		this.name = name;
		this.widgets = widgets;
		this.childRegions = children;
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
	public List<Widget> getWidgets() {
		return widgets;
	}
	public void setWidgets(List<Widget> widgets) {
		this.widgets = widgets;
	}

	public List<Region> getChildRegions() {
		return childRegions;
	}

	public void setChildRegions(List<Region> childRegions) {
		this.childRegions = childRegions;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public Region getParentRegion() {
		return parentRegion;
	}

	public void setParentRegion(Region parentRegion) {
		this.parentRegion = parentRegion;
	}

	public String toString() {
		String str = name + "\n";
		for(Widget widget : widgets) {
			str += "\t\t\t" + widget + "\n";
		}
		for(Region region : childRegions) {
			str += "\t\t\t" + region + "\n";
		}
		return str;
	}
}
