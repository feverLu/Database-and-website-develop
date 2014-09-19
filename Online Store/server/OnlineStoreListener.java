package cs5200.server;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class OnlineStoreListener implements ServletContextListener {
	@Override
	public void contextInitialized(ServletContextEvent e) {
		// TODO Auto-generated method stub
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("onlinestore");
		e.getServletContext().setAttribute("emf", emf);
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent e) {
		// TODO Auto-generated method stub
		EntityManagerFactory emf = (EntityManagerFactory) e.getServletContext().getAttribute("emf");
		emf.close();
	}
}
