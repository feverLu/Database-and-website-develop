<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.*,cs5200.jpa.*,cs5200.server.*" %>
    <%@page import="javax.persistence.EntityManager, javax.persistence.EntityManagerFactory, javax.persistence.TypedQuery"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252" />
<title>Book Store</title>
<link rel="stylesheet" type="text/css" href="style.css" />
</head>
<body>
<div id="wrap">
  <div class="header">
    <div class="logo"><a href="index.htm"><img src="images/logo.gif" alt="" title="" border="0" /></a></div>
    <div id="menu">
      <ul>
        <li><a href="index.htm">home</a></li>
        <li><a href="about.htm">about us</a></li>
        <li><a href="category.htm">books</a></li>
        <li><a href="specials.htm">specials books</a></li>
        <li><a href="register.htm">log in</a></li>
         <li class="selected"><a href="myaccount.htm">account</a></li>
        <li><a href="details.htm">user list</a></li>
        <li><a href="contact.htm">contact</a></li>
      </ul>
    </div>
  </div>



	  <div class="center_content">
    <div class="left_content">
      <div class="title"><span class="title_icon"><img src="images/bullet1.gif" alt="" title="" /></span>Account</div>
      <div class="feat_prod_box_details">
      <p class="details"> Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud. Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud. </p>
      <%
      EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
      EntityManager em = emf.createEntityManager();
      try{
      	em.getTransaction().begin();
      	String buyerId = request.getParameter("buyerId");
      	System.out.println("buyerAccount buyerId " + buyerId);
      	if (buyerId !=null) {
      		Buyer buyer = em.find(Buyer.class, Integer.parseInt(buyerId));
      		System.out.println("buyerAccount buyer " + buyer);
		if (buyer != null) {
			List<CreditCard> creditcardList = buyer.getCreditCard();
			List<Address> addressList = buyer.getAddress();
			List<Review> reviewList = buyer.getReview();
			List<Receipt> receiptList = em.createNativeQuery("SELECT * FROM Receipt", Receipt.class).getResultList();
		%>
		
		<div class="my_contact_form">	
        <div class="form_subtitle">bank account</div>
		<div class="form_row">
		<lable><%=buyer.getFirstName()%>&nbsp &nbsp<%=buyer.getLastName() %> &nbsp &nbsp<%=buyer.getLogName() %>&nbsp &nbsp<%=buyer.getPasscode() %></lable>&nbsp &nbsp
		<a href="AdminServlet?command=buyerInfo&buyerId=<%=buyer.getId() %>">update</a>
        &nbsp &nbsp 
        <div class="right_float">
        </div>
		</div>
		</div>
		
		<div class="my_contact_form">	
        <div class="form_subtitle">bank account</div>
        <form name="register" method="post" action="LogInServlet">
		<%	for (CreditCard creditcard : creditcardList) {
		%>
		<div class="form_row">
		<lable><%=creditcard.getCardType() %>&nbsp &nbsp<%=creditcard.getExpiryDate() %></lable>&nbsp &nbsp<a href="AdminServlet?command=buyerCreditcard&buyerId=<%=buyer.getId() %>&creditcardId=<%=creditcard.getId() %>"> <%=creditcard.getNumber()%></a>
        &nbsp &nbsp 
        <div class="right_float">
        <a href="LogInServlet?command=deleteCreditcard&buyerId=<%=buyer.getId() %>&creditcardId=<%=creditcard.getId() %>">delete</a>
        </div>
		</div>
		<%
			}%>
		</form>
		</div>
		<div class="my_contact_form">	
        <div class="form_subtitle">address</div>
        <form name="register" method="post" action="LogInServlet">
		<%	for (Address address: addressList) {
		%>
		<div class="form_row">
		<label><%=address.getStreetNumber()%>&nbsp<%=address.getStreet()%>
		 &nbsp<%=address.getCity()%>&nbsp<%=address.getSate()%>&nbsp<%=address.getPostcode()%></label>
		 <div class="right_float">
		<a href="AdminServlet?command=buyerAddress&buyerId=<%=buyerId %>&addressId=<%=address.getId()%>">update</a>
		</div>
        &nbsp &nbsp &nbsp &nbsp
        <div class="right_float">
        <a href="LogInServlet?command=deleteAddress&buyerId=<%=buyer.getId() %>&addressId=<%=address.getId() %>">delete</a>
        </div>
		</div>
		<%
			}%>
		</form>
		</div>	
		
		<div class="my_contact_form">	
        <div class="form_subtitle">reviews</div>
        <form name="register" method="post" action="LogInServlet">
		<%	for (Review review: reviewList) {
		%>
		<div class="form_row">
		<label>star: <%=review.getStar()%>&nbsp<%=review.getReview()%>
		 &nbsp<%=review.getDateOfReview()%>&nbsp   product: <a href="AdminServlet?command=readProduct&productId=<%=review.getProduct().getId()%>"><%=review.getProduct().getName()%></a></label>
        <div class="right_float">
        <a href="AdminServlet?command=deleteReview&buyerId=<%=buyer.getId() %>&reviewId=<%=review.getId() %>&productId=<%=review.getProduct().getId()%>">delete</a>
        </div>
		</div>
		<%
			}%>
		</form>
		</div>	
		
		<div class="my_contact_form">	
        <div class="form_subtitle">receipts</div>
        <form name="register" method="post" action="AdminServlet">
		<%	for (Receipt receipt: receiptList) {
			if (receipt.getBuyer().equals(buyer)) {
		%>
		<div class="form_row">
		<a href="AdminServlet?command=readReceipt&buyerId=<%=buyer.getId() %>&receiptId=<%=receipt.getId()%>"><%=receipt.getId()%></a>
        <div class="right_float">
        <a href="AdminServlet?command=deleteReceipt&buyerId=<%=buyer.getId() %>&receiptId=<%=receipt.getId() %>">delete</a>
        </div>
		</div>
		<%
			}
			}%>
		</form>
		</div>	
		
		
		<%
		}
      	}else {
			System.out.println("buyer is null");
		}
		%>
      </div>
      <div class="clear"></div>
    </div>
    <!--end of left content-->

	<div class="right_content">
        <div class="contact_form">
          <div class="form_subtitle">add a new creditcard</div>
          <form name="register" method="post" action="LogInServlet">
        		<div class="form_row">
        		<label class="contact"><strong>Number:</strong></label>
        		<input type="text" name="creditcardNumber" />
            	</div>
           		<div class="form_row">
        		<label class="contact"><strong>Card Type:</strong></label>
        		<input type="text" name="creditcardType" />
            	</div>
            	<div class="form_row">
        		<label class="contact"><strong>Expiry Date:</strong></label>
        		<input type="text" name="creditcardExpiryDate" />
            	</div>
           		<div class="form_row">
            	<input type="submit" class="register" value="add" >
            	<input type="hidden" name="command" value="createCreditcard" >
            	<input type="hidden" name="buyerId" value=<%=buyerId %> >
            	</div>
            	 </form>
            	 </div>
            	 <div class="contact_form">
            	 
          <div class="form_subtitle">add a new address</div>
          <form name="register" method="post" action="LogInServlet">
        		<div class="form_row">
        		<label class="contact"><strong>Street Number:</strong></label>
        		<input type="text" name="streetNumber" />
            	</div>
           		<div class="form_row">
        		<label class="contact"><strong>Street:</strong></label>
        		<input type="text" name="street" />
            	</div>
            	<div class="form_row">
        		<label class="contact"><strong>City:</strong></label>
        		<input type="text" name="city" />
            	</div>
            	<div class="form_row">
        		<label class="contact"><strong>Sate:</strong></label>
        		<input type="text" name="state" />
            	</div>
            	<div class="form_row">
        		<label class="contact"><strong>Postcode:</strong></label>
        		<input type="text" name="postcode" />
            	</div>
           		<div class="form_row">
            	<input type="submit" class="register" value="add" />
            	<input type="hidden" name="command" value="createAddress" />
            	<input type="hidden" name="buyerId" value=<%=buyerId %> />
            	</div>
            	 </form>
            	 </div>
		<%
		em.getTransaction().commit();
      } finally {
    	    if (em.getTransaction().isActive())
				em.getTransaction().rollback();
			em.close();
      }
		%>
      
      <div class="clear"></div>
    </div>
    <!--end of right content-->
    <div class="clear"></div>
  </div>
  
 <!--end of center content-->
  <div class="footer">
    <div class="left_footer"><img src="images/footer_logo.gif" alt="" title="" /><br />
     </div>
     </div>
    <div class="right_footer"> <a href="#">home</a> <a href="#">about us</a> <a href="#">services</a> <a href="#">privacy policy</a> <a href="#">contact us</a> </div>
  </div>
</body>
</html>