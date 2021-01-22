package com.example.report.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.report.vo.Employee;


@Repository
public class EmployeeDao {

	 public static List<Employee> employees = null;
	
	  static {
		  employees = new ArrayList<>();
		  employees.add(new Employee(1001, "Ryan","02-2222222"));
		  employees.add(new Employee(1002, "Tony","02-1111111"));
		  employees.add(new Employee(1003, "Wilson", "0912345678"));
	  }
	  public List<Employee> getAll(){
		  return employees;
	  }
}
