<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Poseta</title>
</head>
<body>

	<c:if test="${!empty termini}">

		<form action="${pageContext.request.contextPath}/korisnik/posetaTermina" method="post">
			<fieldset>
				<legend>Za ovu znamenitost morate izabrati jedan od
					ponudjenih termina</legend>
				<select name="termin">
					<c:forEach var="t" items="${termini}">
						<option value = "${t.idTermin}">pocetak: ${t.pocetak}	kraj:${t.kraj}</option>
					</c:forEach>
				</select>
				<input type="submit" value="Zakazi posetu">
			</fieldset>
		</form>
		
		<c:if test="${poseta == 1 }">
			<span>Nije moguce napraviti ovu posetu. Potrebno je imati rezervaciju u domu na ovoj planini.</span>
		</c:if>
		<c:if test="${!empty pos }">
			<span>Zakazali ste posetu. ${poseta.idPoseta}</span>
		</c:if>
	</c:if>

	<c:if test="${empty termini}">

	</c:if>

</body>
</html>