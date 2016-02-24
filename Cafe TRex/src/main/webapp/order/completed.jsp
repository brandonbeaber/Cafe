<!DOCTYPE html>

<html lang="en"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Cafe T-Rex CSCI 4830 Website">
    <meta name="author" content="">
    <link rel="shortcut icon" href="../images/favicon.ico" type="image/x-icon">
    <link rel="icon" href="../images/favicon.ico" type="image/x-icon">
	
    <title>Cafe T-Rex</title>

    <!-- Bootstrap core CSS -->
    <link href="../lib/dependencies/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="../lib/dependencies/css/style.css" rel="stylesheet">

	<!-- Begin JSP Services -->
	<%@ page import="com.cafetrex.services.jsp.*" %>
	<%@ page import="com.cafetrex.valueObjects.*" %>
	<%@ page import="java.util.*" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<% HTMLService hs = new HTMLService(session, request, response, out);
	ShoppingCartService scs = new ShoppingCartService(session, request, response, out);
    %>
   	<!-- End JSP Services -->

    </head>

  <body>

	<!--Begin Top Menu -->
    <nav class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
				<a href="/"><img style="padding:4px 0px 0px 0px; float:none" class="navbar-brand" src="../images/small-icon.png"  alt="Small Logo" onmouseover="this.src='../images/small-icon-white.png';" onmouseout="this.src='../images/small-icon.png';"  /></a>
                <a style="float:none" class="navbar-brand" href="/">Cafe T-Rex</a>
            </div>
            <div id="navbar" class="collapse navbar-collapse">
                <ul class="nav navbar-nav">
                    <li><a href="/">Home</a></li>
                    <li><a href="../about.html">About</a></li>
                    <li class="active"><a href="order.jsp">Order</a></li>
                    <li><a href="../contact.html">Contact</a></li>

                </ul>
            </div>
        </div>
    </nav>
	<!--End Top Menu -->
	
	
<br />
    <div class="container">
    

<div class="container-fluid">
	<div class="text-center">
<% hs.showAlert(); 
if (request.getParameter("at").equals("1")) {
	%><img style="max-width:20%" src="../images/completed.png" /><%
	scs.clearShoppingCart();
}
%>
	</div>
</div>

    </div><!-- /.container -->

    <script src="../lib/dependencies/jquery.min.js"></script>
    <script src="../lib/dependencies/bootstrap.min.js"></script>
   	<script src="../lib/dependencies/order.js"></script>

</body></html>