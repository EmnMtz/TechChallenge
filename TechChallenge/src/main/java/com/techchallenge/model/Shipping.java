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
@Table(name = "shipping")
public class Shipping {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer shippingNumber;

	private String shippingTrackingNumber;
	private Date shippingDate;
	private Date shippingUpdateDate;
	private String shippingComment;
	private String shippingStatus;

	public Integer getShippingNumber() {
		return shippingNumber;
	}

	public void setShippingNumber(Integer shippingNumber) {
		this.shippingNumber = shippingNumber;
	}

	public String getShippingTrackingNumber() {
		return shippingTrackingNumber;
	}

	public void setShippingTrackingNumber(String shippingTrackingNumber) {
		this.shippingTrackingNumber = shippingTrackingNumber;
	}

	public Date getShippingDate() {
		return shippingDate;
	}

	public void setShippingDate(Date shippingDate) {
		this.shippingDate = shippingDate;
	}

	public Date getShippingUpdateDate() {
		return shippingUpdateDate;
	}

	public void setShippingUpdateDate(Date shippingUpdateDate) {
		this.shippingUpdateDate = shippingUpdateDate;
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
