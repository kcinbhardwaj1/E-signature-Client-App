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
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.9.1/jquery-ui.min.js"></script>

</head>
<link href="/assets/css.css" rel="stylesheet">
<body>
	<form action="/embeddedSigning" method="post" data-busy="form">
		<div class="table-title" align="center">

			<h1>2. Send an envelope with an embedded signer</h1>
			<p>The envelope includes a pdf, Word, and HTML document.</p>

			<table id="template" border="1">
				<caption></caption>
				<c:choose>
					<c:when test="${not empty done.recipients.signers}">
						<tr id="signerId" name="signers">
							<c:forEach items="${done.recipients.signers}" var="item"
								varStatus="loop">
								<table>
									<tr>
										<td><label for="signerName">Signer Name</label></td>
										<td><input type="text" id="signerName" name="signerName"
											value="${item.name}"></td>
									</tr>
									<tr>
										<td><label for="signerEmail">Signer Email</label></td>
										<td><input type="email" id="signerEmail"
											name="signerEmail" aria-describedby="emailHelp"
											value="${item.email}"></td>
									</tr>
								</table>
							</c:forEach>
						</tr>
					</c:when>
					<c:otherwise>
					</c:otherwise>
				</c:choose>

				<tr>
					<table>
						<tr>
							<td class="text-left"><label for="emailSubject">Email
									Subject</label></td>
							<td class="text-left"><input type="text" id="emailSubject"
								name="emailSubject" required></td>
						</tr>
					</table>
				</tr>
				<tr>
					<table>
						<tr>
							<td class="text-left">

								<div class="table-title" align="center" id="main">
									<input type="button" id="btAdd" value="Add Recipient  "
										class="bt" /> <input type="button" id="btRemove"
										value="Remove Recipient  " class="bt" /> <input type="button"
										id="btRemoveAll" value="Remove All" class="bt" /><br />
								</div>
							</td>
						</tr>

						<tr>
							<td class="text-left"><button type="submit">Submit</button></td>
							<td><input type="hidden" id="templateId" name="templateId"
								value="${done.templateId}"></td>

						</tr>
					</table>
				</tr>
			</table>
		</div>
	</form>
	<br />
	<br />
	<div align="center">
		<form method="get" action="/index">
			<button class="btn btn-primary" type="submit">Home</button>
		</form>
	</div>
</body>
<script>
	$(document)
			.ready(
					function() {

						var iCnt = 0;
						// CREATE A "DIV" ELEMENT AND DESIGN IT USING jQuery ".css()" CLASS.
						var container = $(document.createElement('div')).css({
							padding : '10px',
							margin : '5px',
							spacing : '5px',
							borderTopColor : '#999',
							borderBottomColor : '#999',
							borderLeftColor : '#999',
							borderRightColor : '#999'
						});

						$('#btAdd')
								.click(
										function() {
											if (iCnt <= 5) {

												iCnt = iCnt + 1;

												// ADD TEXTBOX.
												$(container)
														.append(
																'<div id=tb' + iCnt+'><tr><td class="text-left"><label for="signerName">Signer Name  :</label></td><td class="text-left"><input type=text name="signerName" class="input" id=tb' + iCnt + ' ' +
                    iCnt + '" /></td></tr>'
																		+ '<tr><td class="text-left"><label for="signerEmail">Signer Email  :</label></td><td class="text-left"><input name="signerEmail" type=text class="input" id=tb' + iCnt + ' ' +
                    iCnt + '" /></td></tr></div><br/>');
												// ADD BOTH THE DIV ELEMENTS TO THE "main" CONTAINER.
												$('#main').before(container);
											}
											// AFTER REACHING THE SPECIFIED LIMIT, DISABLE THE "ADD" BUTTON.
											// (5 IS THE LIMIT WE HAVE SET)
											else {
												$(container)
														.append(
																'<label>Reached the limit</label>');
												$('#btAdd').attr('class',
														'bt-disable');
												$('#btAdd').attr('disabled',
														'disabled');
											}
										});

						// REMOVE ONE ELEMENT PER CLICK.
						$('#btRemove').click(function() {
							if (iCnt != 0) {
								$('#tb' + iCnt).remove();
								iCnt = iCnt - 1;
							}

							if (iCnt == 0) {
								$(container).empty().remove();

								$('#btAdd').removeAttr('disabled');
							}
						});

						// REMOVE ALL THE ELEMENTS IN THE CONTAINER.
						$('#btRemoveAll').click(
								function() {
									$(container).empty().remove();

									iCnt = 0;

									$('#btAdd').removeAttr('disabled').attr(
											'class', 'bt');
								});
					});
</script>
</html>