<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Planinarsko društvo</title>
<link rel="stylesheet" type="text/css" href=<c:url value="/stilovi/stil.css" />>
</head>
<body>

	<c:if test="${!empty korisnik.clanarina || korisnik.uloga.idUloga == 1}">
		<jsp:include page="/pages/navigacija.jsp" />
	</c:if>
	<c:if test="${empty korisnik.clanarina && korisnik.uloga.idUloga != 1}">
		<span class="upozorenje">Jos uvek nemate odobren pristup sistemu. </span>
	</c:if>
	<div class="view">
		<c:if test="${!empty korisnik}">
			<div >
				<h1>${korisnik.ime} ${korisnik.prezime }</h1>
				<hr>
				<span><b>Uloga: </b> ${korisnik.uloga.naziv}</span><br> 
				<span><b>Korisnicko ime: </b>${korisnik.korisnickoIme }</span>
				<hr>
				<form action="${pageContext.request.contextPath}/logout" method="post">
					<input type="submit" value="Odjavi se">
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				</form>
			</div>
		</c:if>
	</div>

	
</body>
</html>