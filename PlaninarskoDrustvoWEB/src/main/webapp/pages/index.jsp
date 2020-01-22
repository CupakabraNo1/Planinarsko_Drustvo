<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Planinarsko društvo</title>
</head>
<body>
	<h1>Hej</h1>
	<c:if test="${!empty korisnik}">
		<h1>${korisnik.ime}${korisnik.prezime }</h1>
		<a href="${pageContext.request.contextPath}/logout"><button>Odjavi se</button></a>
	</c:if>


	<!-- Korisnicki deo -->
	<a href="${pageContext.request.contextPath}/korisnik/rezervisi"><button>Rezervacija</button></a>
	<a href="${pageContext.request.contextPath}/korisnik/znamenitosti"><button>Znamentitosti</button></a>
	<!-- Administratorski deo -->


</body>
</html>