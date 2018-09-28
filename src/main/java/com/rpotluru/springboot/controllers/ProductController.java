package com.rpotluru.springboot.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.rpotluru.springboot.model.Product;
import com.rpotluru.springboot.service.ProductService;

@RestController
public class ProductController {

	@Value("${server.port}")
	int port;

	@Value("${spring.application.name}")
	String appName;

	@Value("${spring.error:no error}")
	String error;

	@Autowired
	private ProductService productService;

	@RequestMapping(value = "/")
	@HystrixCommand(fallbackMethod = "fallback_hello", commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000") })
	public String welcome() throws InterruptedException {
	   Thread.sleep(3000);
	   return "Welcome Hystrix";
	}
	
	@RequestMapping(value = "/hello")
	public ResponseEntity<Object> hello() {
		Map<String, String> map = new HashMap<>();
		map.put("server.port", String.valueOf(port));
		map.put("appName", appName);
		map.put("error", error);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(map, HttpStatus.OK);
	}


	private String fallback_hello() {
		return "Request fails. It takes long time to response";
	}

	@RequestMapping(value = "/products")
	public ResponseEntity<Object> getProduct() {
		return new ResponseEntity<>(productService.getProducts(), HttpStatus.OK);
	}

	@RequestMapping(value = "/products/{productId}")
	public ResponseEntity<Object> getProduct(@PathVariable("productId") String id) {
		return new ResponseEntity<>(productService.getProducts(id), HttpStatus.OK);
	}

	@RequestMapping(value = "/products", method = RequestMethod.POST)
	public ResponseEntity<Object> createProduct(@RequestBody Product product) {
		productService.createProduct(product);
		return new ResponseEntity<Object>("Product is created successfully", HttpStatus.CREATED);
	}

	@RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateProduct(@PathVariable String id, @RequestBody Product product) {
		productService.updateProduct(id, product);
		return new ResponseEntity<Object>("Product is updated successfully", HttpStatus.OK);
	}

	@RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteProduct(@PathVariable String id) {
		productService.deleteProduct(id);
		return new ResponseEntity<Object>("Product is deleted successfully", HttpStatus.OK);
	}

}
