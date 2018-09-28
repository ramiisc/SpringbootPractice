package com.rpotluru.springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {
	@RequestMapping(value = "/index")
	public String index() {
		return "index";
	}

	@RequestMapping("/locale")
	public String locale() {
		return "locale";
	}
}
