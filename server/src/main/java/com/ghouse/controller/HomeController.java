package com.ghouse.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	@GetMapping("/home/demo")
	public String demo() {
		System.out.println("GetMapping Working");
		return "Postman is working";
	}
}
