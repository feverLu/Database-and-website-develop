package edu.neu.cs5200.jga.jpa.cms;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: HtmlWidget
 *
 */
@Entity

public class HtmlWidget extends Widget implements Serializable {
	   
	private int id;
	private String rawHtml;
	private static final long serialVersionUID = 1L;

	public HtmlWidget() {
		super();
	}
	
	public HtmlWidget(String htmlId, String htmlClass, String htmlStyle,
			String htmlName, String rawHtml) {
		super(htmlId, htmlClass, htmlStyle, htmlName);
		this.rawHtml = rawHtml;
	}

	public HtmlWidget(String htmlId, String htmlClass, String htmlStyle,
			String htmlName) {
		super(htmlId, htmlClass, htmlStyle, htmlName);
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public String getRawHtml() {
		return this.rawHtml;
	}

	public void setRawHtml(String rawHtml) {
		this.rawHtml = rawHtml;
	}
   
}
