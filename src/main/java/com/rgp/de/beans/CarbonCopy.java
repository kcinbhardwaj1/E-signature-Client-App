package com.rgp.de.beans;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CarbonCopy implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;

		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		CarbonCopy other = (CarbonCopy) obj;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

	@JsonProperty("deliveredDateTime")
	private String deliveredDateTime;

	@JsonProperty("deliveryMethod")
	private String deliveryMethod;

	@JsonProperty("email")
	private String email;

	@JsonProperty("name")
	private String name;

	@JsonProperty("recipientId")
	private String recipientId;

	@JsonProperty("routingOrder")
	private String routingOrder;

	@JsonProperty("status")
	private String status;

	/**
	 * @return the deliveredDateTime
	 */
	public String getDeliveredDateTime() {
		return deliveredDateTime;
	}

	/**
	 * @param deliveredDateTime the deliveredDateTime to set
	 */
	public void setDeliveredDateTime(String deliveredDateTime) {
		this.deliveredDateTime = deliveredDateTime;
	}

	/**
	 * @return the deliveryMethod
	 */
	public String getDeliveryMethod() {
		return deliveryMethod;
	}

	/**
	 * @param deliveryMethod the deliveryMethod to set
	 */
	public void setDeliveryMethod(String deliveryMethod) {
		this.deliveryMethod = deliveryMethod;
	}

	/**
	 * @return the routingOrder
	 */
	public String getRoutingOrder() {
		return routingOrder;
	}

	/**
	 * @param routingOrder the routingOrder to set
	 */
	public void setRoutingOrder(String routingOrder) {
		this.routingOrder = routingOrder;
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
	 * @return the ccName
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param ccName the ccName to set
	 */
	public void setName(String name) {
		this.name = name;
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
	 * @return the ccEmail
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param ccEmail the ccEmail to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "CarbonCopy [deliveredDateTime=" + deliveredDateTime + ", deliveryMethod=" + deliveryMethod + ", email="
				+ email + ", name=" + name + ", recipientId=" + recipientId + ", routingOrder=" + routingOrder
				+ ", status=" + status + "]";
	}

}
