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
	CheckoutService cs = new CheckoutService(session, request, response, out);
	cs.executeCheckoutService();
	scs.executeOrderService();
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
	<div class="row">
			<div class="col-md-5">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h3 class="panel-title">
						Your Order
					</h3>
				</div>
				<div class="panel-body">
				<h4>Delivery Type:</h4>
				<ul class="nav nav-pills">
				
				<li class="<%=scs.getDeliveryType() %>">
					 <a href="checkout.jsp?type=delivery"> Delivery</a>
				</li>
				<li class="<%=scs.getPickupType() %>">
					 <a href="checkout.jsp?type=pickup"> Pickup</a>
				</li>
				</ul>
				<br />
					<h4>Shopping Cart:</h4>
					<%hs.buildShoppingCart(scs.getShoppingCart()); %>
						<div class="text-center">
				<button type="button" class="btn btn-primary btn-block" onclick="window.location = 'order.jsp'">
				Return to Menu
			</button>
				</div>
				</div>
	
			</div>
		</div>
		<form action='checkout.jsp?placeorder' method='post'>
		<input type="hidden" name="checkout" value="checkout">
		<div class="col-md-7">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h3 class="panel-title">
						Checkout
					</h3>
				</div>
				<div class="panel-body">
				<form role="form">
				<div class="form-group">
					 
					<label for="txtName">
						Name
					</label>
					<input class="form-control" name="name" placeholder="Your Name"/>
				</div>
				<div class="form-group">
					 
					<label for="email">
						Email
					</label>
					<input type="email" class="form-control" name="email" placeholder="Your Email"/>
				</div>
				<div class="form-group">
					 
					<label for="phone">
						Phone
					</label>
					<input type="tel" class="form-control" name="phone" placeholder="Ex: 402-111-1111"/>
				</div>
				<% if(scs.getCartType() == 1) { %>
					
 			<div class="form-group">
                <label for="address">Address</label>
                <input type="text" name="address" class="form-control" id="address" placeholder="Street address">
            </div>
            <div class="row">
                <div class="form-group col-sm-6">
                    <label for="bill-city">City</label>
                    <input type="text" name="city" class="form-control" id="city" placeholder="City"></div>
                <div class="form-group col-sm-3">
                    <label for="bill-state">State</label>
                    <input type="text" name="state" class="form-control" id="state" placeholder="State"></div>
                <div class="form-group col-sm-3">
                    <label for="bill-zip">Zip</label>
                    <input type="text" name="zip" class="form-control" id="zip" placeholder="Zip"></div>
            </div>
				
				<%}%>
			    <div class="form-group">
					 
					<label for="inst">
						Special Instructions
					</label>
					<textarea name="special" id="special" rows="4" class="form-control"></textarea>
				</div>	
				
				<button type="submit" class="btn btn-success btn-block">
					Submit
				</button>
			</form>
				</div>
	
			</div>
		</div>
		</form>

	</div>
</div>

    </div><!-- /.container -->
	
    <script src="../lib/dependencies/jquery.min.js"></script>
    <script src="../lib/dependencies/bootstrap.min.js"></script>

</body></html>