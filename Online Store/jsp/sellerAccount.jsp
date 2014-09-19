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
      <div class="title"><span class="title_icon"><img src="images/bullet1.gif" alt="" title="" /></span>Seller Account</div>
      <div class="feat_prod_box_details">
      <p class="details"> Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud. Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud. </p>
      <%
      EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
      EntityManager em = emf.createEntityManager();
      try{
      	em.getTransaction().begin();
      	String sellerId = request.getParameter("sellerId");
      	System.out.println("sellerAccount entered sellerId " +sellerId);
      	Seller seller = em.find(Seller.class, Integer.parseInt(sellerId));
  	  
		if (seller != null) {
			List<BankAccount> bankAccounts = seller.getBankAccount();
			List<Product> products = em.createNativeQuery("select * from Product", Product.class).getResultList();
		%>
		
		<div class="my_contact_form">	
        <div class="form_subtitle">information</div>
		<div class="form_row">
		<lable><%=seller.getFirstName()%>&nbsp &nbsp<%=seller.getLastName() %> &nbsp &nbsp<%=seller.getLogName() %>&nbsp &nbsp<%=seller.getPasscode() %></lable>&nbsp &nbsp
		<a href="AdminServlet?command=sellerInfo&sellerId=<%=sellerId %>">update</a>
        &nbsp &nbsp 
        <div class="right_float">
        </div>
		</div>
		</div>
		
		<div class="my_contact_form">	
        <div class="form_subtitle">bank account</div>
        <form name="register" method="post" action="LogInServlet">
		<%	for (BankAccount bankAccount : bankAccounts) {
		%>
		<div class="form_row">
		<a href="AdminServlet?command=sellerAccount&sellerId=<%=sellerId %>&sellerAccountId=<%=bankAccount.getId()%>"><%=bankAccount.getNumber()%></a>
        &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp 
        <a href="LogInServlet?command=deleteAccount&sellerId=<%=seller.getId() %>&sellerAccountId=<%=bankAccount.getId() %>">delete</a>
		</div>
		<%
			}%>
		</form>
		</div>	
		
		<div class="my_contact_form">	
        <div class="form_subtitle">product</div>
        <form name="register" method="post" action="AdminServlet">
		<%	for (Product product : products) {
			if (product.getSeller().equals(seller)) {
		%>
		<div class="form_row">
		<a href="AdminServlet?command=readProduct&productId=<%=product.getId() %>&sellerId=<%=seller.getId()%>"><%=product.getName()%></a>
		</div>
		<%
			}
			}%>
		</form>
		</div>
		
		
		<%
		} else {
			System.out.println("seller is null");
		}
		%>
      </div>
      <div class="clear"></div>
    </div>
    
    
    
    <!--end of left content-->



	<div class="right_content">
	
        <div class="contact_form">
          <div class="form_subtitle">add a new account</div>
          
          <form name="register" method="post" action="LogInServlet">
        		<div class="form_row">
        		<label class="contact"><strong>Number:</strong></label>
        		<input type="text" name="accountNumber" />
            	</div>
           		<div class="form_row">
        		<label class="contact"><strong>Amount:</strong></label>
        		<input type="text" name="accountAmount" />
            	</div>
            	<div class="form_row">
        		<label class="contact"><strong>Routing:</strong></label>
        		<input type="text" name="accountRouting" />
            	</div>
           		<div class="form_row">
            	<input type="submit" class="register" value="add" />
            	<input type="hidden" name="command" value="createAccount" />
            	<input type="hidden" name="sellerId" value=<%=sellerId %> />
            	</div>
            	 </form>
            	 
		<%
		em.getTransaction().commit();
      } finally {
    	    if (em.getTransaction().isActive())
				em.getTransaction().rollback();
			em.close();
      }
		%>
        </div>
         <div class="title"><span class="title_icon"><img src="images/bullet3.gif" alt="" title="" /></span>About Our Store</div>
      <div class="about">
        <p> <img src="images/about.gif" alt="" title="" class="right" /> Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud. </p>
      </div>
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