package edu.neu.cs5200.jga.jpa.cms;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: YouTubeWidget
 *
 */
@Entity

public class YouTubeWidget extends Widget implements Serializable {

	private int id;
	private String url;
	private String width;
	private String height;
	private static final long serialVersionUID = 1L;

	public YouTubeWidget() {
		super();
	}
	
	public YouTubeWidget(String htmlId, String htmlClass, String htmlStyle,
			String htmlName, String url, String width, String height) {
		super(htmlId, htmlClass, htmlStyle, htmlName);
		this.url = url;
		this.width = width;
		this.height = height;
	}

		
	public YouTubeWidget(String htmlId, String htmlClass, String htmlStyle,
			String htmlName) {
		super(htmlId, htmlClass, htmlStyle, htmlName);
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}   
	public String getWidth() {
		return this.width;
	}

	public void setWidth(String width) {
		this.width = width;
	}   
	public String getHeight() {
		return this.height;
	}

	public void setHeight(String height) {
		this.height = height;
	}
   
}
