package edu.neu.cs5200.jga.jpa.cms;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import java.util.ListIterator;

public class CmsSelect {

	public static void main(String[] args) {

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory( "JPA5" );
	    EntityManager entityManager = entityManagerFactory.createEntityManager();

	    entityManager.getTransaction().begin();
	    
	    TypedQuery<Widget> widgetQuery = entityManager.createQuery("Select w from Widget w", Widget.class);
	    List<Widget> widgets = widgetQuery.getResultList();
	    printWidgets(widgets);
  
	    widgetQuery = entityManager.createNamedQuery("Widget.findAll", Widget.class);
	    widgets = widgetQuery.getResultList();	    
	    printWidgets(widgets);

	    widgetQuery = entityManager.createNamedQuery("Widget.findByHtmlId", Widget.class);
	    widgetQuery.setParameter("id", "you-tube-widget");
	    widgets = widgetQuery.getResultList();
	    printWidgets(widgets);
	    
	    widgetQuery = entityManager.createNamedQuery("Widget.findByHtmlName", Widget.class);
	    widgetQuery.setParameter("name", "name-3");
	    widgets = widgetQuery.getResultList();
	    printWidgets(widgets);
	    
	    widgetQuery = entityManager.createNamedQuery("Widget.findByHtmlName", Widget.class);
	    widgetQuery.setParameter("name", "name-3");
	    widgets = widgetQuery.getResultList();
	    printWidgets(widgets);
	    
	    TypedQuery<Website> websiteQuery = entityManager.createNamedQuery("Website.findAll", Website.class);
	    Website website = websiteQuery.getSingleResult();
	    System.out.println(website);
	    
	    List<Page> pages = website.getPages();
	    for(Page page : pages) {
	    	System.out.println(page);
	    	List<Region> regions = page.getRegions();
	    	for(Region region : regions) {	    		
		    	System.out.println(region);
		    	List<Region> subRegions = page.getRegions();
		    	for(Region r:subRegions) {
			    	System.out.println(r);
		    	}
		    	widgets = region.getWidgets();
		    	for(Widget w:widgets) {
			    	System.out.println(w);
		    	}
	    	}
	    }
	    
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
