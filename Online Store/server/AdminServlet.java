package cs5200.server;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cs5200.jpa.Address;
import cs5200.jpa.BankAccount;
import cs5200.jpa.Buyer;
import cs5200.jpa.CardType;
import cs5200.jpa.CreditCard;
import cs5200.jpa.Product;
import cs5200.jpa.ProductRecord;
import cs5200.jpa.Receipt;
import cs5200.jpa.Review;
import cs5200.jpa.Seller;

public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public AdminServlet() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 EntityManagerFactory emf = (EntityManagerFactory)getServletContext().getAttribute("emf");
	     EntityManager em  = emf.createEntityManager();
	     try {
	    	 String command = request.getParameter("command");
	    	 String buyerId = request.getParameter("buyerId");
	    	 String creditcardId = request.getParameter("creditcardId");
	    	 String addressId = request.getParameter("addressId");
	    	 String creditcardNumber = request.getParameter("creditcardNumber");
	    	 String creditcardType = request.getParameter("creditcardType");
	    	 String creditcardExpiryDate = request.getParameter("creditcardExpiryDate");
	    	 String streetNumber = request.getParameter("streetNumber");
	    	 String street = request.getParameter("street");
	    	 String city = request.getParameter("city");
	    	 String state = request.getParameter("state");
	    	 String postcode = request.getParameter("postcode");
	    	 String productId = request.getParameter("productId");
	    	 String reviewId = request.getParameter("reviewId");
	    	 String sellerId = request.getParameter("sellerId");
	    	 String sellerAccountId = request.getParameter("sellerAccountId");
	    	 String sellerAccountNumber = request.getParameter("sellerAccountNumber");
	    	 String sellerAccountAmount = request.getParameter("sellerAccountAmount");
	    	 String sellerAccountRouting = request.getParameter("sellerAccountRouting");
	    	 String firstName = request.getParameter("firstName");
	    	 String lastName = request.getParameter("lastName");
	    	 String logName = request.getParameter("logName");
	    	 String passcode = request.getParameter("passcode");
	    	 String receiptId = request.getParameter("receiptId");
	    	 
	    	 System.out.println("admin buyerId " +buyerId);
	    	 System.out.println("admin sellerId " +sellerId);
	    	 
	    	 em.getTransaction().begin();
	    	 String dispatchTo="/userList.jsp";
	    	 
	    	 if (command != null && command.equals("buyerCreditcard") && creditcardId != null && (!buyerId.equals("null"))) {
	    		 request.setAttribute("creditcardId", creditcardId);
	    		 request.setAttribute("buyerId", buyerId);
	    		 dispatchTo = "/updateBuyerAccount.jsp";
	    	 } else if (command != null && command.equals("sellerAccount") && sellerAccountId !=null && (!sellerId.equals("null"))) {
	    		 request.setAttribute("sellerAccountId", sellerAccountId);
	    		 request.setAttribute("sellerId", sellerId);
	    		 dispatchTo = "/updateSellerAccount.jsp";
	    	 } else if (command != null && command .equals("buyerInfo") && (!buyerId.equals("null"))) {
	    		 request.setAttribute("buyerId", buyerId);
	    		 System.out.println("buyerInfo entered buyerId " + buyerId);
	    		 dispatchTo = "/updateBuyerInfo.jsp";
	    	 } else if (command != null && command .equals("sellerInfo") && (!sellerId.equals("null"))) {
	    		 request.setAttribute("sellerId", sellerId);
	    		 dispatchTo = "/updateSellerInfo.jsp";
	    	 }
	    	 else if (command != null && command.equals("updateBuyerInfo") && (!buyerId.equals("null"))) {
	    		 Buyer buyer = em.find(Buyer.class, Integer.parseInt(buyerId));
	    		 buyer.setFirstName(firstName);
	    		 buyer.setLastName(lastName);
	    		 buyer.setLogName(logName);
	    		 buyer.setPasscode(passcode);
	    		 request.setAttribute("buyerId", buyerId);
	    		 dispatchTo = "/buyerAccount.jsp";
	    	 } else if (command != null && command.equals("updateSellerInfo") && (!sellerId.equals("null"))) {
	    		 Seller seller = em.find(Seller.class, Integer.parseInt(sellerId));
	    		 seller.setFirstName(firstName);
	    		 seller.setLastName(lastName);
	    		 seller.setLogName(logName);
	    		 seller.setPasscode(passcode);
	    		 request.setAttribute("sellerId", sellerId);
	    		 dispatchTo = "/sellerAccount.jsp";
	    	 }
	    	 
	    	 else if (command != null && command.equals("updateCreditcard") && buyerId !=null && creditcardId != null) {
	    		 CreditCard creditcard = em.find(CreditCard.class, Integer.parseInt(creditcardId));
	    		 creditcard.setNumber(creditcardNumber);
	    		 if (creditcardType != null) {
	    			 creditcard.setCardType(CardType.valueOf(creditcardType));
	    		 }
	    		 if (creditcardExpiryDate != null) {
	    			 creditcard.setExpiryDate(Date.valueOf(creditcardExpiryDate));
	    		 }
	    		 request.setAttribute("buyerId", buyerId);
	    		 dispatchTo = "/buyerAccount.jsp";
	    	 } else if (command != null && command.equals("updateSellerAccount") && sellerAccountId !=null && (!sellerId.equals("null"))) {
	    		 System.out.println("updateSellerAccount sellerAccoutId" + sellerAccountId);
	    		 System.out.println("updateSellerAccount sellerId" + sellerId);
	    		 BankAccount bankAccount = em.find(BankAccount.class, Integer.parseInt(sellerAccountId));
	    		 bankAccount.setNumber(sellerAccountNumber);
	    		 if(sellerAccountAmount != null) {
	    			 bankAccount.setAmount(Float.valueOf(sellerAccountAmount));
	    		 }
	    		 bankAccount.setRouting(sellerAccountRouting);
	    		 request.setAttribute("sellerId", sellerId);
	    		 dispatchTo = "/sellerAccount.jsp";
	    	 }  else if (command != null && command.equals("buyerAddress") && buyerId !=null && addressId != null) {
	    		 request.setAttribute("addressId", addressId);
	    		 request.setAttribute("buyerId", buyerId);
	    		 dispatchTo = "/updateBuyerAddress.jsp";
	    	 } else if(command != null && command.equals("updateAddress") && buyerId != null && addressId != null) {
	    		 System.out.println("updateAddress entered ");
	    		 Address address = em.find(Address.class, Integer.parseInt(addressId));
	    		 if (streetNumber != null) {
	    			 address.setStreetNumber(Integer.parseInt(streetNumber));
	    		 }
	    		 address.setStreet(street);
	    		 address.setCity(city);
	    		 address.setState(state);
	    		 address.setPostCode(postcode);
	    		 System.out.println("buyerId updateAddress " + buyerId);
	    		 request.setAttribute("buyerId", buyerId);
	    		 dispatchTo="/buyerAccount.jsp";
	    	 } else if(command != null && command.equals("deleteReview") && productId != null && reviewId != null && (!buyerId.equals("null"))) {
	                Product product = em.find(Product.class, Integer.parseInt(productId));
	                Buyer buyer = em.find(Buyer.class, Integer.parseInt(buyerId));
	                System.out.println("deleteReview!!!!product" + product);
	                if(reviewId != null) {
		            	Review review = em.find(Review.class, Integer.parseInt(reviewId));
		            	System.out.println("deleteReview!!!!review" + review);
		            	if(review != null) {
			                product.getReview().remove(review);
			                buyer.getReview().remove(review);
			                em.remove(review);
		            	}
	                }
	                request.setAttribute("buyerId", buyerId);
	                request.setAttribute("productId", productId);
	                dispatchTo = "/buyerAccount.jsp";
	    	 } else if (command != null && command.equals("deleteReceipt") && receiptId != null && (!buyerId.equals("null"))) {
	    		 Receipt receipt = em.find(Receipt.class, Integer.parseInt(receiptId));
	    		 Buyer buyer = em.find(Buyer.class, Integer.parseInt(buyerId));
	    		 if (receipt != null && buyer != null) {
	    			 buyer.getReceipt().remove(receipt);
//	    			 List<ProductRecord> productRecordList = receipt.getProductRecord();
//	    			 for (ProductRecord productRecord : productRecordList) {
//	    				 em.remove(productRecord);
//	    			 }
	    			 em.remove(receipt);
	    		 }
	    		 request.setAttribute("buyerId", buyerId);
	             dispatchTo = "/buyerAccount.jsp";
	    	 } else if(command != null && command.equals("readProduct") && productId != null) {
//	             Product product = em.find(Product.class, Integer.parseInt(id));
	             request.setAttribute("productId", productId);
	             dispatchTo = "/adminProductDetail.jsp";
	    	 } else if (command != null && command.equals("readReceipt") && receiptId != null && (!buyerId.equals("null"))) {
	    		 request.setAttribute(receiptId, receiptId);
	    		 dispatchTo = "/adminReceiptDetail.jsp";
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

