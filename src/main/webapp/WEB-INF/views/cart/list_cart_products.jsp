<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<title>Cesta de la compra</title>


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
				<li class="nav-item"><a class="nav-link"
					href="${pageContext.request.contextPath}/product/index">Listado
						productos </a></li>
				<li class="nav-item active"><a class="nav-link" href="#">Cesta
						<span class="sr-only">(current)</span>
				</a></li>
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
			class="text-secondary border border-success border-top-0 border-left-0 border-right-0">Cesta
			de la compra</h1>
		<br>
		<table class="table table-striped">

			<tr>
				<th class="alert alert-info" role="alert">Option</th>
				<th class="alert alert-info" role="alert">Id</th>
				<th class="alert alert-info" role="alert">Name</th>
				<th class="alert alert-info" role="alert">Price</th>
				<th class="alert alert-info" role="alert">Quantity</th>
				<th class="alert alert-info" role="alert">Sub-total</th>
			</tr>
			<c:set var="total" value="0"></c:set>
			<c:forEach var="item" items="${cartList}">
				<c:set var="total"
					value="${total + item.product.price * item.quantity }"></c:set>
				<tr>
					<td align="center"><a
						href="${pageContext.request.contextPath }/cart/remove/${item.product.id }">Remove</a></td>
					<td>${item.product.id }</td>
					<td>${item.product.name }</td>
					<td align="right"><fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${item.product.price }"/></td>
					<td><a class="btn btn-sm"
						href="${pageContext.request.contextPath }/cart/add/${item.product.id }"
						role="button">+</a>&nbsp;${item.quantity }&nbsp; <a
						class="btn btn-sm"
						href="${pageContext.request.contextPath }/cart/minus/${item.product.id }"
						role="button">-</a></td>
					<td align="right"><fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${item.product.price * item.quantity }"/></td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="5" align="right">Total:</td>
				<td align="right">
					<fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2"  value="${total }"/> 
					
				</td>
			</tr>
		</table>

		<!-- Button trigger modal -->
		<button type="button" class="btn btn-success" data-toggle="modal"
			data-target="#exampleModalCenter">Confirmar la compra</button>

		<!-- Modal -->
		<div class="modal fade" id="exampleModalCenter" tabindex="-1"
			role="dialog" aria-labelledby="exampleModalCenterTitle"
			aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalCenterTitle">Confirmar
							Pago</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<!-- Cuerpo del cuadro de texto -->

						<table align="center">
							<tr>
								<th></th>
								<th></th>
							</tr>
							<tr>
								<td align="right"><a>Precio de los artículos:</a>&nbsp;</td>
								<td align="right"><fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${total}"/></td>
							</tr>
							<tr>
								<td align="right"><a>IVA 21%:</a>&nbsp;</td>
								<td align="right"><fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${total * 0.21 }"/></td>
								
							</tr>
							<tr>
								<td align="right"><a>Total:</a>&nbsp;</td>
								<td align="right"><fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${total * 1.21 }"/></td>
							</tr>
						</table>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Cancelar</button>
						<button type="button" class="btn btn-primary">Confirmar Pago</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>
</body>
</html>