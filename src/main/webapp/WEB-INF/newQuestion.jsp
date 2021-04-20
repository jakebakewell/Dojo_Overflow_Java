<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>New Question</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
</head>
<body>
	<h1 class="pt-3 text-center mb-4">What is your question?</h1>
	<div class="container">
		<a href="/" class="float-end">Dashboard</a>
	</div>
	<div class="container text-center">
		<p class="text-danger"><form:errors path="quest.*"/></p>
		<p class="text-danger"><c:out value="${sizeError}" escapeXml="false"/></p>
		<p class="text-danger"><c:out value="${noTag}" escapeXml="false"/></p>
		<form:form action="/questions/new" method="post" modelAttribute="quest" class="mt-5">
		    <p>
		        <form:label path="questionText" cssClass="form-label">Question</form:label>
		        <form:textarea path="questionText" cssClass="form-control text-center"/>
		    </p>
		    <p>
		        <label for="tagList" class="form-label">Tags</label>
		        <input type="text" name="tagList" class="form-control text-center"/>
		    </p>
		    <input type="submit" value="Ask" class="btn btn-primary mt-3"/>
		</form:form>
	</div>
</body>
</html>