package com.techchallenge.model;

import java.util.Date;

import jakarta.validation.constraints.NotEmpty;

public class OrderDTO {

	
	@NotEmpty
	private Integer orderItemNumber;
	
	@NotEmpty
	private Integer orderItemQuantity;

	private String orderStatus;
	private String orderTrackingNumber;
	private Date orderDate;
	private Date orderUpdateDate;
	private String orderComment;
	private String orderItemDescription;
	
	
	public Integer getOrderItemNumber() {
		return orderItemNumber;
	}
	public void setOrderItemNumber(Integer orderItemNumber) {
		this.orderItemNumber = orderItemNumber;
	}
	public Integer getOrderItemQuantity() {
		return orderItemQuantity;
	}
	public void setOrderItemQuantity(Integer orderItemQuantity) {
		this.orderItemQuantity = orderItemQuantity;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getOrderTrackingNumber() {
		return orderTrackingNumber;
	}
	public void setOrderTrackingNumber(String orderTrackingNumber) {
		this.orderTrackingNumber = orderTrackingNumber;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public Date getOrderUpdateDate() {
		return orderUpdateDate;
	}
	public void setOrderUpdateDate(Date orderUpdateDate) {
		this.orderUpdateDate = orderUpdateDate;
	}
	public String getOrderComment() {
		return orderComment;
	}
	public void setOrderComment(String orderComment) {
		this.orderComment = orderComment;
	}
	public String getOrderItemDescription() {
		return orderItemDescription;
	}
	public void setOrderItemDescription(String orderItemDescription) {
		this.orderItemDescription = orderItemDescription;
	}
	


	
}
