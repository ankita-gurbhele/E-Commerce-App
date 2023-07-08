package com.ecommerce.controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.model.Image;
import com.ecommerce.model.Product;
import com.ecommerce.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	private ProductService productService;
	
	
	@PreAuthorize("hasRole('Admin')")
	@PostMapping(value = "/product/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public Product addProduct(@RequestPart("product") Product product,
							  @RequestPart("imageFile") MultipartFile[] file) {
		 
		
		productService.addProduct(product);
		try {
			Set<Image> images = uploadImage(file);
			product.setProductImages(images);
			return productService.addProduct(product);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	
	public Set<Image> uploadImage(MultipartFile[] multipartFiles) throws IOException {
		Set<Image> images = new HashSet();
		
		for(MultipartFile file : multipartFiles) {
			Image image = new Image(
									file.getSize(),
									file.getOriginalFilename(),
									file.getContentType(),
									file.getBytes()); 
			images.add(image);
		}
		 return images;
	}
	
	
	@GetMapping("/product/getall")
	public List<Product> getAllProducts(@RequestParam(defaultValue = "0") int pageNumber,
										@RequestParam(defaultValue = "") String searchKey){
		return productService.getAllproducts(pageNumber,searchKey);
	}
	
	@PreAuthorize("hasRole('Admin')")
	@GetMapping("/product/get/{productId}")
	public Product getProductById(@PathVariable("productId") Integer productId) {
		return productService.getProductById(productId);
	}
	
	@PreAuthorize("hasRole('Admin')")
	@DeleteMapping("/product/delete/{id}")
	public void deleteProductDetails(@PathVariable("id") Integer productId) {
		productService.deleteProduct(productId);
	}
	
	@PreAuthorize("hasRole('User')")
	@GetMapping("/product/get/{isSingleProductCheckout}/{productId}")
	public List<Product> getProductDetails(@PathVariable("isSingleProductCheckout")boolean isSingleProductCheckout, 
								@PathVariable("proudctId")Integer productId) {
		return productService.getProductDetails(isSingleProductCheckout, productId);
	}
}
