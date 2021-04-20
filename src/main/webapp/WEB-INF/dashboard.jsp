<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Dashboard</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
</head>
<body>
	<h1 class="pt-3 text-center mb-5">Questions Dashboard</h1>
	<div class=" container text-center mb-5">
		<table class="table">
			<thead class="thead-dark">
				<tr>
					<th>Question</th>
					<th>Tags</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${questions}" var="question">
	        	<tr class="table-info">
		            <td><a href="/questions/${question.id}"><c:out value="${question.questionText}"/></a></td>
		            <td><c:forEach items="${question.tags}" var="tag">
		            	<c:if test="${question.tags.indexOf(tag) != question.tags.size()}"><span>${tag.subject}</span></c:if>
		            </c:forEach></td>
	        	</tr>
	        	</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="container">
		<a href="/questions/new" class="float-end mr-5">New Question</a>
	</div>
</body>
</html>