package com.rpotluru.springboot.service;

import java.util.Collection;

import com.rpotluru.springboot.model.Product;

public interface ProductService {
	public abstract Collection<Product> getProducts();
	public abstract Product getProducts(String id);
	public abstract void createProduct(Product product);

	public abstract void updateProduct(String id, Product product);

	public abstract void deleteProduct(String id);

	

}
