package com.techchallenge.model;

public class ShippingDTO {
	
	private String shippingTrackingNumber;
	private String shippingDate;
	private String shippingUpdateDate;
	private String shippingComment;
	private String shippingStatus;
	
	
	public String getShippingTrackingNumber() {
		return shippingTrackingNumber;
	}
	public void setShippingTrackingNumber(String shippingTrackingNumber) {
		this.shippingTrackingNumber = shippingTrackingNumber;
	}
	public String getShippingDate() {
		return shippingDate;
	}
	public void setShippingDate(String shippingDate) {
		this.shippingDate = shippingDate;
	}
	public String getShippingUpdateDate() {
		return shippingUpdateDate;
	}
	public void setShippingUpdateDate(String shippingUpdateDat) {
		this.shippingUpdateDate = shippingUpdateDat;
	}
	public String getShippingComment() {
		return shippingComment;
	}
	public void setShippingComment(String shippingComment) {
		this.shippingComment = shippingComment;
	}
	public String getShippingStatus() {
		return shippingStatus;
	}
	public void setShippingStatus(String shippingStatus) {
		this.shippingStatus = shippingStatus;
	}
}
