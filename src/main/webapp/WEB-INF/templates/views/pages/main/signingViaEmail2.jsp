<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.9.1/jquery-ui.min.js"></script>

</head>
<link href="/assets/css.css" rel="stylesheet">
<body>

	<form action="/remoteSigning" method="post" data-busy="form">
		<div class="table-title" align="center">

			<h1>Send an envelope with a remote (email) signer and cc
				recipient</h1>
			<p>The envelope includes a pdf, Word, and HTML document.</p>


			<table id="template" border="1">
				<caption></caption>
				<c:choose>
					<c:when test="${not empty done.recipients.signers}">
						<tr id="signerId" name="signers">
							<c:forEach items="${done.recipients.signers}" var="item"
								varStatus="loop">

								<tr>
									<td class="text-left"><label for="signerName">Signer
											Name</label></td>
									<td class="text-left"><input type="text" id="signerName"
										name="signerName" value="${item.name}"></td>
								</tr>
								<tr>
									<td class="text-left"><label for="signerEmail">Signer
											Email</label></td>
									<td class="text-left"><input type="email" id="signerEmail"
										name="signerEmail" aria-describedby="emailHelp"
										value="${item.email}"></td>
								</tr>

							</c:forEach>
						</tr>

					</c:when>
					<c:otherwise>
					</c:otherwise>
				</c:choose>


				<c:forEach items="${done.recipients.carbonCopy}" var="item"
					varStatus="loop">

					<tr>
						<td class="text-left"><label for="ccName">CC Name</label></td>
						<td class="text-left"><input type="text" id="ccName"
							name="ccName" value="${item.name}"></td>
					</tr>
					<tr>
						<td class="text-left"><label for="ccEmail">CC Email</label></td>
						<td class="text-left"><input type="email" id="ccrEmail"
							name="ccEmail" aria-describedby="emailHelp"
							value="${item.email}"></td>

					</tr>

				</c:forEach>


				<tr>
				<tr>
					<td class="text-left"><label for="emailSubject">Email
							Subject</label></td>
					<td class="text-left"><input type="text" id="emailSubject"
						name="emailSubject" required></td>
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
				<td class="text-left">
						<button type="submit">Submit</button>
					</td>
					<td class="text-left"><input type="hidden" id="templateId"
						name="templateId" value="${done.templateId}"></td>
					

				</tr>
				</table>
				</tr>

			</table>


		</div>
	</form>
	<br />
	<br />
	<div class="table-title" align="center">
		<form method="get" action="/index">
			<button class="btn btn-primary" type="submit">Home</button>
		</form>
	</div>

</body>
<script>
    $(document).ready(function() {

        var iCnt = 0;
        // CREATE A "DIV" ELEMENT AND DESIGN IT USING jQuery ".css()" CLASS.
        var container = $(document.createElement('div')).css({
            padding: '10px', margin: '5px',spacing:'5px',
            borderTopColor: '#999', borderBottomColor: '#999',
            borderLeftColor: '#999', borderRightColor: '#999'
        });

        $('#btAdd').click(function() {
            if (iCnt <= 5) {

                iCnt = iCnt + 1;
                
                
                // ADD TEXTBOX.
                $(container).append('<div id=tb' + iCnt+'><tr><td class="text-left"><label for="signerName">Signer Name  :</label></td><td class="text-left"><input type=text name="signerName" class="input" id=tb' + iCnt + ' ' +
                    iCnt + '" /></td></tr>'+'<tr><td class="text-left"><label for="signerEmail">Signer Email  :</label></td><td class="text-left"><input name="signerEmail" type=text class="input" id=tb' + iCnt + ' ' +
                    iCnt + '" /></td></tr></div><br/>'
                    );
            // ADD BOTH THE DIV ELEMENTS TO THE "main" CONTAINER.
                $('#main').before(container);
            }
            // AFTER REACHING THE SPECIFIED LIMIT, DISABLE THE "ADD" BUTTON.
            // (5 IS THE LIMIT WE HAVE SET)
            else {      
                $(container).append('<label>Reached the limit</label>'); 
                $('#btAdd').attr('class', 'bt-disable'); 
                $('#btAdd').attr('disabled', 'disabled');
            }
        });

        // REMOVE ONE ELEMENT PER CLICK.
        $('#btRemove').click(function() {
            if (iCnt != 0) { $('#tb' + iCnt).remove(); iCnt = iCnt - 1; }
        
            if (iCnt == 0) { 
                $(container)
                    .empty() 
                    .remove(); 

                
                $('#btAdd')
                    .removeAttr('disabled');
            }
        });

        // REMOVE ALL THE ELEMENTS IN THE CONTAINER.
        $('#btRemoveAll').click(function() {
            $(container)
                .empty()
                .remove(); 

            iCnt = 0; 
            
            $('#btAdd')
                .removeAttr('disabled') 
                .attr('class', 'bt');
        });
    });

    
</script>
</html>