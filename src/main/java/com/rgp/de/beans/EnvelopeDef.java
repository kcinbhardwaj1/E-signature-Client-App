package com.rgp.de.beans;

import java.util.ArrayList;
import java.util.List;

import com.docusign.esign.model.Document;
import com.docusign.esign.model.RecipientViewRequest;
import com.docusign.esign.model.Recipients;

public class EnvelopeDef {
	
	private String emailSubject = null;
	
	private Recipients recipients = null;
	
	private List<Document> documents = new ArrayList<Document>();
	
	private String status;
	
	private RecipientViewRequest recipientViewRequest;
	
	private String templateId;
	

	/**
	 * @return the templateId
	 */
	public String getTemplateId() {
		return templateId;
	}

	/**
	 * @param templateId the templateId to set
	 */
	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	/**
	 * @return the recipientViewRequest
	 */
	public RecipientViewRequest getRecipientViewRequest() {
		return recipientViewRequest;
	}

	/**
	 * @param recipientViewRequest the recipientViewRequest to set
	 */
	public void setRecipientViewRequest(RecipientViewRequest recipientViewRequest) {
		this.recipientViewRequest = recipientViewRequest;
	}

	/**
	 * @return the documents
	 */
	public List<Document> getDocuments() {
		return documents;
	}

	/**
	 * @param documents the documents to set
	 */
	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}

	/**
	 * @return the emailSubject
	 */
	public String getEmailSubject() {
		return emailSubject;
	}

	/**
	 * @return the recipients
	 */
	public Recipients getRecipients() {
		return recipients;
	}


	/**
	 * @param emailSubject the emailSubject to set
	 */
	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}

	/**
	 * @param recipients the recipients to set
	 */
	public void setRecipients(Recipients recipients) {
		this.recipients = recipients;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	

}
