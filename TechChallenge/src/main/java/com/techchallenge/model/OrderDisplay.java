package com.techchallenge.model;

public class OrderDisplay {

	private String orderNumber;
	private String orderItemDescription;
	private Integer orderItemQuantity;
	private String orderStatus;
	private String orderTrackingNumber;
	private String orderDate;
	private String orderUpdateDate;
	private String orderComment;
	private OrderDTO orderDTO;
	
	
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getOrderItemDescription() {
		return orderItemDescription;
	}
	public void setOrderItemDescription(String orderItemDescription) {
		this.orderItemDescription = orderItemDescription;
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
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getOrderUpdateDate() {
		return orderUpdateDate;
	}
	public void setOrderUpdateDate(String orderUpdateDate) {
		this.orderUpdateDate = orderUpdateDate;
	}
	public String getOrderComment() {
		return orderComment;
	}
	public void setOrderComment(String orderComment) {
		this.orderComment = orderComment;
	}
	public OrderDTO getOrderDTO() {
		return orderDTO;
	}
	public void setOrderDTO(OrderDTO orderDTO) {
		this.orderDTO = orderDTO;
	}
}
