package com.example.report.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.report.service.ItextService;

@RestController
public class ItextController {

	@Autowired
	ItextService itextService;
	
	@GetMapping("/createiTextRpt")
	public String createItextReport() {
		try {
			itextService.createRpt();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		return "pdf建立";
	}
}
