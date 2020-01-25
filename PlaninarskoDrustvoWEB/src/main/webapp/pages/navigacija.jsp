<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Navigacija</title>
</head>
<body>
	
	<table style="position:fixed;top:0px;left:0px;right:0px;width:100%">
		<tr>
			
				<th><a href="${pageContext.request.contextPath}/korisnik/komentari">Komentari</a></th>
			
			
				<th><a href="${pageContext.request.contextPath}/korisnik/rezervisi">Rezervacija</a></th>
			
			<sec:authorize access="hasRole('administrator')">
				
					<th><a href="${pageContext.request.contextPath}/administrator/clanstva">Clanstva</a></th>
				
			</sec:authorize>
				
					<th><a href="${pageContext.request.contextPath}/ulogovan">Pocetna</a></th>
				
			<sec:authorize access="hasRole('administrator')">
				
					<th><a href="${pageContext.request.contextPath}/administrator/statistike">Statistike</a></th>
				
			</sec:authorize>
			
				<th><a href="${pageContext.request.contextPath}/korisnik/staze">Staze</a></th>
			
			
				<th><a href="${pageContext.request.contextPath}/korisnik/znamenitosti">Znamentitosti</a></th>
			
		</tr>
	</table>
</body>
</html>