package com.raman.crudemployee.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.raman.crudemployee.model.Employee;
import com.raman.crudemployee.service.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	// View ALL Employees List
	// @RequestMapping(value= "/employees",method = RequestMethod.GET, produces
	// = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@Operation(summary = "Display List of All Employees in the Organisation" )
	@Tag(name ="Employee List", description = "Employee List Section")
	@RequestMapping(value = "/employees", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> viewAll() {
		return new ResponseEntity<List<Employee>>(employeeService.viewAll(),
				HttpStatus.OK);
	}

	// Create New Employee
	@Tag(name ="Employee NEW")
	@Operation(summary=" To ADD a new Employee in the organisation", description="Select to create New Employee")
	@RequestMapping(value = "/employees", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addEmployee(@RequestBody Employee emp) {
		return new ResponseEntity<>(employeeService.addEmployee(emp),
				HttpStatus.CREATED);
	}

	// Get Employee by ID
	@Tag(name ="Employee List")
	@Operation(summary = "Display Employee details by ID")
	@RequestMapping(value = "/employees/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
		return new ResponseEntity<Employee>(employeeService.getEmployeeById(id),
				HttpStatus.OK);
	}

	// Update Employee
	@Tag(name ="Employee Update")
	@Operation(summary ="To update an existing Employee")
	@RequestMapping(value = "/employees/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id,
			@RequestBody Employee employee) {

		Employee empbyId = employeeService.getEmployeeById(id);
		if (empbyId != null)
			employee.setId(empbyId.getId());
		return new ResponseEntity<Employee>(
				employeeService.addEmployee(employee), HttpStatus.OK);

	}

	// Delete Employee by ID
	@Tag(name ="Employee Delete")
	@Operation(summary="To delete a Employee")
	@RequestMapping(value = "/employees/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Boolean>> deleteEmployeebyId(
			@PathVariable Long id) {
		return new ResponseEntity<Map<String, Boolean>>(
				employeeService.deleteById(id), HttpStatus.OK);
	}
	// Employee List Gender Wise
	@Tag(name ="Employee Youngest")
	@Operation(summary="View Youngest Employee Gender wise => Male or Female")
	@RequestMapping(value = "/employees/gender/{gender}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Employee>> getAllGender(
			@PathVariable String gender) {
		System.out.println("\n Gender wise Employee List");
		return new ResponseEntity<List<Employee>>(
				employeeService.findAllGenderWise(gender), HttpStatus.OK);
	}
	// Count of Employees Gender Wise
	@Tag(name ="Employee Youngest")
	@Operation(summary="Count Employees Gender Wise in the Organisation")
	@RequestMapping(value = "/employees/gender", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Long>> getCountOfGenderWise() {
		return new ResponseEntity<Map<String, Long>>(
				employeeService.findAllGenderWise(), HttpStatus.OK);
	}
	// list of Employee Department Wise
	@Tag(name="Employee Department")
	@Operation(summary="List of Employees in a Department")
	@RequestMapping(value = "/employees/dept/{dept}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Employee>> getListEmployeeDepartmentWise(
			@PathVariable String dept) {
		return new ResponseEntity<List<Employee>>(
				employeeService.findByDepartmentWise(dept), HttpStatus.OK);
	}
	// Count of Employee DepartmentWise
	@Tag(name="Employee Department")
	@Operation(summary="Count of Employees Department Wise")
	@RequestMapping(value = "/employees/dept/count/{dept}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Long>> getCountEmployeeDepartmentWise(
			@PathVariable String dept) {
		return new ResponseEntity<Map<String, Long>>(
				employeeService.countByDepartmentWise(dept), HttpStatus.OK);
	}
	// List of Department
	@Tag(name="Employee Department")
	@Operation(summary="Names of the Department in the Organisation")
	@RequestMapping(value = "employees/dept", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List> getAllDepartment() {
		return new ResponseEntity<List>(employeeService.ViewAllDepartment(),
				HttpStatus.OK);
	}
	// List Of Employees Gender wise of a Department
	@Tag(name="Employee Department")
	@Operation(summary="List of Employees in the Department Gender Wise => Male or Female")
	@RequestMapping(value = "employees/dept/gender/{gender}/{dept}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Employee>> getDepartmentAndGenderWise(
			@PathVariable String gender, @PathVariable String dept) {
		return new ResponseEntity<List<Employee>>(employeeService
				.ViewEmployeeGenderWiseInDepartment(gender, dept),
				HttpStatus.OK);
	}
	// Average Age of Male and Female Employees
	@Tag(name="Employee Average")
	@Operation(summary="View Average Age of Employees in the Organisation")
	@RequestMapping(value = "employees/avg", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Double>> getAverageAge() {
		return new ResponseEntity<Map<String, Double>>(
				employeeService.ViewAverageAgeAll(), HttpStatus.OK);
	}
	// Average Age of Male and Female Employees Department Wise
	@Tag(name="Employee Average")
	@Operation(summary="View Average of Employee in a Department")
	@RequestMapping(value = "employees/avg/{dep}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Double>> getAverageAge(
			@PathVariable String dep) {
		return new ResponseEntity<Map<String, Double>>(
				employeeService.ViewAverageAgeByDept(dep), HttpStatus.OK);
	}
	// Highest Paid Salary of a employee in the Department
	@Tag(name="Employee Salary")
	@Operation(summary="View Highest Salary Employee in the Organization")
	@RequestMapping(value = "employees/sal/highest", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Optional<Employee>> getEmployeeMaxSal() {
		return new ResponseEntity<Optional<Employee>>(
				employeeService.ViewEmplMaxSal(), HttpStatus.OK);
	}
	// Highest Paid Salary of a employee in the Organization
	@Tag(name="Employee Salary")
	@Operation(summary="View Highest Salary Employee in the Department")
	@RequestMapping(value = "employees/sal/highest/{dept}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Optional<Employee>> getEmployeeMaxSal(
			@PathVariable String dept) {
		return new ResponseEntity<Optional<Employee>>(
				employeeService.ViewEmplMaxSal(dept), HttpStatus.OK);
	}
	// List Of Employees Who joined after 2015
	@Tag(name="Employee Joining")
	@Operation(summary="View Employee who joind after for eg:2015")
	@RequestMapping(value = "employees/dept/yoj/{yroj}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Employee>> getEmployeeYoj(
			@PathVariable Integer yroj) {
		return new ResponseEntity<List<Employee>>(
				employeeService.ViewEmplYoj(yroj), HttpStatus.OK);
	}
	// List of Years from Years of Joining
	@Tag(name="Employee Joining")
	@Operation(summary="View List of Employees Joining Year")
	@RequestMapping(value = "employees/yoj", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List> getEmployeeYoj() {
		return new ResponseEntity<List>(employeeService.ViewEmplYoj(),
				HttpStatus.OK);
	}
	// Get the details of youngest employee in the product development
	// department
	@Tag(name="Employee Youngest")
	@Operation(summary="View Youngest Employees in a Department")
	@RequestMapping(value = "employees/young", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Optional<Employee>> getEmployeeYoungest() {
		return new ResponseEntity<Optional<Employee>>(
				employeeService.ViewEmplYoungest(), HttpStatus.OK);
	}
	// Get the details of youngest male / female employee in the organization
	@Tag(name="Employee Youngest")
	@Operation(summary="View Youngest Male or Female in the organization")
	@RequestMapping(value = "employees/young/{gender}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Optional<Employee>> getEmployeeYoungest(@PathVariable String gender) {
		return new ResponseEntity<Optional<Employee>>(
				employeeService.ViewEmplYoungest(gender), HttpStatus.OK);
	}
	// Get the details of youngest male / female employee in the a Department
	@Tag(name="Employee Youngest")
	@Operation(summary="View Youngest Employee Male or Female Department Wise")
	@RequestMapping(value = "employees/young/{gender}/{dept}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Optional<Employee>> getEmployeeYoungest(@PathVariable String gender,@PathVariable String dept) {
	return new ResponseEntity<Optional<Employee>>(
	employeeService.ViewEmplYoungest(gender,dept), HttpStatus.OK);
	}
	
}
