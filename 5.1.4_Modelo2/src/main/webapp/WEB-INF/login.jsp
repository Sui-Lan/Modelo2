<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@page import="com.repaso.modelo.Usuario"%>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>JSP CRUD</title>
	<script src="/webjars/jquery/jquery.min.js"></script>
	<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="/webjars/bootstrap/4.3.0/css/bootstrap.min.css"/>
</head>
<body>
	<br>
	<form action="handleLogin" method="POST" class="p-3">
		<h1>Iniciar sesión</h1>
		<c:if test="${mensaje != null ? true : false}">
			<div class="alert alert-secondary alert-dismissible fade show" role="alert">
				${mensaje}
				<button type="button" class="close" data-dismiss="alert" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
		</c:if>
		<div class="form-row align-items-center">
			<div class="col-auto">
				<label class="sr-only" for="nombre">Nombre</label> 
				<input type="text" class="form-control mb-2" id="nombre" name="nombre" placeholder="Nombre de usuario">
			</div>
			<div class="col-auto">
				<label class="sr-only" for="clave">Contraseña</label> 
				<input type="password" class="form-control mb-2" id="clave" name="clave" placeholder="Contraseña">
			</div>
			<div class="col-auto">
				<button type="submit" class="btn btn-primary mb-2">Enviar</button>
			</div>
		</div>
	</form>
<div>
</div>
</body>
</html>