
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Rezervacije</title>
</head>
<body>
	<c:if test="${empty planine}">
		<h1>Nema nijedne planine</h1>
	</c:if>

	<c:if test="${!empty planine}">
		<form action="${pageContext.request.contextPath}/korisnik/domovi">
			<label for="planina">Planina</label> <select name="planina">
				<c:forEach var="p" items="${planine}">
					<option value="${p.idPlanina}">${p.naziv}</option>
				</c:forEach>
			</select>
			<input type="submit" value="Pretrazi">
		</form>
	</c:if>
	<hr>
    <c:if test="${!empty domovi}">
    	<a href="${pageContext.request.contextPath}/korisnik/pregledStaza"><button>Pregled staza</button></a>
    </c:if>
	<c:if test="${!empty domovi }">
		<form action="${pageContext.request.contextPath}/korisnik/napraviRezervaciju" method="post">
			<label for="dom">Dom</label> <select name="dom">
				<c:forEach var="d" items="${domovi}">
					<option value="${d.idDom }">${d.naziv}</option>
				</c:forEach>
			</select> 
			<label for="datumPocetka">Datum pocetka</label> <input type="date" name="datumPocetka">
			<label for="datumZavrsetka">Datum zavrsetka</label> <input type="date" name="datumZavrsetka">
			<input type="submit" value="Rezervisi">
		</form>
	</c:if>

	<c:if test="${empty domovi }">
		<h1>Nema domova. Plakyyyy.</h1>
	</c:if>
	
	<c:if test="${!empty rezervacija}">
		<h1>Napravili ste rezervaciju id joj je ${rezervacija.idRezervacija}</h1>
	</c:if>



</body>
</html>