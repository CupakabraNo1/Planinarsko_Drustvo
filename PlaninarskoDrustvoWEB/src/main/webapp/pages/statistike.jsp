<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Statistike</title>
<link rel="stylesheet" type="text/css" href=<c:url value="/stilovi/stil.css" />>
</head>
<body>
	
	<jsp:include page="/pages/navigacija.jsp" />
	<div class="view">
	<h1>Statistika</h1>
	<hr>
	<c:forEach var="s" items="${statistika}">
		<div class="statistika">
			<span><b> Naziv planine: </b> ${s.key.naziv} </span><br>
			<span><b> Broj rezervacija: </b> ${s.value[0]} </span><br>
			<span><b> Broj nocenja: </b> ${s.value[1]} </span>
		</div>
		<br>
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
	</div>
</body>
</html>