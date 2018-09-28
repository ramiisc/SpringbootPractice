package com.rpotluru.springboot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.rpotluru.springboot.model.Product;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringbootPracticeApplication.class)
@WebAppConfiguration
public class ProductServiceControllerTest extends AbstractTest{
	protected MockMvc mvc;
	@Autowired
	WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void getProductsList() throws Exception {
		String uri = "/products";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Product[] productlist = super.mapFromJson(content, Product[].class);
		assertTrue(productlist.length > 0);
	}

	@Test
	public void createProduct() throws Exception {
		String uri = "/products";
		Product product = new Product();
		product.setId("3");
		product.setName("Ginger");
		String inputJson = super.mapToJson(product);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(201, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "Product is created successfully");
	}

	@Test
	public void updateProduct() throws Exception {
		String uri = "/products/2";
		Product product = new Product();
		product.setName("Lemon");
		String inputJson = super.mapToJson(product);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "Product is updated successfully");
	}

	@Test
	public void deleteProduct() throws Exception {
		String uri = "/products/2";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "Product is deleted successfully");
	}

}
