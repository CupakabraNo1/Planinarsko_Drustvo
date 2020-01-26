<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Clanstva</title>
<link rel="stylesheet" type="text/css" href=<c:url value="/stilovi/stil.css" />>
</head>
<body>
	<jsp:include page="/pages/navigacija.jsp" />
	<div class="view">
	<h1>Clanarine:</h1>
	<hr>
	<c:forEach var="k" items="${korisnici}">
		<c:if test="${k.uloga.idUloga == 2 && (empty k.clanarina)}">
			<div class="neuclanjen">
				<form
					action="${pageContext.request.contextPath}/administrator/uclani"
					method="post">
					<fieldset>
						<legend>Ovaj korisnik nije uclanjen (dodelite mu clanarinu) </legend>
						<input type="hidden" name="id" value="${k.idKorisnik}">
						<span> ${k.ime } </span><br>
						<span> ${k.prezime } </span><br>
						<span><input type="date" name="od"></span> <br>
						<span><input type="date" name="do_"></span> <br>
						<span><input type="submit" value="Uclani"></span><br>
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					</fieldset>
				</form>
			</div>
		</c:if>
		<c:if test="${k.uloga.idUloga == 2 && (!empty k.clanarina)}">
			<div class="uclanjen">
				<form
					action="${pageContext.request.contextPath}/administrator/produzi"
					method="post">
					<fieldset>
						<legend>Ovaj korisnik je uclanjen (mozete mu produziti clanarinu) </legend>
						<input type="hidden" name="id" value="${k.idKorisnik}">
						<span> ${k.ime } </span><br>
						<span> ${k.prezime } </span><br>
						<span><input type="date" name="od"></span><span><b> ${k.clanarina.od }</b></span> <br>
						<span><input type="date" name="do_"></span><span><b> ${k.clanarina.do_}</b></span> <br>
						<span><input type="submit" value="Produzi"></span><br>
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					</fieldset>
				</form>
			</div>
		</c:if>
		<c:if test="${k.uloga.idUloga == 1}">
			<div class="admin">
				<form>
					<fieldset>
						<legend>Ovaj korisnik je administrator (clanarina nije neophodna) </legend>
						<span> ${k.ime } </span><br>
						<span> ${k.prezime } </span><br>
					</fieldset>
				</form>
			</div>
		</c:if>
	</c:forEach>
	
	<hr>
	
	<form action="${pageContext.request.contextPath}/administrator/uclaniNovog" method="post">
		<fieldset>
			<legend>Uclani novog</legend>
			<label for="ime">Ime</label> <input type="text" name="ime"><br>
			<label for="prezime">Prezime</label> <input type="text" name="prezime"> <br> 
			<label for="korisnicko_ime">Korisničko ime</label><input type="text" name="korisnicko_ime"><br>
			<label for="lozinka">Lozinka</label><input type="password" name="lozinka"><br>
			<!-- TODO: uloga korisnika u drustvu  -->
			<select name="uloga">
				<c:forEach var="u" items="${uloge}">
					<option value="${u.idUloga}">${u.naziv}</option>
				</c:forEach>
			</select>
			<label for="od">Od</label><input type="date" name="od">
			<label for="do_">Do</label><input type="date" name="do_">
			<input type="submit" value="Uclani">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		</fieldset>
	</form>
	
	</div>

</body>
</html>