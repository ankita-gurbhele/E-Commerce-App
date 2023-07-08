package com.ecommerce.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ecommerce.dao.ProductRepository;
import com.ecommerce.model.Product;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public Product addProduct(Product product) {
		return productRepository.save(product);
	}

	public List<Product> getAllproducts(int pageNumber, String searchKey) {
		Pageable pageable = PageRequest.of(pageNumber, 10);
		
		if (searchKey.equals("")) {
			Page<Product> page = productRepository.findAll(pageable);
			List<Product> products = page.getContent();
			return products;
		}else {
			List<Product> products = productRepository.findByProductNameContainingIgnoreCaseOrProductDescriptionContainingIgnoreCase(searchKey, searchKey, pageable);
			return products;
		}
	}

	public void deleteProduct(Integer productId) {
		productRepository.deleteById(productId);;
	}
	
	public List<Product> getProductDetails(boolean isSingleProductCheckout, Integer productId) {
		if (isSingleProductCheckout) {
			//we are going to buy a single product
			List<Product> productList = new ArrayList<Product>();
			Product product = productRepository.findById(productId).get();
			productList.add(product);
			return productList;
		}else {
			//we are going to checkout a cart
		}
		return new ArrayList<Product>();
	}

	public Product getProductById(Integer productId) {
		return productRepository.getById(productId);
	} 
	
	
}
