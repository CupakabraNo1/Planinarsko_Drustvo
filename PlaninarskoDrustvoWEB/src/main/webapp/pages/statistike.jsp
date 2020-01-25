<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Statistike</title>
</head>
<body>

	<h1>Statistika</h1>
	<hr>
	<c:forEach var="s" items="${statistika}">
		<div>
			<span>${s.key.naziv} </span><span> (${s.value[0]}) </span><span>
				(${s.value[1]}) </span>
		</div>
	</c:forEach>
	<hr>
	<h3>Pregled poseta znamenitosti: </h3>
	<form
		action="${pageContext.request.contextPath}/administrator/prikaziPosete">
		<select name="znamenitost">
			<c:forEach var="z" items="${znamenitosti}">
				<option value="${z.idZnamenitost}">${z.opis}</option>
			</c:forEach>
		</select><br> 
		<input type="submit" value="Prikazi posete"> 
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form>
	<c:if test="${!empty posete}">
		<c:forEach var="p" items="${posete}">
			<span>${p.rezervacija.korisnik.ime} ${p.rezervacija.korisnik.prezime}</span>
			<span>${p.terminZnamenitost.pocetak}</span>
			<span>${p.terminZnamenitost.kraj}</span>
		</c:forEach>
	</c:if>
</body>
</html>