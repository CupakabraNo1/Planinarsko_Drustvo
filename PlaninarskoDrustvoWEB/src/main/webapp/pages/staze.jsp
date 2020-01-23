<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Planinarske staze</title>
</head>
<body>

	<c:if test="${!empty planine}">
		<form
			action="${pageContext.request.contextPath}/korisnik/pregledStaza">
			<label for="planina">Planina</label> <select name="planina">
				<c:forEach var="p" items="${planine}">
					<option value="${p.idPlanina}">${p.naziv}</option>
				</c:forEach>
			</select> <input type="submit" value="Pretrazi">
		</form>
	</c:if>

	<hr>

	<c:if test="${!empty staze}">
		<c:forEach var="i" begin="0" end="${staze.size()-1}">
			<form
				action="${pageContext.request.contextPath}/korisnik/pregledZnamenitosti">
				<div style="border: 1px solid black; magine: 1%">

					<h2>${staze.get(i).naziv}</h2>
					<h4>${staze.get(i).opis }</h4>
					<img src="data:image/jpg;base64,${bs64.get(i)}"
						alt="${staze.get(i).naziv}">
					<h6>${staze.get(i).tezina}</h6>
					<input type="hidden" name="staza" value="${staze.get(i).idStaza}">
					<input type="submit" value="Prikazi znamenitost">

				</div>
			</form>
		</c:forEach>
	</c:if>


</body>
</html>