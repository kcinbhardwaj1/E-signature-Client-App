package com.rgp.de.beans;

import java.util.List;

public class EnvelopeDTO {

	private String emailSubject;
	private List<String> signerName;
	private List<String> signerEmail;
	private List<String> ccName;
	private List<String> ccEmail;
	private String templateId;
	private List<Signer> signers;
	private List<CarbonCopy> cc;

	/**
	 * @return the ccName
	 */
	public List<String> getCcName() {
		return ccName;
	}

	/**
	 * @param ccName the ccName to set
	 */
	public void setCcName(List<String> ccName) {
		this.ccName = ccName;
	}

	/**
	 * @return the ccEmail
	 */
	public List<String> getCcEmail() {
		return ccEmail;
	}

	/**
	 * @param ccEmail the ccEmail to set
	 */
	public void setCcEmail(List<String> ccEmail) {
		this.ccEmail = ccEmail;
	}

	/**
	 * @return the signers
	 */
	public List<Signer> getSigners() {
		return signers;
	}

	/**
	 * @param signers the signers to set
	 */
	public void setSigners(List<Signer> signers) {
		this.signers = signers;
	}

	/**
	 * @return the cc
	 */
	public List<CarbonCopy> getCc() {
		return cc;
	}

	/**
	 * @param cc the cc to set
	 */
	public void setCc(List<CarbonCopy> cc) {
		this.cc = cc;
	}

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

	public String getEmailSubject() {
		return emailSubject;
	}

	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}

	/**
	 * @return the signerName
	 */
	public List<String> getSignerName() {
		return signerName;
	}

	/**
	 * @param signerName the signerName to set
	 */
	public void setSignerName(List<String> signerName) {
		this.signerName = signerName;
	}

	/**
	 * @return the signerEmail
	 */
	public List<String> getSignerEmail() {
		return signerEmail;
	}

	/**
	 * @param signerEmail the signerEmail to set
	 */
	public void setSignerEmail(List<String> signerEmail) {
		this.signerEmail = signerEmail;
	}

}
