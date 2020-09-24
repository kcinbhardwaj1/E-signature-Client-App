<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<br />
<title>IFrame Application</title>
<br />

<script type="text/javascript"
	src="https://static.eversign.com/js/embedded-signing.js"></script>
<style type="text/css">
body {
	background-color: #fefdfc;
}

p.description {
	width: 500px;
	border: 1px solid #cfc;
	padding: 20px;
}

.form_cont {
	border: 1px solid #cfc;
	padding: 20px;
	width: 1024px;
	background-color: #cd9;
}

.top_cont {
	width: 1124px;
	margin-left: auto;
	margin-right: auto;
}

#dswpf {
	width: 1024px;
	height: 700px;
}
</style>
</head>
<link href="/assets/css.css" rel="stylesheet">
<body>
	<div class="table-title" align="center">
		<h2>Embedded Signing Ceremony URLs</h2>

		<c:choose>
			<c:when test="${empty done.embedList}">

				<h2>
					Embedded Signing Ceremony in Process... <img id="loading-image"
						src="\images\Preloader_3.gif" alt="Loading..." />
				</h2>
			</c:when>
		</c:choose>

		<c:choose>
			<c:when test="${not empty done.embedList}">

				<table border="2" cellpadding="5">
					<tr>
						<th>Signer Name</th>
						<th>Embedded URL</th>
					</tr>
					<c:forEach items="${done.embedList}" var="item">

						<tr>
							<td>${item.signerName}</td>
							<td><a href="/viewUrl?url=${item.viewUrl}" target=_blank>Click
									Here</a></td>

						</tr>
					</c:forEach>
				</table>
			</c:when>
		</c:choose>
		<br /> <br />

		<c:choose>
			<c:when test="${done.isTemplate == 'true'}">
				<p>
					<a href="/embedSigning">Continue</a>
				</p>
			</c:when>
			<c:otherwise>
				<p>
					<a href="/embedSigningWithoutTemp">Continue</a>
				</p>
			</c:otherwise>
		</c:choose>
	</div>
	<p>
		<a href="/getLatestStatus/${done.envelopeId}" target=_blank>Latest
			Status</a>
	</p>

</body>
</html>