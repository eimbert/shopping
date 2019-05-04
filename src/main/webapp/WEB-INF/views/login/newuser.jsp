<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1" />
<title>New User</title>
</head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<body>
	<div class="container">
		<h2>Alta nuevo usuario</h2>
		<br> <br>
		<form:form name="newUser" method="POST" action="/shopping/user">
			<div class="form-row">
				<div class="form-group col-md-6">
					<label for="inputEmail4">Usuario</label> <input type="text"
						class="form-control" id="inputUsuario" placeholder="Usuario"
						name="user">
				</div>
				<div class="form-group col-md-6">
					<label for="inputPassword">Password</label> <input type="password"
						class="form-control" id="inputPassword" placeholder="Password"
						name="password">
				</div>
			</div>
			<div class="form-group">
				<label for="inputName">Nombre</label> <input type="text"
					class="form-control" id="inputNombre"
					placeholder="Nombre y apellidos" name="name">
			</div>
			<div class="form-group">
				<label for="inputAdress">Dirección</label> <input type="text"
					class="form-control" id="inputAddress"
					placeholder="Dirección, número, piso, puerta" name="adress">
			</div>
			<div class="form-row">
				<div class="form-group col-md-6">
					<label for="inputEmail">email</label> <input type="email"
						class="form-control" id="inputEmail" name="email">
				</div>
				<div class="form-group col-md-2">
					<label for="inputPhone">Teléfono</label> <input type="text"
						class="form-control" id="inputPhone" name="phone">
				</div>
			</div>

			<button type="submit" class="btn btn-primary">Alta Usuario</button>
		</form:form>
	</div>
</body>
</html>