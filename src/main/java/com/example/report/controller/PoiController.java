package com.example.report.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.report.service.PoiService;


@RestController
public class PoiController {

	@Autowired
	PoiService poiService;
	
	@GetMapping("/createExcel")
	public String createExcel() {
		try {
			poiService.createReport();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "ok"; 
	}
}
