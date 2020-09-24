package com.rgp.de.beans;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EmbeddedDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("signerName")
	private String signerName;

	@JsonProperty("viewUrl")
	private String viewUrl;

	/**
	 * @return the signerName
	 */
	public String getSignerName() {
		return signerName;
	}

	/**
	 * @param signerName the signerName to set
	 */
	public void setSignerName(String signerName) {
		this.signerName = signerName;
	}

	/**
	 * @return the viewUrl
	 */
	public String getViewUrl() {
		return viewUrl;
	}

	/**
	 * @param viewUrl the viewUrl to set
	 */
	public void setViewUrl(String viewUrl) {
		this.viewUrl = viewUrl;
	}

	@Override
	public String toString() {
		return "EmbeddedDTO [signerName=" + signerName + ", viewUrl=" + viewUrl + "]";
	}

}
