<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Prijava</title>
</head>
<body>

	<c:if test="${! empty loguj }">

		<form action="${pageContext.request.contextPath}/login" method="post">
			<fieldset>
				<legend>Prijava</legend>
				<label for="username">Korisničko ime</label> <input type="text" name="username"><br> 
				<label for="password">Lozinka</label><input type="password" name="password"> <input type="submit" value="Prijavi se">
				 <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			</fieldset>
		</form>

		<a href="${pageContext.request.contextPath}/registruj">Registruj
			se</a>
	</c:if>

	<!--<c:set var = "registracija" scope = "session" value = "1"/>-->


	<c:if test="${! empty reg}">

		<form action="${pageContext.request.contextPath}/registracija"
			method="post">
			<fieldset>
				<legend>Registracija</legend>
				<label for="ime">Ime</label> <input type="text" name="ime"><br>
				<label for="prezime">Prezime</label> <input type="text" name="prezime"> <br> 
				<label for="korisnicko_ime">Korisničko ime</label><input type="text" name="korisnicko_ime"><br>
				<label for="lozinka">Lozinka</label><input type="password" name="lozinka">
				<input type="submit" value="Prijavi se">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			</fieldset>
		</form>
		<a href="${pageContext.request.contextPath}/logovanje">Prijavi se</a>
	</c:if>
</body>
</html>