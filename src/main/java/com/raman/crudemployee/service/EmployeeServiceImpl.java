package com.raman.crudemployee.service;

import java.util.List;
import java.util.Map;

import com.raman.crudemployee.model.Employee;

public interface EmployeeServiceImpl {

	// view all employees
	public List<Employee> viewAll();
	
	//add or update new employee
	public Employee addEmployee(Employee emp);

	//find by id
	public Employee getEmployeeById(Long id); 
	
	//delete by id
	public Map<String,Boolean> deleteById(Long id);
	
	//Employee List Gender Wise
	public List<Employee> findAllGenderWise(String gender);
	
	//Count of Employee Gender wise
	public Map<String, Long> findAllGenderWise();
	
	
	
}

