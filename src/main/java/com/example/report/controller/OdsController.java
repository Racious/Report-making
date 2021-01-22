package com.example.report.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.report.service.OdsService;

@RestController
public class OdsController {

	@Autowired
	OdsService odsService;
	
	@GetMapping("/createOdsRpt")
	public String creatOdsRpt() {
		try {
			odsService.createReport();
		} catch (Exception e) {
			System.out.println(e);
		}
		return "ods建立";
	}
}
