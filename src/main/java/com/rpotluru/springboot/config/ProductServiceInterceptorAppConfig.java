package com.rpotluru.springboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.rpotluru.springboot.interceptor.ProductServiceInterceptor;

@SuppressWarnings("deprecation")
//@Component
public class ProductServiceInterceptorAppConfig extends WebMvcConfigurerAdapter {
	@Autowired
	ProductServiceInterceptor productServiceInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(productServiceInterceptor);
	}

}
