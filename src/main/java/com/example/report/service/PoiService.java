package com.example.report.service;

import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.report.dao.EmployeeDao;
import com.example.report.vo.Employee;



@Service
public class PoiService {

	@Autowired
	private EmployeeDao employeeDao;

	public void createReport() throws Exception {
		
	    Workbook workbook = null;
	    Sheet sheet = null;
	    Row row = null;

	    List<Employee> list = employeeDao.getAll();
	    System.out.println(list.toString());
	    
	    workbook = new XSSFWorkbook();
		// 取得 Sheet
		sheet = workbook.createSheet();
		// 建立 sheet
		row = sheet.createRow(0); 
		// 建立第一列 (標題)
		row.createCell(0).setCellValue("ID");
		row.createCell(1).setCellValue("名稱");
		row.createCell(2).setCellValue("電話");
		
		for (int i = 0; i < list.size(); i++) {
			Employee employee=list.get(i);
			row=sheet.createRow(i+1);
			row.createCell(0).setCellValue(employee.getId());
			row.createCell(1).setCellValue(employee.getName());
			row.createCell(2).setCellValue(employee.getPhone());
		}
		
		FileOutputStream out=new FileOutputStream("test.xlsx");
		workbook.write(out);
		workbook.close();
		out.close();
	}
	
}
