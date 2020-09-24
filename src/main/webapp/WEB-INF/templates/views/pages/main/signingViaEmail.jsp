<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="initial-scale=1.0; maximum-scale=1.0; width=device-width;">
<meta name="description" content="">
<meta name="author" content="">


<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script src="/js/es.js"></script>
</head>
<link href="/assets/css.css" rel="stylesheet">
<body>



	<form action="/templateDetails/${templateId}" method="get" data-busy="form">
		<div class="table-title" align="center">

			<h1> Send an envelope with a remote (email) signer and cc
				recipient</h1>
			<p>Use Template to send Envelope </p>
			
			<c:choose>
				<c:when test="${empty done}">

					<h2>
						Use Template for remote Singing in Process... <img id="loading-image"
							src="\images\Preloader_3.gif" alt="Loading..." />
					</h2>
				</c:when>
			</c:choose>

			<c:choose>
				<c:when test="${not empty done}">
			<table class="table-fill" border="2" cellpadding="5">
					
				<tr>
					<td class="text-left"><label for="templateId">Template:</label></td>
		  			<td class="text-left">
			  			<select id="templateId" name="templateId" class="form-control"> 
					  		<c:forEach items="${done}" var="template">
			                  		<option value="${template.templateId}">${template.name}</option>
			            	</c:forEach>
			  			</select>
		  			</td>
				</tr>
				<tr></tr>

				<tr>
					<td></td>
					<td>
						<button type="submit">Submit</button>
					</td>
				</tr>
			</table>
			</c:when>
			</c:choose>
		</div>
	</form>
	<br/>
	<br/>
	<div align="center">
	<form method="get" action="/index">
    		<button class="btn btn-primary" type="submit">Home</button>
		</form>
	</div>
</body>
</html>