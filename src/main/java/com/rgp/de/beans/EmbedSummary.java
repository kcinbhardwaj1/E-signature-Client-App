package com.rgp.de.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class EmbedSummary implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("envelopeId")
	private String envelopeId;
	
	@JsonProperty("embeds")
	private List<EmbeddedDTO> embedList;
	
	@JsonIgnore
	private boolean isTemplate;
	
	/**
	 * @return the isTemplate
	 */
	public boolean getIsTemplate() {
		return isTemplate;
	}
	

	/**
	 * @param isTemplate the isTemplate to set
	 */
	public void setIsTemplate(boolean isTemplate) {
		this.isTemplate = isTemplate;
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
	 * @return the embedList
	 */
	public List<EmbeddedDTO> getEmbedList() {
		return embedList;
	}

	/**
	 * @param embedList the embedList to set
	 */
	public void setEmbedList(List<EmbeddedDTO> embedList) {
		this.embedList = embedList;
	}

	@Override
	public String toString() {
		return "EmbedSummary [envelopeId=" + envelopeId + ", embedList=" + embedList + "]";
	}

}
