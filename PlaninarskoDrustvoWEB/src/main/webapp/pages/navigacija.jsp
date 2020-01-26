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
	
	<div class="navigation">

				<span><a href="${pageContext.request.contextPath}/korisnik/komentari">Komentari</a></span>
			
			
				<span><a href="${pageContext.request.contextPath}/korisnik/rezervisi">Rezervacija</a></span>
			
			<sec:authorize access="hasRole('administrator')">
				
					<span><a href="${pageContext.request.contextPath}/administrator/clanstva">Clanstva</a></span>
				
			</sec:authorize>
				
					<span><a href="${pageContext.request.contextPath}/ulogovan">Pocetna</a></span>
				
			<sec:authorize access="hasRole('administrator')">
				
					<span><a href="${pageContext.request.contextPath}/administrator/statistike">Statistike</a></span>
				
			</sec:authorize>
			
				<span><a href="${pageContext.request.contextPath}/korisnik/staze">Staze</a></span>
			
			
				<span><a href="${pageContext.request.contextPath}/korisnik/znamenitosti">Znamentitosti</a></span>
			
	</div>
</body>
</html>