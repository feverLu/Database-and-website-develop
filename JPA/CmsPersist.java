package edu.neu.cs5200.jga.jpa.cms;

import java.util.List;
import java.util.Arrays;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CmsPersist {

	public static void main(String[] args) {

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory( "JPA5" );
	    EntityManager entityManager = entityManagerFactory.createEntityManager();

	    entityManager.getTransaction().begin();
	    
	    Widget genericWidget = new Widget("some-widget","class","style", "name");
	    entityManager.persist(genericWidget);
	    
	    YouTubeWidget youTubeWidget = new YouTubeWidget("you-tube-widget", "class", "style", "name", "http://url.com", "123px", "234px");
	    entityManager.persist(youTubeWidget);

	    HtmlWidget htmlWidget = new HtmlWidget("raw-html-widget", "class-2", "style-2", "name-2", "<h1>HTML Widget</h1>");
	    entityManager.persist(htmlWidget);
	    
	    Region region1 = new Region("region1");	    
	    Region region2 = new Region("region2");
	    
	    Widget[] widgets = {youTubeWidget, htmlWidget};	    
	    Region[] regions = {region1, region2};

	    List<Widget> widgetList = Arrays.asList(widgets);
	    List<Region> regionList = Arrays.asList(regions);
	    
	    Region region3 = new Region("region3", widgetList, regionList);
	    region1.setParentRegion(region3);
	    region2.setParentRegion(region3);
	    entityManager.persist(region3);

	    Region[] homeRegions = {region3};
	    List<Region> homeRegionList = Arrays.asList(homeRegions);
	    
	    Page homePage = new Page("Home Page", homeRegionList);
	    region3.setPage(homePage);
	    entityManager.persist(homePage);

	    Page[] pages = {homePage};
	    List<Page> pageList = Arrays.asList(pages);
	    
	    Website site = new Website("name", "me", pageList);
	    entityManager.persist(site);
	    		
	    entityManager.getTransaction().commit();
	    entityManager.close();
	}

}
