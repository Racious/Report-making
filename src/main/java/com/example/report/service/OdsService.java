package com.example.report.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.jopendocument.dom.spreadsheet.SpreadSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.report.dao.EmployeeDao;
import com.example.report.vo.Employee;

@Service
public class OdsService {

	@Autowired
	EmployeeDao employeeDao;
	
	public void createReport() throws IOException {
		System.out.println("create ods Report....");
		
		final Object[][] data = new Object[7][2];
		data[0] = new Object[] { "January", 1 };
		data[1] = new Object[] { "February", 3 };
		data[2] = new Object[] { "March", 8 };
		data[3] = new Object[] { "April", 10 };
		data[4] = new Object[] { "May", 15 };
		data[5] = new Object[] { "June", 18 };
		data[6] = new Object[] { "中文", "中文字" };

//		String[] columns = new String[] { "Month", "Temp" };
		List<Employee> list=employeeDao.getAll();
		
		String[] columns = new String[] {"ID","名子","電話"};
		
		Object[][] data2=new Object[list.size()][3];
		for (int i = 0; i < list.size(); i++) {
			data2[i] = new Object[] { list.get(i).getId(), list.get(i).getName(), list.get(i).getPhone(), };
		}

		TableModel model = new DefaultTableModel(data2, columns); 
		
		    
		// Save the data to an ODS file and open it.
		final File file = new File("temperature.ods");
		SpreadSheet.createEmpty(model).saveAs(file);
	
	}
}
