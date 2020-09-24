package com.rgp.de.beans;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class EnvelopeResponse implements Serializable {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	@JsonProperty("envelopeId")
	private String envelopeId = null;

	@JsonProperty("status")
	private String status = null;

	@JsonProperty("statusDateTime")
	private String statusDateTime = null;

	@JsonProperty("uri")
	private String uri = null;
	
	@JsonIgnore
	boolean isTemplate;


	/**
	 * @return the isTemplate
	 */
	public boolean getIsTemplate() {
		return isTemplate;
	}

	/**
	 * @param isTemplate the isTemplate to set
	 */
	public void setTemplate(boolean isTemplate) {
		this.isTemplate = isTemplate;
	}

	/**
	 * @return the envelopeId
	 */
	public String getEnvelopeId() {
		return envelopeId;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @return the statusDateTime
	 */
	public String getStatusDateTime() {
		return statusDateTime;
	}

	/**
	 * @return the uri
	 */
	public String getUri() {
		return uri;
	}

	@Override
	public String toString() {
		return "EnvelopeResponse [envelopeId=" + envelopeId + ", status=" + status + ", statusDateTime="
				+ statusDateTime + ", uri=" + uri + "]";
	}
	
}
