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
    <div class="logo"><a href="welcome.jsp"><img src="images/logo.gif" alt="" title="" border="0" /></a></div>
    <div id="menu">
      <ul>
        <li><a href="index.htm">home</a></li>
        <li><a href="about.htm">about us</a></li>
        <li class="selected"><a href="productList.jsp">books</a></li>
        <li><a href="specials.htm">specials books</a></li>
        <li><a href="myaccount.htm">my accout</a></li>
        <li><a href="register.htm">register</a></li>
        <li><a href="details.htm">prices</a></li>
        <li><a href="contact.htm">contact</a></li>
      </ul>
    </div>
  </div>
	
    <div class="center_content">
    <div class="left_content">
    <div class="crumb_nav"> <a href="welcome.jsp">home</a>&gt;&gt;<a href="productList.jsp">category name</a> &gt;&gt; product name </div>
     <%
      EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
      EntityManager em = emf.createEntityManager();
      try{
      	em.getTransaction().begin();
      	String personId = (String) request.getAttribute("personId");
      	String productId = request.getParameter("productId");
      	String receiptId = request.getParameter("receiptId");
      	System.out.println("productDetail PersonId" + personId);
      	TypedQuery<Product> productQuery = em.createNamedQuery("Product.findById", Product.class);
      	productQuery.setParameter("id", Integer.parseInt(productId));
      	Product product = productQuery.getResultList().get(0);
      	%>
     
      <div class="title"><span class="title_icon"><img src="images/bullet1.gif" alt="" title="" /></span><%=product.getName() %></div>
      <div class="feat_prod_box_details">
       <div class="prod_img"><a href="details.htm"><img src="images/prod1.gif" alt="" title="" border="0" /></a> <br />
          <br />
          <a href="images/big_pic.jpg" rel="lightbox"><img src="images/zoom.gif" alt="" title="" border="0" /></a> </div>
        <div class="prod_det_box">
          <div class="box_top"></div>
          <div class="box_center">
            <div class="prod_title">Details</div>
            <p class="details"><%=product.getDescription()  %>Lorem ipsum dolor sit amet,minim veniam, quis nostrud exercitation. </p>
            <div class="price">price: <%=product.getPrice() %><span class="red"> $</span></div>
            <a href="ReceiptServlet?command=addRecord&personId=<%=personId %>&receiptId=<%=receiptId %>&productId=<%=product.getId() %>" class="more"><img src="images/order_now.gif" alt="" title="" border="0" /></a>
            <div class="clear"></div>
          </div>
          <div class="box_bottom"></div>
        </div>
        <div class="clear"></div>
      </div>
      
            <div id="demo" class="demolayout">
        <ul id="demo-nav" class="demolayout">
          <li><a class="active" href="#tab1">Reviews</a></li>
        </ul>
        <div class="tabs-container">
          <div style="display: block;" class="tab" id="tab1">
          
              <%
      	List<Review> reviews = new ArrayList<Review>(product.getReview());
              System.out.println("productDetail" + reviews.toString());
            	if (reviews != null) {
              		String name = null;
              		int size = reviews.size();
              		for (int i=0; i<size; i++) {
              	 if (product.getReview().get(i).getBuyer() == null) {
              		 name = "administrator";
              	 } else {
              		 name = product.getReview().get(i).getBuyer().getLogName();
              	 }
              		 %>
              		  <ul class="list">
              	 <li><%=name  %>
              	 		<a href="#"><%=product.getReview().get(i).getReview() %>    stars: <%=product.getReview().get(i).getStar() %>     <%=product.getReview().get(i).getDateOfReview()%></a></li>
                 </ul>
                      <%}
              	}
       %>
       <div class="clear"></div>
        </div>
       
          </div>
          </div>
              <div class="clear"></div>
      </div>
    <!--end of left content--> 
      
	<div class="right_content">
	<form>
	 <div class="form_row">
            <input type="submit" class="register" value="update" />
              <input type="hidden" name="command" value="productUpdate">
              <input type="hidden" name="personId" value=<%=personId %>>
              <input type="hidden" name="productId" value=<%=product.getId()  %>>
              <input type="hidden" name="receiptId" value=<%=receiptId  %>>
            </div>
	 </form>
         <div class="title"><span class="title_icon"><img src="images/bullet1.gif" alt="" title="" /></span>New Book</div>
      <div class="feat_prod_box_details">
        <div class="contact_form">
          <div class="form_subtitle">add a review</div>
          
          
          
          <form name="register" method="post" action="ProductServlet">
              <div class="form_row">
              <label class="contact"><strong>Star:</strong></label>
              <input type="text" name="reviewStars" />
             </div>
              <div class="form_row">
              <label class="contact"><strong>Review:</strong></label>
              <textarea rows="3" name="reviews" ></textarea>
             </div>
            <div class="form_row">
              <input type="submit" class="register" value="add" />
              <input type="hidden" name="command" value="addReview" />
              <input type="hidden" name="personId" value=<%=personId %>>
              <input type="hidden" name="productId" value="<%=product.getId() %>">
              <input type="hidden" name="receiptId" value=<%=receiptId  %>>
            </div>
          </form>
        </div>
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
    <div class="left_footer"><img src="images/footer_logo.gif" alt="" title="" /><br />
      </div>
    <div class="right_footer"> <a href="#">home</a> <a href="#">about us</a> <a href="#">services</a> <a href="#">privacy policy</a> <a href="#">contact us</a> </div>
  </div>
</div>
</body>
</html>