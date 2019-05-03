<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1" />
<title>Insert title here</title>
</head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<body>
	<div class="container">
		<h2>Bienvenido a mi tienda.</h2>
		<br> <br>
		<table>
		<tr>
		<form:form name="product" method="POST" action="/shopping/product">
			<div class="form-group">
				<label for="exampleInputEmail1">Usuario</label> <input type="text"
					class="form-control" id="exampleInputEmail1"
					aria-describedby="emailHelp" placeholder="Enter user"
					name="nameUser">
			</div>
			<div class="form-group">
				<label for="exampleInputPassword1">Password</label> <input
					type="password" class="form-control" id="exampleInputPassword1"
					placeholder="Password" name="password">
			</div>

			<button type="submit" class="btn btn-primary">Iniciar  sesión</button>
		</form:form>
		</tr>
		<tr>
		<form:form name="submitForm" method="post" value="product">
			<button type="submit" class="btn btn-warning">Nuevo Usuario</button>
		</form:form>
		</tr>
		</table>
	</div>
</body>
</html>