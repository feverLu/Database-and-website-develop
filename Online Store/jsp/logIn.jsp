<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
        <li class="selected"><a href="register.htm">log in</a></li>
         <li><a href="myaccount.htm">account</a></li>
        <li><a href="details.htm">user list</a></li>
        <li><a href="contact.htm">contact</a></li>
      </ul>
    </div>
  </div>

  <div class="center_content">
    <div class="left_content">
      <div class="title"><span class="title_icon"><img src="images/bullet1.gif" alt="" title="" /></span>Log in</div>
      <div class="feat_prod_box_details">
        <p class="details"> Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud. Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud. </p>
        <div class="my_contact_form">
          <div class="form_subtitle">log in</div>
          <form name="register" method="post" action="ProductServlet">
            <div class="form_row">
              <label class="contact"><strong>LogName:</strong></label>
              <input type="text" name="logName" />
            </div>
            <div class="form_row">
              <label class="contact"><strong>Password:</strong></label>
              <input type="text" name="passcode" />
            </div>
            <div class="form_row">
              <input type="submit" class="register" value="Log in" />
              <input type="hidden" name="command" value="login" /><br/>
            </div>
          </form>
        </div>
      </div>
      <div class="clear"></div>
    </div>

    <div class="right_content">
          <div class="feat_prod_box_details">
        <div class="contact_form">
          <div class="form_subtitle">create seller account</div>
          <form name="register" method="post" action="LogInServlet">
              <div class="form_row">
              <label class="contact"><strong>LogName:</strong></label>
              <input type="text" name="logName" />
            </div>
            <div class="form_row">
              <label class="contact"><strong>Passcode :</strong></label>
              <input type="password" name="passcode" />
            </div>
            <div class="form_row">
              <label class="contact"><strong>FirstName:</strong></label>
              <input type="text" name="firstName" />
            </div>
            <div class="form_row">
              <label class="contact"><strong>LastName:</strong></label>
              <input type="text" name="lastName" />
            </div>
            <div class="form_row">
              <input type="submit" class="register" value="register" />
              <input type="hidden" name="command" value="createSeller" />
            </div>
          </form>
        </div>
        
	 <div class="contact_form">
          <div class="form_subtitle">create buyer account</div>
          <form name="register" method="post" action="LogInServlet">
              <div class="form_row">
              <label class="contact"><strong>LogName:</strong></label>
              <input type="text" name="logName" />
            </div>
            <div class="form_row">
              <label class="contact"><strong>Passcode :</strong></label>
              <input type="password" name="passcode" />
            </div>
            <div class="form_row">
              <label class="contact"><strong>FirstName:</strong></label>
              <input type="text" name="firstName" />
            </div>
            <div class="form_row">
              <label class="contact"><strong>LastName:</strong></label>
              <input type="text" name="lastName" />
            </div>
            <div class="form_row">
              <input type="submit" class="register" value="register" />
              <input type="hidden" name="command" value="createBuyer" />
            </div>
          </form>
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