<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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
    	<div class="logo">
    		<a href="welcome.jsp">
    		<img src="images/logo.gif" alt="" title="" border="0" /></a>
    	</div>
    	<div id="menu">
    	<ul>
    		<li><a href="index.jsp">home</a></li>
        	<li><a href="about.jsp">about us</a></li>
        	<li><a href="productList.jsp">books</a></li>
        	<li class="selected"><a href="specials.jsp">cart</a></li>
        	<li><a href="myaccount.jsp">my accout</a></li>
        	<li><a href="register.jsp">register</a></li>
        	<li><a href="details.jsp">prices</a></li>
        	<li><a href="contact.jsp">contact</a></li>
		</ul>
    	</div>
  	</div>
	
    <div class="center_content">
    	<div class="left_content">
    	<div class="crumb_nav"><a href="welcome.jsp">home</a> &gt;&gt; cart </div>
    	
     	<%
      	EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
      	EntityManager em = emf.createEntityManager();
      	try{
      		em.getTransaction().begin();
      		String personId 	= (String) request.getAttribute("personId");
      		String receiptId	= (String) request.getAttribute("receiptId");
      		
      		float totalPrice = 0;
      		Receipt receipt = em.find(Receipt.class, Integer.parseInt(receiptId));
      		List<ProductRecord> productRecordList	= receipt.getProductRecord();
      		int productRecordSize = productRecordList.size();
      	%>
      	
      	<div class="feat_prod_box_details">
        	<div class="title">
        		<span class="title_icon">My cart</span>
        		<img src="images/bullet1.gif" alt="" title="" />
        	</div>
        	<table class="cart_table">
          		<tr class="cart_title">
          			<td>Remove</td>
            		<td>Item pic</td>
            		<td>Book name</td>
            		<td>Unit price</td>
            		<td>Qty</td>
            		<td>Total</td>
            	</tr>
          	<%
          	for (int i=0; i<productRecordSize; i++) {
        	  	totalPrice +=  Float.valueOf(productRecordList.get(i).getProduct().getPrice());
          	%>
          
          		<tr>
           			<td>
           				<a href="ReceiptServlet?command=removeRecord&personId=<%=personId%>&productRecord=<%=i%>&receiptId=<%=receiptId%>">remove</a>
           			</td>
            		<td>
            			<a href="details.jsp"><img src="images/cart_thumb.gif" alt="" title="" border="0" class="cart_thumb" /></a>
            		</td>
            		<td><%=productRecordList.get(i).getProduct().getName()%></td>
            		<td><%=productRecordList.get(i).getProduct().getPrice()%> $</td>
            		<td><%=productRecordList.get(i).getQuantity()%></td>
            		<td><%=(productRecordList.get(i).getProduct().getPrice())*(productRecordList.get(i).getQuantity())%> $</td>
          		</tr>
          	<%
          	}
          	%>
          
           		<tr>
            		<td colspan="5" class="cart_total">
            			<span class="red">TOTAL SHIPPING:</span>
            		</td>
            		<td> 10$</td>
          		</tr>
          		<tr>
            		<td colspan="5" class="cart_total"><span class="red">TOTAL:</span></td>
            		<td><%=totalPrice + 10 %></td>
          		</tr>
        	</table>
        		<a href="ProductServlet?command=continueShop&personId=<%=personId %>&receiptId=<%=receiptId %>" class="continue">&lt; continue</a> 
        		<a href="#" class="checkout">&gt; check out</a>
        		<div class="clear"></div>
      		</div>
      		<div class="clear"></div>
		</div>
    	<!--end of left content--> 
	
		<div class="right_content">
			<div class="title">
				<span class="title_icon">About Our Store</span>
				<img src="images/bullet3.gif" alt="" title="" />
			</div>
      		<div class="about">
        		<p> 
        			<img src="images/about.gif" alt="" title="" class="right" /> 
        			Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed 
        			do eiusmod tempor incididunt ut labore et dolore magna aliqua. 
        			Ut enim ad minim veniam, quis nostrud.
        		</p>
      		</div>
      		<div class="clear"></div>

        	<%
          		em.getTransaction().commit();    
      		} finally {
        	 	if(em.getTransaction().isActive())
        		 	em.getTransaction().rollback();
       	 	}
        	%>
    		<!--end of right content-->
		
			<div class="clear"></div>
		</div>
    	<div class="clear"></div>
	</div>
  
  	<!--end of center content-->
  	<div class="footer">
    	<div class="left_footer">
    		<img src="images/footer_logo.gif" alt="" title="" /><br />
      	</div>
    	<div class="right_footer"> 
    		<a href="#welcome.jsp">home</a> 
    		<a href="about.jsp">about us</a> 
    		<a href="#">services</a> 
    		<a href="#">privacy policy</a> 
    		<a href="contact.jsp">contact us</a> 
    	</div>
    </div>
</div>
</body>
</html>

