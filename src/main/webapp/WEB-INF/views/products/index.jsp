<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Product Page</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<h1
			class="text-secondary border border-success border-top-0 border-left-0 border-right-0">Products
			Page</h1>
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
					<td align="center"><a
						href="${pageContext.request.contextPath }/cart/buy/${product.id}">Add
							to Cart</a></td>
					<td><img
						src="${pageContext.request.contextPath }/product/barcode/${product.id}"
						width="200" height="40"></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>
