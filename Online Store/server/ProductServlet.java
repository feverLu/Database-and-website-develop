package cs5200.server;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import cs5200.jpa.Buyer;
import cs5200.jpa.Person;
import cs5200.jpa.Product;
import cs5200.jpa.Review;
import cs5200.jpa.Seller;

public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public ProductServlet() {
		super();
	}
	
	 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    	EntityManagerFactory emf = (EntityManagerFactory)getServletContext().getAttribute("emf");
	        EntityManager em  = emf.createEntityManager();
	        
	        try {
	        	String command	 	= request.getParameter("command");
	        	String personId		= request.getParameter("personId");
	            String productId	= request.getParameter("productId");
	            String receiptId 	= request.getParameter("receiptId");
	            String name 	 	= request.getParameter("name");
	            String description  = request.getParameter("description");
	            String price	 	= request.getParameter("price");
	            String reviewId  	= request.getParameter("reviewId");
				String reviewStars	= request.getParameter("reviewStars");
				String reviews		= request.getParameter("reviews");
				String logName		= request.getParameter("logName");
				String passcode		= request.getParameter("passcode");

	            String dispatchTo = "/productList.jsp";
	            em.getTransaction().begin();

	            System.out.println("command" + command);
	            System.out.println("personId" + personId);
	            
	            if (command != null && command.equals("create") && name != null && (!personId.equals("null")) && personId != null) {
	            	System.out.println("S6LineOK");
	            	Product product = new Product();
	            	product.setName(name);
	            	product.setDescription(description);
	            	Seller seller = em.find(Seller.class, Integer.valueOf(personId));
	            	System.out.println("create book personId " +personId);
	            	product.setSeller(seller);
	            	if (price != null) {
	            		product.setPrice(Integer.parseInt(price)); 
	            	}
	            	if(reviewId != null) {
	            		System.out.println("7LineOK");
	            		Review review = em.find(Review.class, Integer.parseInt(reviewId));
	            		if(review != null) {
		            		review.setProduct(product);
		            		product.getReview().add(review);
	            		}
	            	}
	            	System.out.println("S7LineOK");
	            	em.persist(product);
	            	request.setAttribute("personId", personId);
	            	request.setAttribute("receiptId", receiptId);
	            	dispatchTo = "/productList.jsp";
	            } else if(command != null && command.equals("read") && productId != null) {
//	                Product product = em.find(Product.class, Integer.parseInt(id));
	            	request.setAttribute("personId", personId);
	            	request.setAttribute("receiptId", receiptId);
	                request.setAttribute("productId", productId);
	                System.out.println("read personId" + personId);
	                dispatchTo = "/productDetail.jsp";
	            } else if (command != null && command.equals("productUpdate") && productId != null && (!personId.equals("null"))) {
	            	System.out.println("productUpdate sevelet personId " + personId);
	            	request.setAttribute("personId", personId);
	                request.setAttribute("productId", productId);
	                request.setAttribute("receiptId", receiptId);
	                dispatchTo = "/productUpdate.jsp";
	            } else if(command != null && command.equals("update") && productId != null && (!personId.equals("null"))) {
	                Product product = em.find(Product.class, Integer.parseInt(productId));
	                product.setName(name);
	                product.setPrice(Float.parseFloat(price));
	                product.setDescription(description);
	                request.setAttribute("personId", personId);
	                request.setAttribute("productId", productId);
	                request.setAttribute("receiptId", receiptId);
	                dispatchTo = "/productDetail.jsp";
	            } else if(command != null && command.equals("delete") && productId != null && (!personId.equals("null"))) {
	            	Product product = em.find(Product.class, Integer.parseInt(productId));
	            	List<Review> productReviews = product.getReview();
	            	int size = productReviews.size();
	            	for (int i=0; i<size; i++) {
	            		em.remove(productReviews.get(i));
	            	}
	            	request.setAttribute("personId", personId);
	            	request.setAttribute("receiptId", receiptId);
	                em.remove(product);
	                dispatchTo = "/productList.jsp";
	            } else if(command != null && command.equals("deleteReview") && productId != null && reviewId != null && (!personId.equals("null"))) {
	                Product product = em.find(Product.class, Integer.parseInt(productId));
	                System.out.println("deleteReview!!!!product" + product);
	                if(reviewId != null) {
		            	Review review = em.find(Review.class, Integer.parseInt(reviewId));
		            	System.out.println("deleteReview!!!!review" + review);
		            	if(review != null) {
		            		//buyer.getReview().remove(review);
			                product.getReview().remove(review);
			                em.remove(review);
		            	}
	                }
	                request.setAttribute("personId", personId);
	                request.setAttribute("productId", productId);
	                request.setAttribute("receiptId", receiptId);
	                dispatchTo = "/productDetail.jsp";
	            } else if(command != null && command.equals("updateReview") && reviewId != null && productId != null && (!personId.equals("null"))) {
	            	System.out.println("updateReview entered" + reviewId);
	            	Review review = em.find(Review.class, Integer.parseInt(reviewId));
	            	if (review != null) {
	            		System.out.println("updateReview in" + review);
	            		review.setStar(Integer.valueOf(reviewStars));
	            		review.setReview(reviews);
	            		review.setDateOfReview(new Date(System.currentTimeMillis()));
	            	}
	            	request.setAttribute("personId", personId);
	                request.setAttribute("productId", productId);
	                request.setAttribute("receiptId", receiptId);
	            	dispatchTo = "/productDetail.jsp";
	            } else if((command != null) && (command.equals("addReview")) && (productId != null) && (!personId.equals("null"))) {
	            	System.out.println("addReview entered");
	            	System.out.println("addreview personId" + personId);
	            	Product product = em.find(Product.class, Integer.parseInt(productId));
	            	Review review = new Review();
	            	Buyer buyer = new Buyer();
	            	buyer = em.find(Buyer.class, Integer.parseInt(personId));
	            	review.setBuyer(buyer);
	            	System.out.println("Add a new review buyer " + buyer);
	            	try {
	            		buyer.getReview().add(review);
	            	} catch (Exception e) {}
	            	review.setReview(reviews);
	            	if(reviewStars != null) {
	            		review.setStar(Integer.valueOf(reviewStars));
	            	}
	            	review.setDateOfReview(new Date(System.currentTimeMillis()));
	            	reviewId = review.getId() + "";
	                if(reviewId != null) {
		            	if(review != null) {
		            		product.getReview().add(review);
		            		review.setProduct(product);
		            	}
	                }
	                System.out.println("addreview personId" + personId);
	                request.setAttribute("personId", personId);
	                request.setAttribute("productId", productId);
	                request.setAttribute("receiptId", receiptId);
	                dispatchTo = "/productDetail.jsp";
	            }  else if (command != null && command.equals("login") && logName!=null && passcode!= null) {
					 TypedQuery<Person> personQuery = em.createNamedQuery("Person.findByLognamePasscode", Person.class);
				     personQuery.setParameter("logName", logName);
				     personQuery.setParameter("passcode", passcode);
				     List<Person> person = personQuery.getResultList();
				     if (!person.isEmpty()) {
				    	 //only one DBA named after this
				    	 System.out.println("login yes");
				    	 System.out.println("LOGIN personID" + person.get(0).getId());
				    	 personId = person.get(0).getId() + "";
				    	 request.setAttribute("personId", personId);
//				    	 request.getRequestDispatcher("/WebContent/productList.jsp");
//				    	 response.sendRedirect("/productList.jsp");
				    	 dispatchTo = "/productList.jsp";
				     } else {
				         //do not exist
				    	 System.out.println("login no");
				    	 JOptionPane.showMessageDialog(null, "No user found!", "Alert",JOptionPane.WARNING_MESSAGE);  
				     }
				 } else if (command != null && command.equals("continueShop") && receiptId != null && (!personId.equals("null"))) {
					 request.setAttribute("personId", personId);
					 request.setAttribute("receiptId", receiptId);
					 dispatchTo = "/productList.jsp";
				 }
	            em.getTransaction().commit();
	            
	            List<Product> productList = em.createQuery("SELECT p FROM Product p", Product.class).getResultList();
	            request.setAttribute("productList", productList);
	            List<Review> reviewList = em.createQuery("SELECT r FROM Review r", Review.class).getResultList();
	            request.setAttribute("reviewList", reviewList);
	            
	            request.getRequestDispatcher(dispatchTo).forward(request, response);
	            
	        } finally {
	            if (em.getTransaction().isActive())
	                em.getTransaction().rollback();
	            em.close();
	        }
	    }
	 
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    	System.out.println("9LineOK");
	    	doGet(request, response);
	    }
	}
