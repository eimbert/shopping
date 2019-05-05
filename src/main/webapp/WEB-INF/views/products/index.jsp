<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Product Page</title>
<style type="text/css">
.thumbnail {
 	position: relative;
}
.caption {
 	position: absolute;
 	color: orange;
 	font-weight:bold;
 	top: 45%;
 	left: 45%;
 	width: 45%;
}
</style>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<div class="collapse navbar-collapse" id="navbarText">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}">Cerrar sesión
						<span class="sr-only"></span>
				</a></li>
				<li class="nav-item active"><a class="nav-link" href="#">Listado productos <span class="sr-only">(current)</span></a>
				</li>
				<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/cart/cart">Cesta</a></li>
			</ul>
			<span class="navbar-text">Hola &nbsp; ${userName} &nbsp;&nbsp;</span>
			<div class="thumbnail text-center">
				<a class="navbar-brand" href="${pageContext.request.contextPath}/cart/cart">
    			<img src="${pageContext.request.contextPath}/resources/basket.png" width="30" height="30" class="d-inline-block align-top" alt=""></a>
    			<div class="caption"><p>${itemsInCart}</p></div>
    		</div>
		</div>
	</nav>
	<div class="container">
		<h1
			class="text-secondary border border-success border-top-0 border-left-0 border-right-0">Products
			Page</h1>
		<br>
		<table class="table table-striped">
			<tr>
				<th class="alert alert-info" role="alert">Id</th>
				<th class="alert alert-info" role="alert">Name</th>
				<th class="alert alert-info" role="alert">Price</th>
				<th class="alert alert-info" role="alert">Option</th>
				<th class="alert alert-info" role="alert">BarCode</th>
			</tr>
			<c:forEach var="product" items="${products }">
				<tr>
					<td>${product.id }</td>
					<td>${product.name }</td>
					<td>${product.price }</td>
					<td align="center"><a href="${pageContext.request.contextPath}/product/buy/${product.id}">Add to Cart</a></td>
					<td><img
						src="${pageContext.request.contextPath }/product/barcode/${product.id}"
						width="200" height="40"></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>
