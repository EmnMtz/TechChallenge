package com.techchallenge.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer orderNumber;
	
	private String orderTrackingNumber;
	private String orderItemNumber;
	private Integer orderItemQuantity;
	private Date orderDate;
	private Date orderUpdateDate;
	private String orderComment;
	private String orderStatus;
	
	
	public Integer getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getOrderTrackingNumber() {
		return orderTrackingNumber;
	}
	public void setOrderTrackingNumber(String orderTrackingNumber) {
		this.orderTrackingNumber = orderTrackingNumber;
	}
	public String getOrderItemNumber() {
		return orderItemNumber;
	}
	public void setOrderItemNumber(String orderItemNumber) {
		this.orderItemNumber = orderItemNumber;
	}
	public Integer getOrderItemQuantity() {
		return orderItemQuantity;
	}
	public void setOrderItemQuantity(Integer orderItemQuantity) {
		this.orderItemQuantity = orderItemQuantity;
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
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	
}
