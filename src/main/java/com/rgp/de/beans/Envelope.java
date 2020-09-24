package com.rgp.de.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Envelope implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		
		result = prime * result + ((statusChangedDateTime == null) ? 0 : statusChangedDateTime.hashCode());
		System.out.println("In hash code result "+result);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Envelope other = (Envelope) obj;
		if (!sentDateTime.equals(other.sentDateTime))
			return false;
		System.out.println("In equals ");
		return true;
	}

	@JsonProperty("createdDateTime")
	private String createdDateTime;

	@JsonProperty("envelopeDocuments")
	private List<Document> envelopeDocuments = new ArrayList<Document>();

	@JsonProperty("envelopeId")
	private String envelopeId = null;

	@JsonProperty("emailSubject")
	private String emailSubject = null;

	@JsonProperty("lastModifiedDateTime")
	private String lastModifiedDateTime;

	@JsonProperty("recipients")
	private Recipients recipients = null;

	@JsonProperty("sentDateTime")
	private String sentDateTime;

	@JsonProperty("status")
	private String status;

	@JsonProperty("statusChangedDateTime")
	private String statusChangedDateTime;
	
	/**
	 * @return the emailSubject
	 */
	public String getEmailSubject() {
		return emailSubject;
	}

	/**
	 * @param emailSubject the emailSubject to set
	 */
	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}

	/**
	 * @return the recipients
	 */
	public Recipients getRecipients() {
		return recipients;
	}

	/**
	 * @param recipients the recipients to set
	 */
	public void setRecipients(Recipients recipients) {
		this.recipients = recipients;
	}

	/**
	 * @return the envelopeDocuments
	 */
	public List<Document> getEnvelopeDocuments() {
		return envelopeDocuments;
	}

	/**
	 * @param envelopeDocuments the envelopeDocuments to set
	 */
	public void setEnvelopeDocuments(List<Document> envelopeDocuments) {
		this.envelopeDocuments = envelopeDocuments;
	}

	/**
	 * @return the envelopeId
	 */
	public String getEnvelopeId() {
		return envelopeId;
	}

	/**
	 * @param envelopeId the envelopeId to set
	 */
	public void setEnvelopeId(String envelopeId) {
		this.envelopeId = envelopeId;
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

	/**
	 * @return the createdDateTime
	 */
	public String getCreatedDateTime() {
		return createdDateTime;
	}

	/**
	 * @param createdDateTime the createdDateTime to set
	 */
	public void setCreatedDateTime(String createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	/**
	 * @return the lastModifiedDateTime
	 */
	public String getLastModifiedDateTime() {
		return lastModifiedDateTime;
	}

	/**
	 * @param lastModifiedDateTime the lastModifiedDateTime to set
	 */
	public void setLastModifiedDateTime(String lastModifiedDateTime) {
		this.lastModifiedDateTime = lastModifiedDateTime;
	}

	/**
	 * @return the sentDateTime
	 */
	public String getSentDateTime() {
		return sentDateTime;
	}

	/**
	 * @param sentDateTime the sentDateTime to set
	 */
	public void setSentDateTime(String sentDateTime) {
		this.sentDateTime = sentDateTime;
	}

	/**
	 * @return the statusChangedDateTime
	 */
	public String getStatusChangedDateTime() {
		return statusChangedDateTime;
	}

	/**
	 * @param statusChangedDateTime the statusChangedDateTime to set
	 */
	public void setStatusChangedDateTime(String statusChangedDateTime) {
		this.statusChangedDateTime = statusChangedDateTime;
	}

	@Override
	public String toString() {
		return "Envelope [createdDateTime=" + createdDateTime + ", envelopeDocuments=" + envelopeDocuments
				+ ", envelopeId=" + envelopeId + ", emailSubject=" + emailSubject + ", lastModifiedDateTime="
				+ lastModifiedDateTime + ", recipients=" + recipients + ", sentDateTime=" + sentDateTime + ", status="
				+ status + ", statusChangedDateTime=" + statusChangedDateTime + "]";
	}



	
}
