<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Question</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<a href="/" class="float-end">Dashboard</a>
	</div>
	<h1 class="text-center pt-4">${question.questionText}</h1>
	<h4 class="container">Tags: <c:forEach items="${question.tags}" var="tag">
		<c:if test="${question.tags.indexOf(tag) != question.tags.size()}">
		<span class="border-dark bg-dark text-light shadow p-2">${tag.subject}</span>
		</c:if>
		</c:forEach>
	</h4>
	<div class="d-flex  justify-content-around">
		<div class="container text-center mb-5 p-5">
			<table class="table">
				<thead class="thead-dark">
					<tr>
						<th>Answers</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${question.answers}" var="answer">
		        	<tr class="table-info">
			            <td>${answer.answerText}</td>
		        	</tr>
		        	</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="container p-5">
		<p class="text-danger"><form:errors path="answer.*"/></p>
		<form:form action="/answers/new" method="post" modelAttribute="answer" class="mt-5">
		    <p>
		        <form:label path="answerText" cssClass="form-label">Answer</form:label>
		        <form:textarea path="answerText" cssClass="form-control text-center"/>
		    </p>
		    <p>
		        <form:hidden path="question" value="${question.id}"/>
		    </p>
		    <input type="submit" value="Answer" class="btn btn-primary mt-3"/>
		</form:form>
		</div>
	</div>
</body>
</html>