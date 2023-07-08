package com.ecommerce.model;

import java.util.List;

import javax.persistence.OneToOne;

import lombok.Data;

@Data
public class OrderInput {

	private String fullName;
	private String fullAddress;
	private Double contactNumber;
	private Double alternateContactNumber;
	private List<OrderProductQuantity> orderProductQuantities;
	
	
}
