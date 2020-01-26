<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Poseta</title>
<link rel="stylesheet" type="text/css" href=<c:url value="/stilovi/stil.css" />>
</head>
<body>
	
	<jsp:include page="/pages/navigacija.jsp" />
	<div class="view">
		<c:if test="${!empty termini}">
	
			<form action="${pageContext.request.contextPath}/korisnik/posetaTermina" method="post">
				<fieldset>
					<legend>Za ovu znamenitost morate izabrati jedan od
						ponudjenih termina</legend>
					<select name="termin">
						<c:forEach var="t" items="${termini}">
							<option value="${t.idTermin}">pocetak: ${t.pocetak}	kraj:${t.kraj}</option>
						</c:forEach>
					</select><br>
					<input type="submit" value="Zakazi posetu">
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
				</fieldset>
			</form>
		</c:if>
	
		<c:if test="${empty termini}">
			<form action="${pageContext.request.contextPath}/korisnik/posetaVanTermina" method="post">
				<fieldset>
					<legend>Za ovu znamenitost nije potreban izbor termina</legend>
					<input type="hidden" name="znamenitost" value="${znamenitost.idZnamenitost}"/>
					<input type="date" name="pocetak"><br>
					<input type="date" name="kraj">
					<input type="submit" value="Zakazi posetu">
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
				</fieldset>
			</form>
		</c:if>
		
		<c:if test="${poseta == 1 }">
				<span class="neuspesno">Nije moguce napraviti ovu posetu. Potrebno je imati rezervaciju u domu na ovoj planini.</span>
			</c:if>
			<c:if test="${!empty pos }">
				<span class="uspesno">Zakazali ste posetu. ${poseta.idPoseta}</span>
			</c:if>
	</div>
</body>
</html>