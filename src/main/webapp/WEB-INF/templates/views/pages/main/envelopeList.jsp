<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="initial-scale=1.0; maximum-scale=1.0; width=device-width;">
<meta name="description" content="">
<meta name="author" content="">
<title>Envelope List</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.9.1/jquery-ui.min.js"></script>
</head>
<style>

#filterth{
	padding-right:40px
    }
 

</style>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="/assets/css2.css" rel="stylesheet">
<body>

	<h1>Get an envelope's basic information and status</h1>
	<div align="center">
	<form method="get" action="/index">
    		<button class="btn btn-primary" type="submit">Home</button>
		</form>
	</div>
	<div class="row">
		<div>
			 <form action="/filterEnvelopes" method="post" data-busy="form"> 
			<!--  <form id="filterData" data-busy="form">  -->
				<table id="filter">
					<tr>
						<th id="filterth"><label for="count">Count:</label> <select
							id="envCount" name="envCount" class="form-control">
								<%-- <c:forEach items="${done.users}" var="user">
                  		<option value="${user.userId}">${user.email}</option>
            </c:forEach> --%>
            					<option value="select">--Select--</option>
								<option value="10">10</option>
								<option value="20">20</option>
								<option value="30">30</option>

						</select></th>

						<th id="filterth"><label for="envOrder">Order:</label> <select
							id="envOrder" name="envOrder" class="form-control">
							<option value="select">--Select--</option>
								<option value="asc">Ascending</option>
								<option value="desc">Descending</option>
						</select></th>

						<th id="filterth"><label for="envOrderBy">Order By:</label> <select
							id="envOrderBy" name="envOrderBy" class="form-control">
							<option value="select">--Select--</option>
								<option value="created">Created</option>
								<option value="completed">Completed</option>
								<option value="sent">Sent</option>
								<option value="status">Status</option>
								<option value="subject">Subject</option>
								<option value="user_name">UserName</option>

								<!-- <option value="envelope_name">Descending</option>
		    <option value="expire">Descending</option>
		     <option value="status_changed">Descending</option>
          <option value="last_modified">Ascending</option>
		    <option value="action_required">Descending</option>
				 -->
						</select></th>

						<th id="filterth"><label for="envStatus">Status:</label> <select
							id="envStatus" name="envStatus" class="form-control">
							<option value="select">--Select--</option>
								<option value="completed">Completed</option>
								<option value="declined">Declined</option>
								<option value="delivered">Delivered</option>
								<option value="sent">Sent</option>
								<option value="signed">Signed</option>
								<option value="processing">Processing</option>

						</select></th>

						<th id="filterth"><label for="envCreatedFrom">Created From:</label> <input
							type="date" id="envCreatedFrom" name="envCreatedFrom"
							class="form-control"></th>

						<th id="filterth"><label for="envCreatedTo">Created To:</label> <input
							type="date" id="envCreatedTo" name="envCreatedTo"
							class="form-control"></th>

						<th id="filterth">
							<button type="submit" class="btn btn-primary">Submit</button>
						</th>
					</tr>
				</table>

			</form>
		</div>
		<!-- Filter Ends Here -->


		<br />

		<div id="envelopeDetailView">
			<c:choose>
				<c:when test="${not empty done.envelopes}">
					<table id="envelopes" border="1">
						<caption></caption>
						<tr>
							<th>Sr No.</th>
							<th id="envSubject">Subject</th>
							<!-- <th id="envSender">Sender</th> -->
							<th id="envStatus">Status</th>
							<th id="envCreatedAt">Created At</th>
							<th id="envRecipients">Recipients</th>
							<th id="envRecipients">Documents</th>

						</tr>
						<c:forEach items="${done.envelopes}" var="item" varStatus="loop">

							<tr id="envelopeId" name="envelopeId" value="${item.envelopeId}">
							
								<td>${loop.index+1}</td>							
								<td>${item.emailSubject}</td>
								<%-- <td>${item.sender.userName}</td> --%>
								<td>${item.status}</td>
								<td>${item.createdDateTime}</td>

								<!-- Recipients TD -->
								<td>
									<!-- Signer Details -->
									<h3>Signer:</h3>
									<table id="signers" border="1" >
										<%--  <caption>Signer</caption> --%>
										<tr>
											<th>Email</th>
											<th>Status</th>
											<!--  <th>Sent On</th> -->
											<th>Delivered Date</th>
											<th>Signed Date</th>
										</tr>
										<c:forEach items="${item.recipients.signers}" var="signer">

											<tr>

												<td>${signer.email}</td>
												<td>${signer.status}</td>
												<%-- <td>${signer.sentDateTime}</td> --%>
												<td>${signer.deliveredDateTime}</td>
												<td>${signer.signedDateTime}</td>
											</tr>

										</c:forEach>
									</table> <!-- Signer Details End--> <!-- cc Details Start--> 
									<c:if test="${not empty item.recipients.carbonCopies}">
										<h3>Carbon Copies:</h3>
										<table id="carbenCopies" border="1" >
											<%--  <caption>Carbon Copies</caption> --%>
											<tr>
												<th>Email</th>
												<th>Status</th>
												<!--  <th>Sent On</th> -->
												<th>Delivered Date</th>
											</tr>
											<c:forEach items="${item.recipients.carbonCopies}" var="cc">

												<tr>
													<td>${cc.email}</td>
													<td>${cc.status}</td>
													<%--  <td>${cc.sentDateTime}</td> --%>
													<td>${cc.deliveredDateTime}</td>
												</tr>

											</c:forEach>
										</table>
									</c:if> <!-- cc Details End -->

								</td>

								<!-- Recipients TD End-->

								<!-- Documents TD Start-->

								<td>
									<table id="docTable" border="1">
										<tr>
											<th>Sr No.</th>
											<th>Name</th>
											<th></th>
										</tr>
										<c:forEach items="${item.envelopeDocuments}" var="document"
											varStatus="loop">

											<tr>
												<td>${loop.index+1}</td>
												<td><a
													href="/downloadDoc/${item.envelopeId}/${document.documentId}" target="_blank">${document.name}</a></td>
												<td>
												<c:if  test="${(document.name !='Summary')}">	
													<c:if  test="${(item.status == 'sent') || (item.status =='created')}">												
														<a href="/deleteDoc/${item.envelopeId}/${document.documentId}"><i class="fa fa-trash"></i></a>
													</c:if>
												</c:if>
												</td>	
											</tr>

										</c:forEach>

									</table>
									<br/>
									<div align="right">
											<a href="/downloadAllDocs/${item.envelopeId}">DownloadAll</a>
										</div>
									
								</td>
								<!-- Documents TD End-->

							</tr>

						</c:forEach>
					</table>

				</c:when>
				<c:otherwise>
					<p>No Envelopes Found</p>
				</c:otherwise>
			</c:choose>
		</div>
		<!-- Envelope Table Ends Here -->
	</div>
	
</body>

<!-- <script>
    $(document).ready(function() {
    	$("#filterData").submit(function(e) {
    		console.log("hi");
    	    $.ajax({
    	        type: 'POST',
    	        //accept:"text/html",
    	        // dataType: json,
    	        //contentType: 'application/json',
    	        url: '/filterEnvelopes',
    	        data: $('#filterData').serialize(),
    	        success: function(response) {
    	        	console.log(response);
    	            $('#envelopeDetailView').html(response);
    	        },
    	        error: function() {
                    console.log('not okay');
                }
    	    });
    	});	
    });
    </script> -->
</html>
