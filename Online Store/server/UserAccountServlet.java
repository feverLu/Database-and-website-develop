package cs5200.server;

import java.io.IOException;
import java.sql.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cs5200.jpa.Address;
import cs5200.jpa.BankAccount;
import cs5200.jpa.Buyer;
import cs5200.jpa.CardType;
import cs5200.jpa.CreditCard;
import cs5200.jpa.Seller;

public class UserAccountServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public UserAccountServlet() {
		super();
	}
	
	 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 EntityManagerFactory emf = (EntityManagerFactory)getServletContext().getAttribute("emf");
	     EntityManager em  = emf.createEntityManager();
	     
	     try {
			 String command		= request.getParameter("command");
			 String buyerId 	= request.getParameter("buyerId");
			 String sellerId 	= request.getParameter("sellerId");
			 String sellerAccountId		= request.getParameter("sellerAccountId");
			 String sellerAccountNumber	= request.getParameter("accountNumber");
			 String sellerAccountAmount	= request.getParameter("accountAmount");
			 String sellerAccountRouting= request.getParameter("accountRouting");
			 String buyerCreditNumber	= request.getParameter("creditcardNumber");
			 String buyerCreditType		= request.getParameter("creditcardType");
			 String buyerCreditExDate	= request.getParameter("creditcardExpiryDate");
			 String buyerCreditId		= request.getParameter("creditcardId");
			 String buyerStreetNumber	= request.getParameter("streetNumber");
			 String buyerStreet			= request.getParameter("street");
			 String buyerCity			= request.getParameter("city");
			 String buyerState			= request.getParameter("state");
			 String buyerPostcode		= request.getParameter("postcode");
			 String buyerAddressId		= request.getParameter("addressId");
			 
			 String dispatchTo = null;
			 em.getTransaction().begin();
			 
			 if (command != null && command.equals("sellerAccount") && sellerId != null) {
				 // Direct to the seller's account, which contains seller's bank account
				 Seller seller = em.find(Seller.class, Integer.parseInt(sellerId));
				 if (seller != null) {
					 request.setAttribute("sellerId", sellerId);
					 dispatchTo = "/sellerAccount.jsp";
				 }
			 } else if (command != null && command.equals("buyerAccount") && buyerId != null) {
				 // Direct to the buyer's account, which contains the buyer's address and credit card information
				 Buyer buyer = em.find(Buyer.class, Integer.parseInt(buyerId));
				 if (buyer != null) {
					 request.setAttribute("buyerId", buyerId);
					 dispatchTo = "/buyerAccount.jsp";
				 }
			 } else if (command != null && command.equals("createAccount") && sellerId != null) {
				 // Create a new bank account associated with seller
				 Seller seller = em.find(Seller.class, Integer.parseInt(sellerId));
				 BankAccount newBankAccount = new BankAccount();
				 newBankAccount.setNumber(sellerAccountNumber);
				 newBankAccount.setRouting(sellerAccountRouting);
				 if (sellerAccountAmount != null) {
					 newBankAccount.setAmount(Float.valueOf(sellerAccountAmount));
				 }
				 
				 sellerAccountId = newBankAccount.getId() + "";
				 if (sellerAccountId != null) {
					 if (newBankAccount != null) {
						 seller.getBankAccount().add(newBankAccount);
						 newBankAccount.setSeller(seller);
					 }
				 }
				 request.setAttribute("sellerId", sellerId);
				 dispatchTo = "/sellerAccount.jsp";
			 } else if (command != null && command.equals("createCreditcard") && buyerId != null) {
				 // Create a new credit card associated with the buyer
				 Buyer buyer = em.find(Buyer.class, Integer.parseInt(buyerId));
				 CreditCard newCreditcard = new CreditCard();
				 newCreditcard.setNumber(buyerCreditNumber);
				 newCreditcard.setCardType(CardType.valueOf(buyerCreditType));
				 newCreditcard.setExpiryDate(Date.valueOf(buyerCreditExDate));
				 buyerCreditId = newCreditcard.getId() + "";
				 if (buyerCreditId != null) {
					 if (newCreditcard != null) {
						 buyer.getCreditCard().add(newCreditcard);
						 newCreditcard.setBuyer(buyer);
					 }
				 }
				 request.setAttribute("buyerId", buyerId);
				 dispatchTo = "/buyerAccount.jsp";
			 } else if (command != null && command.equals("createAddress") && buyerId != null) {
				 // Create a new address associated to the buyer
				 Buyer buyer = em.find(Buyer.class, Integer.parseInt(buyerId));
				 Address newAddress = new Address();
				 newAddress.setStreetNumber(Integer.parseInt(buyerStreetNumber));
				 newAddress.setStreet(buyerStreet);
				 newAddress.setCity(buyerCity);
				 newAddress.setState(buyerState);
				 newAddress.setPostCode(buyerPostcode);
				 buyerAddressId = newAddress.getId() + "";
				 if (buyerAddressId != null) {
					 if (newAddress != null) {
						 buyer.getAddress().add(newAddress);
						 newAddress.setBuyer(buyer);
					 }
				 }
				 request.setAttribute("buyerId", buyerId);
				 dispatchTo = "/buyerAccount.jsp";
			 } else if (command != null && command.equals("deleteAccount") && sellerId != null && sellerAccountId != null){
				 // Delete the bank account associated to the seller
				 Seller seller = em.find(Seller.class, Integer.parseInt(sellerId));
				 if (sellerAccountId != null) {
					 BankAccount bankAccount = em.find(BankAccount.class, Integer.parseInt(sellerAccountId));
					 if (bankAccount != null) {
						 seller.getBankAccount().remove(bankAccount);
						 em.remove(bankAccount);
					 }
				 }
				 request.setAttribute("sellerId", sellerId);
				 dispatchTo = "/sellerAccount.jsp";
			 } else if (command != null && command.equals("deleteCreditcard") && buyerId != null && buyerCreditId != null) {
				 // Delete the credit card associated to the buyer
				 System.out.println("deleteCreditcard entered");
				 Buyer buyer = em.find(Buyer.class, Integer.parseInt(buyerId));
				 if (buyerCreditId != null) {
					 CreditCard creditCard = em.find(CreditCard.class, Integer.parseInt(buyerCreditId));
					 if (creditCard != null) {
						 buyer.getCreditCard().remove(creditCard);
						 em.remove(creditCard);
					 }
				 }
				 request.setAttribute("buyerId", buyerId);
				 dispatchTo = "/buyerAccount.jsp";
			 } else if (command != null && command.equals("deleteAddress") && buyerId != null && buyerAddressId != null) {
				 // Delete the address associated to the buyer
				 Buyer buyer = em.find(Buyer.class, Integer.parseInt(buyerId));
				 if (buyerAddressId != null) {
					 Address address = em.find(Address.class, Integer.parseInt(buyerAddressId));
					 if (address != null) {
						 buyer.getAddress().remove(address);
						 em.remove(address);
					 }
				 }
				 request.setAttribute("buyerId", buyerId);
				 dispatchTo = "/buyerAccount.jsp";
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
