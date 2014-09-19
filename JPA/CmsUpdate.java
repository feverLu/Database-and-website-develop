package edu.neu.cs5200.jga.jpa.cms;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import java.util.ListIterator;

public class CmsUpdate {

	public static void main(String[] args) {

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory( "JPA5" );
	    EntityManager entityManager = entityManagerFactory.createEntityManager();

	    entityManager.getTransaction().begin();
	    
	    TypedQuery<Widget> widgetQuery = entityManager.createNamedQuery("Widget.findByHtmlId", Widget.class);
	    widgetQuery.setParameter("id", "you-tube-widget");
	    Widget widget = widgetQuery.getSingleResult();
	    System.out.println(widget);
	    System.out.println("==========");
	    System.out.println(widget.getHtmlName());
	    widget.setHtmlName("This is the new name");
	    System.out.println(widget.getHtmlName());
	    System.out.println("==========");
	    
	    TypedQuery<Website> websiteQuery = entityManager.createNamedQuery("Website.findAll", Website.class);
	    Website website = websiteQuery.getSingleResult();
	    System.out.println(website);
	    System.out.println("==========");
	    System.out.println(website.getName());
	    website.setName("CNN");
	    System.out.println(website.getName());
	    System.out.println("==========");
	    
	    Page aboutUs = new Page("About Us");
	    entityManager.persist(aboutUs);
	    website.getPages().add(aboutUs);
	    System.out.println(website);
	    
	    entityManager.getTransaction().commit();
	    entityManager.close();
	}
	public static void printWidgets(List<Widget> widgets) {
	    ListIterator<Widget>iterator = widgets.listIterator();
	    while(iterator.hasNext()) {
	    	Widget widget = iterator.next();
	    	System.out.println(widget);
	    }
	}
}
