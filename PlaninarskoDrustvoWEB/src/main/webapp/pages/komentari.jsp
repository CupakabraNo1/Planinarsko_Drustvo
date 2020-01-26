<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Komentari</title>
<link rel="stylesheet" type="text/css" href=<c:url value="/stilovi/stil.css" />>
</head>
<body>
	
	<jsp:include page="/pages/navigacija.jsp" />
	<div class="view">
	<c:if test="${!empty znamenitosti}">
		<form
			action="${pageContext.request.contextPath}/korisnik/komentariZnamenitosti">
			<label for="znamenitost">Znamenitost</label> <select
				name="znamenitost">
				<c:forEach var="z" items="${znamenitosti}">
					<option value="${z.idZnamenitost}">${z.opis}</option>
				</c:forEach>
			</select> <input type="submit" value="Pretrazi">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		</form>
	</c:if>
	<hr>
	
	<h3>Komentari:</h3>
	<c:if test="${!empty komentari}">
		<c:forEach var="k" items="${komentari}">
			<div>
				<i><q>${k.tekst}</q></i>
			</div>
		</c:forEach>
		<span>.</span>
		<br>
		<span>.</span>
		<br>
		<span>.</span>
		<br>

	</c:if>
	<c:if test="${empty komentari}">
		<i><b>Nema dostupnih komentara</b></i>
	</c:if>
	<hr>
	<c:if test="${!empty poseta}">
		<form action="${pageContext.request.contextPath}/korisnik/komentarisi"
			method="post">
			<input type="text" style="height: 50px; width: 100%" name="tekst"><br>
			<input type="submit" value="Komentarisi">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		</form>
	</c:if>
	<c:if test="${empty poseta}">
		<b> Nije vam dozvoljeno komentarisanje ove znamenitosti. </b>
	</c:if>
	</div>
</body>
</html>