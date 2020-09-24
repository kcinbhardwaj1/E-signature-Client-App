package com.rgp.de.beans;
import org.springframework.web.multipart.MultipartFile;

public class EnvelopeDTOWithoutTemp {
	
	private String emailSubject;
	private String signerName;
	private String signerEmail;
	
	private String signerName2;
	private String signerEmail2;
	
	private String ccName;
	private String ccEmail;
	
	
	private String filePath;
	
	private MultipartFile file;
	private String recipientId;
	private String clientUserId;
	private String ccRecipientId;
	private String ccClientUserId;


	/**
	 * @return the signerName2
	 */
	public String getSignerName2() {
		return signerName2;
	}
	/**
	 * @param signerName2 the signerName2 to set
	 */
	public void setSignerName2(String signerName2) {
		this.signerName2 = signerName2;
	}
	/**
	 * @return the signerEmail2
	 */
	public String getSignerEmail2() {
		return signerEmail2;
	}
	/**
	 * @param signerEmail2 the signerEmail2 to set
	 */
	public void setSignerEmail2(String signerEmail2) {
		this.signerEmail2 = signerEmail2;
	}
	/**
	 * @return the recipientId
	 */
	public String getRecipientId() {
		return recipientId;
	}
	/**
	 * @param recipientId the recipientId to set
	 */
	public void setRecipientId(String recipientId) {
		this.recipientId = recipientId;
	}
	/**
	 * @return the clientUserId
	 */
	public String getClientUserId() {
		return clientUserId;
	}
	/**
	 * @param clientUserId the clientUserId to set
	 */
	public void setClientUserId(String clientUserId) {
		this.clientUserId = clientUserId;
	}
	/**
	 * @return the ccRecipientId
	 */
	public String getCcRecipientId() {
		return ccRecipientId;
	}
	/**
	 * @param ccRecipientId the ccRecipientId to set
	 */
	public void setCcRecipientId(String ccRecipientId) {
		this.ccRecipientId = ccRecipientId;
	}
	/**
	 * @return the ccClientUserId
	 */
	public String getCcClientUserId() {
		return ccClientUserId;
	}
	/**
	 * @param ccClientUserId the ccClientUserId to set
	 */
	public void setCcClientUserId(String ccClientUserId) {
		this.ccClientUserId = ccClientUserId;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public String getEmailSubject() {
		return emailSubject;
	}
	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}
	public String getSignerName() {
		return signerName;
	}
	public void setSignerName(String signerName) {
		this.signerName = signerName;
	}
	public String getSignerEmail() {
		return signerEmail;
	}
	public void setSignerEmail(String signerEmail) {
		this.signerEmail = signerEmail;
	}
	public String getCcName() {
		return ccName;
	}
	public void setCcName(String ccName) {
		this.ccName = ccName;
	}
	public String getCcEmail() {
		return ccEmail;
	}
	public void setCcEmail(String ccEmail) {
		this.ccEmail = ccEmail;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	@Override
	public String toString() {
		return "EnvelopeDTOWithoutTemp [emailSubject=" + emailSubject + ", signerName=" + signerName + ", signerEmail="
				+ signerEmail + ", signerName2=" + signerName2 + ", signerEmail2=" + signerEmail2 + ", ccName=" + ccName
				+ ", ccEmail=" + ccEmail + ", filePath=" + filePath + ", file=" + file + ", recipientId=" + recipientId
				+ ", clientUserId=" + clientUserId + ", ccRecipientId=" + ccRecipientId + ", ccClientUserId="
				+ ccClientUserId + "]";
	}
	
	

}
