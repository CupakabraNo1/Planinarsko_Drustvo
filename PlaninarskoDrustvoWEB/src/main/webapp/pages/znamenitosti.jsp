<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Znamenitosti</title>
</head>
<body>

	<h1>Znamenitosti za stazu: ${staza.naziv}</h1>
    <c:if test="${!empty znamenitosti}"></c:if>
	<c:forEach var="z" begin="0" end="${znamenitosti.size()-1}">
		<div>
			<h1>${znamenitosti.get(i).tip}</h1>
			<h3>${znamenitosti.get(i).opis}</h3>
			<c:if test="${slike.get(i).size() != 0}">
				<c:forEach var="s" begin="0" end="${slike.get(i).size()}">
					<img src="data:image/jpg;base64,${slike.get(i).get(s)}">
				</c:forEach>
			</c:if>
			<c:if test="${slike.get(i).size() == 0}">
				<h1>Nema slika za ovu znamenitost.</h1>
			</c:if>
			<c:if test="${posetio.get(i) == 1}">
				<a href="${pageContext.request.contextPath}/korisnik/komentarisi">Komentarisi</a>
			</c:if>
			<a href="${pageContext.request.contextPath}/korisnik/poseti?z=${znamenitosti.get(i).idZnamenitost}">Zakazi posetu</a>
		</div>

	</c:forEach>

</body>
</html>