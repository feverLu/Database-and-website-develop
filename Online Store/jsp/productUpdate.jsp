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
    <div class="crumb_nav"> <a href="welcome.jsp">home</a> &gt;&gt;<a href="productList.jsp">category name</a>&gt;&gt;<a href="productDetail.jsp">product name</a>&gt;&gt;update product</div>
     <%
      EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
      EntityManager em = emf.createEntityManager();
      try{
      	em.getTransaction().begin();
      	String productId = request.getParameter("productId");
      	String personId = request.getParameter("personId");
      	System.out.println("productUpdate entered" + personId);
      	TypedQuery<Product> productQuery = em.createNamedQuery("Product.findById", Product.class);
      	productQuery.setParameter("id", Integer.parseInt(productId));
      	Product product = productQuery.getResultList().get(0);
      	%>
     
      <div class="title"><span class="title_icon"><img src="images/bullet1.gif" alt="" title="" /></div>
      
      
      
      <div class="feat_prod_box_details">
       <div class="prod_img"><a href="details.htm"><img src="images/prod1.gif" alt="" title="" border="0" /></a> <br />
          <br />
          <a href="images/big_pic.jpg" rel="lightbox"><img src="images/zoom.gif" alt="" title="" border="0" /></a> </div>
        <div class="my_prod_det_box">
          <div class="box_top"></div>
          <div class="box_center">
        
            <div class="prod_title">Details</div>
            <p class="details">
            <form name="register" method="post" action="ProductServlet">
            
            <div class="my_form_row">
             <label class="contact"><strong>Name:</strong></label>
            <textarea rows="1" name="name" ><%=product.getName() %></textarea>
            </div>
            <div class="my_form_row">
            <label class="contact"><strong>Price:</strong></label>
            <input class="text" name="price" value=<%=product.getPrice() %>><span class="red"> $</span>
            </div>
            <div class="my_form_row">
            <label class="contact"><strong>Description:</strong></label>
            <textarea rows="3" name="description"><%=product.getDescription()%></textarea>
            </div>
            <input type="submit" class="register2" value="update">
              <input type="hidden" name="command" value="update">
              <input type="hidden" name="productId" value=<%=product.getId() %>>
              <input type="hidden" name="personId" value=<%=personId %>>
            </form>
            <div class="clear"></div>
          </div>
          <div class="box_bottom"></div>
        </div>
        <form name="register" method="post" action="ProductServlet">
              <input type="submit" class="register2" value="delete" >
              <input type="hidden" name="command" value="delete" >
              <input type="hidden" name="productId" value=<%=product.getId() %>>
              <input type="hidden" name="personId" value=<%=personId %>>
        </form>
        <div class="clear"></div>
      </div>
      </div>
      
    <!--end of left content--> 
  
  <div class="right_content">
  	           <div id="demo" class="demolayout">
        <ul id="demo-nav" class="demolayout">
          <li><a class="active" href="#tab1">More details</a></li>
        </ul>
        <div class="tabs-container">
          <div style="display: block;" class="tab" id="tab1">
          
              <%
      	List<Review> reviews = new ArrayList<Review>(product.getReview());
              System.out.println("productDetail" + reviews.toString());
      	if (reviews != null) {
      		int size = reviews.size();
      		for (int i=0; i<size; i++) {
      	%>
      	 <ul class="my_list">
      	 <li>
      	  <form name="register" method="post" action="ProductServlet">
      	  <div class="form_row">
      	 <label class="contact"><strong>Star:</strong></label>
              <input type="text" name="reviewStars" value=<%=product.getReview().get(i).getStar() %>>
             </div>
             <div class="my_form_row">
             <label class="contact"><strong>Review:</strong></label>
              <textarea rows="3" name="reviews" ><%=product.getReview().get(i).getReview() %></textarea>
             </div>
             &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp 
             &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp 
		     <a href="ProductServlet?command=deleteReview&personId=<%=personId %>&productId=<%=product.getId() %>&reviewId=<%=reviews.get(i).getId() %>">delete</a>
		     <input type="submit" class="my_register" value="update">
              <input type="hidden" name="command" value="updateReview">
              <input type="hidden" name="productId" value=<%=product.getId() %>>
              <input type="hidden" name="personId" value=<%=personId %>>
              <input type="hidden" name="reviewId" value=<%=reviews.get(i).getId() %>>
             </form>
         </ul>
              <%}
      	}
       %>
        </div>
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