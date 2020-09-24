<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html xmlns:th="http://www.thymeleaf.org">

<head>
<title>RGP Webhook : Envelope Status</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script language="javascript">
	setInterval(function() {
		window.location.reload(1);
	}, 10000);


</script>


<style>
table {
	border-collapse: collapse;
}

table, th, td {
	border: 1px solid black;
	padding: 10px;
}

td {
	border-bottom: 0px solid #ccc;
	padding: 0.1em 0 0.1em 0;
	text-align: center;
}

.sent {
	background-color: #1AEA30;
	color: #000;
}

.delivered {
	background-color: #7fbf7b;
	color: #000;
}

.created {
	background-color: #d0f0d0;
	color: #000;
}

.completed {
	background-color: #1b7837;
	color: #000;
}

.declined {
	background-color: #EA1A36;
	color: #000;
}

.voided {
	background-color: #EA1A36;
	color: #000;
}

.Sent {
	background-color: #1AEA30;
	color: #000;
}

.Delivered {
	background-color: #7fbf7b;
	color: #000;
}

.Created {
	background-color: #d0f0d0;
	color: #000;
}

.Completed {
	background-color: #1b7837;
	color: #fff;
}

.Declined {
	background-color: #EA1A36;
	color: #000;
}

.AuthenticationFailed {
	background-color: #EA1A36;
	color: #000;
}

.AutoResponded {
	background-color: #af8dc3;
	color: #000;
}
</style>
</head>

<body>



	<div class="container" style="min-height: 500px">

		<div class="starter-template">
			<h1>RGP Webhook : Envelope Status</h1>


			<form class="form-horizontal" id="search-form">


				<div id="" class="form-group form-group-lg">
									<c:choose>
						<c:when test="${empty envelopeList}">
						
						<h2>RGP Webhook : Envelope Status in Process... <img id="loading-image" src="\images\Preloader_3.gif" alt="Loading..." /></h2>
						</c:when>
						</c:choose>

					<c:choose>
						<c:when test="${not empty envelopeList}">

							<table id="webhookTable" border="1">
								<caption></caption>
								<tr>
									<th id="envStatus">Envelope Id</th>
									<th id="envStatus">Envelope Status</th>
									<th id="envStatus">Sent Time</th>
									<th id="envSigner">Signer Status</th>
									<th id="envCc">CarbonCopy Status</th>
								</tr>


								<c:forEach items="${envelopeList}" var="env" varStatus="loop">
									<tr id="webhookStatus">
										<td>${env.envelopeId}</td>
										<td class="${env.status}">${env.status}</td>
										<td>${env.statusChangedDateTime}</td>

										<td>
											<table>

												<c:forEach items="${env.recipients.signers}" var="signer">
													<tr>

														<td>${signer.email}</td>
														<td class="${signer.status}">[ ${signer.status} ]</td>

													</tr>
												</c:forEach>

											</table>
										</td>
										<td>
											<table>
												<c:forEach items="${env.recipients.carbonCopy}" var="cc">
													<tr>
														<td>${cc.email}</td>
														<td class="${cc.status}">[ ${cc.status} ]</td>
													</tr>

												</c:forEach>
											</table>
										</td>
									</tr>

								</c:forEach>

							</table>
						</c:when>
					</c:choose>
				</div>

			</form>

		</div>

	</div>

	<div class="container">
		<footer>
			<p>
				© <a href="http://www.mkyong.com">rgp.com</a> 2020 copyright
			</p>
		</footer>
	</div>



</body>
</html>




















