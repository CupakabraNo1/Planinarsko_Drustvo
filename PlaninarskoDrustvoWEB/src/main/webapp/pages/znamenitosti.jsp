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
	<c:if test="${!empty staze}">
		<form
			action="${pageContext.request.contextPath}/korisnik/pregledZnamenitosti">
			<label for="staze">Staze</label> <select name="staza">
				<c:forEach var="s" items="${staze}">
					<option value="${s.idStaza}">${s.naziv} (${s.planina.naziv})</option>
				</c:forEach>
			</select> <input type="submit" value="Pretrazi">
		</form>
	</c:if>
	<hr>
	
    <c:if test="${!empty znamenitosti}">
    <h1>Znamenitosti za stazu: ${staza.naziv}</h1>
	<c:forEach var="z" begin="0" end="${znamenitosti.size()-1}">
		<div>
			<h1>${znamenitosti.get(i).tip}</h1>
			<span>${znamenitosti.get(i).opis}</span><br>
			<c:if test="${slike.get(i).size() != 0}">
				<c:forEach var="s" begin="0" end="${slike.get(i).size()}">
					<img src="data:image/jpg;base64,${slike.get(i).get(s)}"><br>
				</c:forEach>
			</c:if>
			<c:if test="${slike.get(i).size() == 0}">
				<span>Nema slika za ovu znamenitost.</span><br>
			</c:if>
			<c:if test="${posetio.get(i) == 1}">
				<a href="${pageContext.request.contextPath}/korisnik/prikaziKomentare=${znamenitost.get(i).idZnamenitost}"><button>Komentarisi</button></a>
			</c:if>
			<a href="${pageContext.request.contextPath}/korisnik/poseti?z=${znamenitosti.get(i).idZnamenitost}"><button>Zakazi posetu</button></a>
		</div>

	</c:forEach>
	</c:if>

</body>
</html>