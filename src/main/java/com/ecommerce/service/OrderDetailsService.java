package com.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.configuration.JwtRequestFilter;
import com.ecommerce.dao.OrderDetailsRepository;
import com.ecommerce.dao.ProductRepository;
import com.ecommerce.dao.UserRepository;
import com.ecommerce.model.OrderDetails;
import com.ecommerce.model.OrderInput;
import com.ecommerce.model.OrderProductQuantity;
import com.ecommerce.model.Product;
import com.ecommerce.model.User;

@Service
public class OrderDetailsService {
	
	private static final String ORDER_PLACED = "Placed";

	@Autowired
	private OrderDetailsRepository orderDetailsRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public void placeOrder(OrderInput orderInput) {
		List<OrderProductQuantity> orderProductQuantities = orderInput.getOrderProductQuantities();
		
		for (OrderProductQuantity orderProductQuantity : orderProductQuantities) {
			
			Product product = productRepository.findById(orderProductQuantity.getProductId()).get();
			
			String currentUser = JwtRequestFilter.CURRENT_USER;
			User user = userRepository.findById(currentUser).get();
			
			
			OrderDetails orderDetails = new OrderDetails(
												orderInput.getFullName(), 
												orderInput.getFullAddress(), 
												orderInput.getContactNumber(), 
												orderInput.getAlternateContactNumber(), 
												ORDER_PLACED, 
												product.getProductDiscountedPrice() * orderProductQuantity.getQuantity(),
												product,
												user);
					
			orderDetailsRepository.save(orderDetails);
		}
	}

}
