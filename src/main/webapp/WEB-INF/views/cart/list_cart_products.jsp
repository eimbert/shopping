<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Cesta de la compra</title>

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
				<li class="nav-item"><a class="nav-link"
					href="${pageContext.request.contextPath}">Cerrar sesión <span
						class="sr-only"></span>
				</a></li>
				<li class="nav-item active"><a class="nav-link" href="#">Listado
						productos </a></li>
				<li class="nav-item"><a class="nav-link" href="#">Cesta <span
						class="sr-only">(current)</span></a></li>
			</ul>
			<span class="navbar-text">Hola &nbsp; ${userName} &nbsp;&nbsp;</span>
			<div class="thumbnail text-center">
				<a class="navbar-brand" href="#"> <img
					src="${pageContext.request.contextPath}/resources/basket.png"
					width="30" height="30" class="d-inline-block align-top" alt=""></a>
				<div class="caption"></div>
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
				<th>Option</th>
				<th class="alert alert-info" role="alert">Id</th>
				<th class="alert alert-info" role="alert">Name</th>
				<th class="alert alert-info" role="alert">Price</th>
				<th class="alert alert-info" role="alert">Option</th>
				<th class="alert alert-info" role="alert">BarCode</th>
			</tr>
			<c:set var="total" value="0"></c:set>
			<c:forEach var="item" items="${cartList}">
				<c:set var="total"
					value="${total + item.product.price * item.quantity }"></c:set>
				<tr>
					<td align="center"><a
						href="${pageContext.request.contextPath }/cart/remove/${item.product.id }"
						onclick="return confirm('Are you sure?')">Remove</a></td>
					<td>${item.product.id }</td>
					<td>${item.product.name }</td>
					<td>${item.product.price }</td>
					<td>${item.quantity }</td>
					<td>${item.product.price * item.quantity }</td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="6" align="right">Sum</td>
				<td>${total }</td>
			</tr>
		</table>
		<br> <a href="${pageContext.request.contextPath }/product">Continue Shopping</a>
	</div>
</body>
</html>