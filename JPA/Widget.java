package edu.neu.cs5200.jga.jpa.cms;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Widget
 *
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Widget.findAll",  query="SELECT w FROM Widget w"),
	@NamedQuery(name="Widget.findById", query="SELECT w FROM Widget w WHERE w.id=:id"),
	@NamedQuery(name="Widget.findByHtmlId", query="SELECT w FROM Widget w WHERE w.htmlId=:id"),
	@NamedQuery(name="Widget.findByHtmlName", query="SELECT w FROM Widget w WHERE w.htmlName=:name")
})
public class Widget implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String htmlId;
	private String htmlClass;
	private String htmlStyle;
	private String htmlName;
	private static final long serialVersionUID = 1L;

	public Widget(String htmlId, String htmlClass, String htmlStyle,
			String htmlName) {
		super();
		this.htmlId = htmlId;
		this.htmlClass = htmlClass;
		this.htmlStyle = htmlStyle;
		this.htmlName = htmlName;
	}
	public Widget() {
		super();
	}   
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public String getHtmlId() {
		return this.htmlId;
	}

	public void setHtmlId(String htmlId) {
		this.htmlId = htmlId;
	}   
	public String getHtmlClass() {
		return this.htmlClass;
	}

	public void setHtmlClass(String htmlClass) {
		this.htmlClass = htmlClass;
	}   
	public String getHtmlStyle() {
		return this.htmlStyle;
	}

	public void setHtmlStyle(String htmlStyle) {
		this.htmlStyle = htmlStyle;
	}   
	public String getHtmlName() {
		return this.htmlName;
	}

	public void setHtmlName(String htmlName) {
		this.htmlName = htmlName;
	}

	public String toString() {
		return id + " " + htmlId + " " + htmlName + " " + this.getClass();
	}
}
