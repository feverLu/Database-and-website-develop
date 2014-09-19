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
         <li><a href="myaccount.htm">account</a></li>
        <li class="selected"><a href="details.htm">user list</a></li>
        <li><a href="contact.htm">contact</a></li>
      </ul>
    </div>
  </div>

  <div class="center_content">
    <div class="my_right_content">
      <div class="title"><span class="title_icon"><img src="images/bullet1.gif" alt="" title="" /></span>The Administrator Form</div>
      <div class="feat_prod_box_details">
          <%
          EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
          EntityManager em = emf.createEntityManager();
          try{
        	  em.getTransaction().begin();
        	  TypedQuery<Buyer> buyerQuery = em.createNamedQuery("Buyer.findByAll", Buyer.class);
        	  List<Buyer> buyerList = buyerQuery.getResultList();
        	  System.out.println("userList entered");
        	  if(buyerList != null) { %>
        	  
        		  <div class="my_contact_form">	
             	 <div class="form_subtitle">buyers</div>
               <form name="register" method="post" action="LogInServlet">
        		<%  for (Buyer buyer : buyerList) {
        		%>
           			<div class="form_row">
           			<a href="LogInServlet?command=buyerAccount&buyerId=<%=buyer.getId() %>"><%=buyer.getLogName()%></a>
           			&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp 
          			<a href="LogInServlet?command=deleteBuyer&buyerId=<%=buyer.getId() %>">delete</a>
           			</div>
            	<%	  
        		  }
        		}%>
        		</form>
                </div>
        	  
        	  <%
        	  TypedQuery<Seller> sellerQuery = em.createNamedQuery("Seller.findByAll", Seller.class);
        	  List<Seller> sellerList = sellerQuery.getResultList();
        	  if (sellerList != null) {
        	  %>
        	  <div class="my_contact_form">	
          	 <div class="form_subtitle">sellers</div>
          	 <form name="register" method="post" action="LogInServlet">
     		<%  for (Seller seller : sellerList) {
     		%>
           <div class="form_row">
           <a href="LogInServlet?command=sellerAccount&sellerId=<%=seller.getId() %>"><%=seller.getLogName()%></a>
            &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp
           <a href="LogInServlet?command=deleteSeller&sellerId=<%=seller.getId() %>">delete</a>
           </div>
         	<%	  
     		  } %>
     		  </form>
             </div>
     	  <%
        	  }
        	  
        	  List<Product> productList = em.createNativeQuery("SELECT * FROM Product", Product.class).getResultList();
        	  if (productList != null) {
        	  %>
        	  <div class="my_contact_form">	
          	 <div class="form_subtitle">products</div>
          	 <form name="register" method="post" action="AdminServlet">
     		<%  for (Product product : productList) {
     		%>
           <div class="form_row">
           <a href="AdminServlet?command=readProduct&productId=<%=product.getId() %>"><%=product.getName()%></a>
           <%System.out.println("product "+ product); %>
           <div class="right_float"><a href="LogInServlet?command=sellerAccount&sellerId=<%=product.getSeller().getId()%>">
             <%=product.getSeller().getLogName() %></a></div>
           </div>
         	<%	  
     		  } %>
     		  </form>
             </div>
     	  <%
        	  }
        	  
        	  List<Review> reviewList = em.createNativeQuery("SELECT * FROM Review", Review.class).getResultList();
        	  if (reviewList != null) {
        	  %>
        	  <div class="my_contact_form">	
          	 <div class="form_subtitle">reviews</div>
          	 <form name="register" method="post" action="AdminServlet">
     		<%  for (Review review : reviewList) {
     		%>
           <div class="form_row">
           <a href="LogInServlet?command=buyerAccount&buyerId=<%=review.getBuyer().getId() %>"><%=review.getBuyer().getLogName()%></a>
           star: <%=review.getStar()%>   <%=review.getReview()%>    <%=review.getDateOfReview()%>
           <a href="AdminServlet?command=readProduct&productId=<%=review.getProduct().getId() %>"><%=review.getProduct().getName()%></a>
           <div class="right_float"><a href="AdminServlet?command=deleteReview&reviewId=<%=review.getId()%>&buyerId=<%=review.getBuyer().getId()%>&productId=<%=review.getProduct().getId()%>">delete</a></div>
           </div>
         	<%	  
     		  } %>
     		  </form>
             </div>
     	  <%
        	  }
        	  
        	  
        	  em.getTransaction().commit();
          } finally {
        	  if(em.getTransaction().isActive())
        		  em.getTransaction().rollback();
          }
          %>
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
    <div class="right_footer"> <a href="#">home</a> <a href="#">about us</a> <a href="#">services</a> <a href="#">privacy policy</a> <a href="#">contact us</a> </div>
  </div>
  </div>
</body>
</html>