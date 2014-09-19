package cs5200.server;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cs5200.jpa.Buyer;
import cs5200.jpa.Product;
import cs5200.jpa.ProductRecord;
import cs5200.jpa.Receipt;

public class ReceiptServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L; 
	public ReceiptServlet() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 EntityManagerFactory emf = (EntityManagerFactory)getServletContext().getAttribute("emf");
	     EntityManager em  = emf.createEntityManager();
	     
	     try {
	    	 String command 	= request.getParameter("command");
	    	 String productId	= request.getParameter("productId");
	    	 String personId	= request.getParameter("personId");
	    	 String receiptId	= request.getParameter("receiptId");
	    	 String productRecordNum = request.getParameter("productRecord");

	    	 String dispatchTo = "/receipt.jsp";
	    	 em.getTransaction().begin();
	    	 
	    	 if (command != null && command.equals("addRecord") && productId != null && personId != null && (!personId.equals(null))) {
	    		 Product product = em.find(Product.class, Integer.valueOf(productId));
	    		 if (receiptId != null && (!receiptId.equals("null"))) {
	    			 System.out.println("addrecord entered receiptId" + receiptId);
	    			 Receipt receipt = em.find(Receipt.class, Integer.valueOf(receiptId));
	    			 ProductRecord productRecord = new ProductRecord();
	    			 productRecord.setProduct(product);
	    			 productRecord.setQuantity(1);
	    			 productRecord.setReceipt(receipt);
	    			 List<ProductRecord> recordList = receipt.getProductRecord();
	    			 recordList.add(productRecord);
	    		 } else {
	    			 ProductRecord productRecord = new ProductRecord();
	    			 productRecord.setProduct(product);
	    			 productRecord.setQuantity(1);
	    			 List<ProductRecord> recordList = new ArrayList<ProductRecord>();
	    			 recordList.add(productRecord);
	    			 Receipt receipt = new Receipt();
	    			 receipt.setProductRecord(recordList);
	    			 receipt.setDateOfReceipt(new Date(System.currentTimeMillis()));
	    			 Buyer buyer = em.find(Buyer.class, Integer.parseInt(personId));
	    			 receipt.setBuyer(buyer);
	    			 List<Receipt> receiptList = new ArrayList<Receipt>();
	    			 receiptList.add(receipt);
	    			 em.persist(receipt);
	    			 int receiptIdInt = receipt.getId();
	    			 receiptId = String.valueOf(receipt.getId());
	    			 System.out.println("receiptIdInt " + receiptIdInt);
	    			 System.out.println("receipt.getId() " + receipt.getId());
	    			 System.out.println("receiptId " + receiptId);
	    		 } 
	    		 request.setAttribute("receiptId", receiptId);
				 request.setAttribute("personId", personId);
				 System.out.println("ReceiptServlet receiptId " + receiptId);
				 dispatchTo = "/receipt.jsp";
	    	 } else if (command != null && command.equals("removeRecord") && (!personId.equals(null)) && productRecordNum != null && receiptId != null) {
	    		 Receipt receipt = em.find(Receipt.class, Integer.parseInt(receiptId));
	    		 int index = Integer.valueOf(productRecordNum);
	    		 ProductRecord productRecord = receipt.getProductRecord().get(index);
	    		 if (productRecord != null) {
	    			 receipt.getProductRecord().remove(productRecord); 
	    			 em.remove(productRecord);
	    		 }
	    		 request.setAttribute("personId", personId);
				 request.setAttribute("receiptId", receiptId);
				 dispatchTo = "/receipt.jsp";
	    	 }
	    	 em.getTransaction().commit();
	    	 request.getRequestDispatcher(dispatchTo).forward(request, response);
	     } finally {
			 if (em.getTransaction().isActive())
				 em.getTransaction().rollback();
			 em.close();
		 }
	 }
	 
	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doGet(request, response);
	}
}
