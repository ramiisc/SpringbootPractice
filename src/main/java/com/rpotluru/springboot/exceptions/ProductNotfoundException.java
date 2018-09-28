package com.rpotluru.springboot.exceptions;

public class ProductNotfoundException  extends RuntimeException{

	private static final long serialVersionUID = 1L;
	private static String message = "Product not found";
	public ProductNotfoundException(String message) {
		super(message);
	}
	
	public ProductNotfoundException() {
		super(message);
	}

	public String getMessage() {
		return "Product not found ";
	}
	
	

}
