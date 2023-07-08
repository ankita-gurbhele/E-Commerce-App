package com.ecommerce.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class OrderDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer orderId;
	private String orderFullName;
	private String orderFullAddress;
	private Double contactNumber;
	private Double alternateContactNumber;
	private String orderStatus;
	private Double orderAmount;
	@OneToOne
	private Product product;
	@OneToOne
	private User user;
	
	
	
	public OrderDetails() {
		super();
	}



	public OrderDetails(String orderFullName, String orderFullAddress, Double contactNumber,
			Double alternateContactNumber, String orderStatus, Double orderAmount, Product product, User user) {
		super();
		this.orderFullName = orderFullName;
		this.orderFullAddress = orderFullAddress;
		this.contactNumber = contactNumber;
		this.alternateContactNumber = alternateContactNumber;
		this.orderStatus = orderStatus;
		this.orderAmount = orderAmount;
		this.product = product;
		this.user = user;
	}
	
	
}
