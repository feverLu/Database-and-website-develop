<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,cs5200.server.*,cs5200.jpa.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="Content-Style-Type" content="text/css" />
	<link href="style.css" rel="stylesheet" type="text/css" />
	<link href="layout.css" rel="stylesheet" type="text/css" />
	<script src="js/jquery-1.4.2.min.js" type="text/javascript"></script>
	<script src="js/jqFancyTransitions.1.7.min.js" type="text/javascript"></script>
	<script src="js/tabs.js" type="text/javascript"></script>
	<script type="text/javascript">
	$(function(){
		$('#slider').jqFancyTransitions({
			effect: 'curtain', // wave, zipper, curtain
			width: 642, // width of panel
			height: 414, // height of panel
			strips: 10, // number of strips
			delay: 5000, // delay between images in ms
			stripDelay: 50, // delay beetwen strips in ms
			position: 'alternate', // top, bottom, alternate, curtain
			direction: 'fountainAlternate', // left, right, alternate, random, fountain, fountainAlternate
			navigation: false, // prev and next navigation buttons
			links: false // show images as links
		});
	})
	</script>	
	<!--[if lt IE 7]>
		<link href="ie_style.css" rel="stylesheet" type="text/css" />
	<![endif]-->
</head>

<body id="page1">
  <div id="main">
    <!-- header -->
    <div id="header">
      <div class="row-1">
      	<div class="logo">
        	<h1><a href="#">Shop Enjoy.co</a></h1>
        </div>
        <form action="" id="search-form">
          <fieldset>
          	<input type="text" class="text" value="search" onfocus="if(this.value=='search'){this.value=''}" onblur="if(this.value==''){this.value='search'}"/><input type="submit" class="submit" value=""/>
          </fieldset>
        </form>
      </div>
      <div class="row-2">
      	<!-- .nav -->
      	<ul class="nav">
        	<li class="first"><a href="#" class="current">Home</a></li>
          <li><a href="#">Products</a></li>
          <li><a href="#">My Account</a></li>
          <li class="last"><a href="#">Contact us</a></li>
        </ul>
      	<!-- /.nav -->
      </div>
    </div>
    <!-- content -->
    <div id="content">
    	<!-- .main-box -->
    	<div class="main-box">
      	<!-- .slider -->
      	<div id="slider">
        	<img src="images/slide1.jpg" alt="" />
          <img src="images/slide2.jpg" alt="" />
          <img src="images/slide3.jpg" alt="" />
        </div>
      	<!-- /.slider -->
        <!-- .tabs-box -->
        <div class="tabs-box">
        	<ul class="tabs">
          	<li><a href="#tab1">news</a></li>
            <li><a href="#tab2">articles</a></li>
            <li class="last-item"><a href="#tab3" class="last">issues</a></li>
          </ul>
          <div class="tab_container">
          	<div id="tab1" class="tab_content">
              <h2>Mixed grain movement trend keeps basic levels relatively stable</h2>
              <a href="#">Read More</a>
            </div>
            <div id="tab2" class="tab_content">
           	  <h2>Markets/Weather: April's second-half cooler than first</h2>
              <a href="#">Read More</a>
            </div>
            <div id="tab3" class="tab_content">
            	<h2>CME Group soybean prices explode to the upside</h2>
              <a href="#">Read More</a>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <!-- footer -->
  <div id="footer">
  	<div class="container">
    	<!-- .nav -->
    	<ul class="nav">
      	<li><a href="#">home</a>|</li>
        <li><a href="#">services</a>|</li>
        <li><a href="#">products</a>|</li>
        <li><a href="#">features</a>|</li>
        <li><a href="#">orders form</a>|</li>
        <li><a href="#">our equipment</a>|</li>
        <li><a href="#">contact info</a></li>
      </ul>
    	<!-- /.nav -->
      <span>agriculture.co</span> &nbsp;&copy; &nbsp;2010. &nbsp; &nbsp;<a href="#">Privacy Policy</a>
    </div>
  </div>
  <script type="text/javascript">
var gaJsHost = (("https:" == document.location.protocol) ? "https://ssl." : "http://www.");
document.write(unescape("%3Cscript src='" + gaJsHost + "google-analytics.com/ga.js' type='text/javascript'%3E%3C/script%3E"));
</script>
<script type="text/javascript">
try {
var pageTracker = _gat._getTracker("UA-7078796-1");
pageTracker._trackPageview();
} catch(err) {}</script>
</body>
</html>
