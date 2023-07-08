package com.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.model.OrderInput;
import com.ecommerce.service.OrderDetailsService;

@RestController
public class OrderDetailsController {

	@Autowired
	private OrderDetailsService orderDetailsService;
	
	@PreAuthorize("hasRole('User')")
	@PostMapping("/placeorder")
	public void placeOrder(@RequestBody OrderInput orderInput) {
		orderDetailsService.placeOrder(orderInput);
	}
}
