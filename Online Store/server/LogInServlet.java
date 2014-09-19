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

import cs5200.jpa.Administrator;
import cs5200.jpa.BankAccount;
import cs5200.jpa.Buyer;
import cs5200.jpa.CardType;
import cs5200.jpa.CreditCard;
import cs5200.jpa.Person;
import cs5200.jpa.Seller;
import cs5200.jpa.Address;

public class LogInServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public LogInServlet() {
		super();
	}
	 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 EntityManagerFactory emf = (EntityManagerFactory)getServletContext().getAttribute("emf");
	     EntityManager em  = emf.createEntityManager();
		 
	     try {
			 String command		= request.getParameter("command");
			 String buyerId 	= request.getParameter("buyerId");
			 String sellerId 	= request.getParameter("sellerId");
			 String firstName	= request.getParameter("firstName");
			 String lastName	= request.getParameter("lastName");
			 String logName		= request.getParameter("logName");
			 String passcode	= request.getParameter("passcode");
			 String dateOfBirth	= request.getParameter("dateOfBirth");
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
			 
			 if (command != null && command.equals("createDBA") && logName != null && passcode != null && firstName != null && lastName != null) {
				//Create DBA can only be executed once
				 Administrator dba = new Administrator(firstName, lastName, logName, passcode);
				 if (dateOfBirth != null) {
					 dba.setDateOfBirth(Date.valueOf(dateOfBirth));
				 }
				 em.persist(dba);
				 dispatchTo = "/logIn.jsp";
			 } else if (command != null && command.equals("createBuyer") && logName != null && passcode != null && firstName != null && lastName != null) {
				 // Create a new buyer, first name, last name, log name, password are not null
				 Buyer buyer = new Buyer(firstName, lastName, logName, passcode);
				 if (dateOfBirth != null) {
					 buyer.setDateOfBirth(Date.valueOf(dateOfBirth));
				 }
				 em.persist(buyer);
				 dispatchTo = "/logIn.jsp";
			 } else if (command != null && command.equals("createSeller") && logName != null && passcode != null && firstName != null && lastName != null) {
				// Create a new seller, first name, last name, log name, password are not null
				 Seller seller = new Seller(firstName, lastName, logName, passcode);
				 if (dateOfBirth != null) {
					 seller.setDateOfBirth(Date.valueOf(dateOfBirth));
				 }
				 em.persist(seller);
				 dispatchTo = "/logIn.jsp";
			 } else if (command != null && command.equals("deleteBuyer") && buyerId != null) {
				// delete a buyer, first name, last name, log name, password are not null
				 Buyer buyer = em.find(Buyer.class, Integer.parseInt(buyerId));
				 if (buyer != null) {
					 if (buyer.getAddress() != null) {
						 int sizeA = buyer.getAddress().size();
						 for (int i=0; i<sizeA; i++) {
							 em.remove(buyer.getAddress().get(i));
						 }
					 }
					 if (buyer.getCreditCard() != null) {
						 int sizeC = buyer.getCreditCard().size();
						 for (int j=0; j<sizeC; j++) {
							 em.remove(buyer.getCreditCard().get(j));
						 }
					 }
					 em.remove(buyer);
				 }
				 dispatchTo = "/userList.jsp";
			 } else if (command != null && command.equals("deleteSeller") && sellerId != null) {
				 Seller seller = em.find(Seller.class, Integer.parseInt(sellerId));
				 if (seller != null) {
					 // cascade remove seller's bank accounts
					 if (seller.getBankAccount() != null) {
						 int size = seller.getBankAccount().size();
						 for (int i=0; i<size; i++) {
							 em.remove(seller.getBankAccount().get(i));
						 }
					 }
					// cascade remove seller's products
					 if (seller.getProduct() != null) {
						 int size = seller.getProduct().size();
						 for (int i=0; i<size; i++) {
							 em.remove(seller.getProduct().size());
						 }
					 }
					 //remove the seller
					 em.remove(seller);
				 }
				 dispatchTo = "/userList.jsp";
			 }  else if (command != null && command.equals("sellerAccount") && sellerId != null) {
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
			 } else if (command != null && command.equals("login") && logName!=null && passcode!= null) {
				 // Check the typed log name and password whether there is a record in the database
				 TypedQuery<Person> personQuery = em.createNamedQuery("Person.findByLognamePasscode", Person.class);
			     personQuery.setParameter("logName", logName);
			     personQuery.setParameter("passcode", passcode);
			     List<Person> person = personQuery.getResultList();
			     if (!person.isEmpty()) {
			    	 request.setAttribute("personId", person.get(0).getId() + "");
			    	 dispatchTo = "/productList.jsp";
			     } else {
			         // The log name and password do not exist in the database, show an alert
			    	 JOptionPane.showMessageDialog(null, "No user found!", "Alert",JOptionPane.WARNING_MESSAGE);  
			     }
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
