<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*,cs5200.server.*,cs5200.jpa.*"%>
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
      <div class="crumb_nav"> <a href="welcom.jsp">home</a> &gt;&gt; category name </div>
      <div class="title"><span class="title_icon"><img src="images/bullet1.gif" alt="" title="" /></span>Category books</div>
      <div class="new_products">
      <%
      EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
      EntityManager em = emf.createEntityManager();
      try{
      	em.getTransaction().begin();
      	//try {
      		String personId = (String) request.getAttribute("personId");
      		String receiptId = request.getParameter("receiptId");
      	//} catch (Exception e) {
      		//personId = request.getParameter("personId");
      	//}
      	System.out.println("ProductList entered personId" + personId);
      	TypedQuery<Product> productQuery = em.createNamedQuery("Product.findByAll", Product.class);
    	List<Product> productList = productQuery.getResultList();	
		if (productList != null) {
			for (Product product : productList) {
		%>
        <div class="new_prod_box"><%=product.getName() %>
          <div class="new_prod_bg"> 
          <a href="ProductServlet?command=read&personId=<%=Integer.valueOf(personId) %>&productId=<%=product.getId()%>&receiptId=<%=receiptId %>">
          <img src="images/thumb1.gif" alt="" title="" class="thumb" border="0" />
          </a> </div>
          <div class="new_pro_box"><%=product.getPrice() %> $</div>
        </div>
		<%
			}
			%>
      </div>
      <div class="clear"></div>
    </div>
    <!--end of left content-->

	<div class="right_content">
         <div class="title"><span class="title_icon"><img src="images/bullet1.gif" alt="" title="" /></span>New Book</div>
      <div class="feat_prod_box_details">
        <p class="details"> Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud. Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud. </p>
        <div class="contact_form">
          <div class="form_subtitle">add a new book</div>
          <form name="register" method="post" action="ProductServlet">
              <div class="form_row">
              <label class="contact"><strong>Name: </strong></label>
              <input type="text" name="name" />
             </div>
             <div class="form_row">
              <label class="contact"><strong>Price: </strong></label>
              <input type="text" name="price" ><span class="red"> $</span>
             </div>
             <div class="form_row">
              <label class="contact"><strong>Description: </strong></label>
              <textarea rows="3" name="description" ></textarea>
             </div>
          
            <div class="form_row">
              <input type="submit" class="register" value="create" />
              <input type="hidden" name="command" value="create" />
              <input type="hidden" name="personId" value=<%=personId %>>
              <input type="hidden" name="receiptId" value=<%=receiptId %>>
            </div>
          </form>
        </div>
        <%
		}
		em.getTransaction().commit();
      } finally {
    	    if (em.getTransaction().isActive())
				em.getTransaction().rollback();
			em.close();
      }
		%>
         <div class="title"><span class="title_icon"><img src="images/bullet3.gif" alt="" title="" /></span>About Our Store</div>
      <div class="about">
        <p> <img src="images/about.gif" alt="" title="" class="right" /> Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud. </p>
      </div>
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