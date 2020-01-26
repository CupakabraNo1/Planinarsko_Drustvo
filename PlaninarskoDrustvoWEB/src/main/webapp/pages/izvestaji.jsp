<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Izvestaji</title>
<link rel="stylesheet" type="text/css" href=<c:url value="/stilovi/stil.css" />>
</head>
<body>
	
	<jsp:include page="/pages/navigacija.jsp" />
	<div class="view">
	<c:if test="${!empty rezervacija}">
		<form action="${pageContext.request.contextPath}/korisnik/dodajIzvestaj" method="post" enctype="multipart/form-data">
			<fieldset>
				<legend>Ostavite izvestaj</legend>
				<input type="hidden" name="rezervacija" value="${rezervacija.idRezervacija}">
				<label for="izvestaj">Unesi tekst</label><textarea name="izvestaj" rows="3" cols="30"></textarea><br>
				<label for="slika">Izaberite sliku </label><input type="file" name="slika" accept="image/png, image/jpeg"><br> 
				<input type="submit" value="Unesi">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> 
			</fieldset>
		</form>
	</c:if>
	<c:if test="${empty rezervacija }">
		<span><b>Nemate rezervaciju za ovu planinu, ne mozete ostaviti izvestaj...</b></span>
	</c:if>
	<c:if test="${!empty izvestaj}">
		<span><b>Dodali ste vas izvestaj.</b></span>
	</c:if>
	</div>
</body>
</html>