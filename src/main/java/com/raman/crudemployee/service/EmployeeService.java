package com.raman.crudemployee.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.criteria.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raman.crudemployee.exception.ResourceNotFoundException;
import com.raman.crudemployee.model.Employee;
import com.raman.crudemployee.repository.EmployeeRepository;

@Service
public class EmployeeService implements EmployeeServiceImpl {
	List employeeList = new ArrayList();
	public void empRep() {
	
	
	employeeList.add(new Employee(1, "Jiya Brein", 32, "Female", "HR", 2011, 25000.0));
	employeeList.add(new Employee(2, "Paul Niksui", 25, "Male", "Sales And Marketing", 2015, 13500.0));
	employeeList.add(new Employee(3, "Martin Theron", 29, "Male", "Infrastructure", 2012, 18000.0));
	employeeList.add(new Employee(4, "Murali Gowda", 28, "Male", "Product Development", 2014, 32500.0));
	employeeList.add(new Employee(5, "Nima Roy", 27, "Female", "HR", 2013, 22700.0));
	employeeList.add(new Employee(6, "Iqbal Hussain", 43, "Male", "Security And Transport", 2016, 10500.0));
	employeeList.add(new Employee(7, "Manu Sharma", 35, "Male", "Account And Finance", 2010, 27000.0));
	employeeList.add(new Employee(8, "Wang Liu", 31, "Male", "Product Development", 2015, 34500.0));
	employeeList.add(new Employee(9, "Amelia Zoe", 24, "Female", "Sales And Marketing", 2016, 11500.0));
	employeeList.add(new Employee(10, "Jaden Dough", 38, "Male", "Security And Transport", 2015, 11000.5));
	employeeList.add(new Employee(11, "Jasna Kaur", 27, "Female", "Infrastructure", 2014, 15700.0));
	employeeList.add(new Employee(12, "Nitin Joshi", 25, "Male", "Product Development", 2016, 28200.0));
	employeeList.add(new Employee(13, "Jyothi Reddy", 27, "Female", "Account And Finance", 2013, 21300.0));
	employeeList.add(new Employee(14, "Nicolus Den", 24, "Male", "Sales And Marketing", 2017, 10700.5));
	employeeList.add(new Employee(15, "Ali Baig", 23, "Male", "Infrastructure", 2018, 12700.0));
	employeeList.add(new Employee(16, "Sanvi Pandey", 26, "Female", "Product Development", 2015, 28900.0));
	employeeList.add(new Employee(17, "Anuj Chettiar", 31, "Male", "Product Development", 2012, 35700.0));
	
	}
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private EntityManager entityManager;
	
	@Override
	public List<Employee> viewAll() {
	try {
		empRep();
		employeeRepository.saveAll(employeeList);
		return employeeRepository.findAll();
	
	} catch (NullPointerException e) {
		throw new ResourceNotFoundException("***********No Records Found in Database" + e.getMessage());
	}
	}

	@Override
	public Employee addEmployee(Employee emp) {
		
		return employeeRepository.save(emp);
	}

	@Override
	public Employee getEmployeeById(Long id) {
		return employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee does not exit with id :"+id));
	}

	@Override
	public Map<String,Boolean> deleteById(Long id) {
			if (!employeeRepository.existsById(id))
				throw new ResourceNotFoundException("Id does not exists");
		
			employeeRepository.deleteById(id);
			Map<String,Boolean> response = new HashMap<>();
			response.put("Deleted",Boolean.TRUE);
			return response;
	}
	//Employee List Gender Wise
	@Override
	public List<Employee> findAllGenderWise(String Gender) {
		return employeeRepository.findAllByGender(Gender);
		
	}
	// COUNT OF EMPLOYEE GENDER WISE
	public Map<String, Long> findAllGenderWise() {
		
	List<Employee> employeeList = employeeRepository.findAll(); 
		
		Map<String, Long> empGender = employeeList.stream().collect(Collectors
				.groupingBy(Employee::getGender, Collectors.counting()));
		return empGender;
	}

	// list of Employee DepartmentWise
	public List<Employee> findByDepartmentWise(String dept) {
		
		return employeeRepository.findAllByDepartment(dept);
	}
	// Count of Total Male and Female in the Organisation
	public Map<String, Long> countByDepartmentWise(String dept) {
		List<Employee> employeeList= employeeRepository.findAllByDepartment(dept);
		Map<String, Long> empGender = employeeList.stream().collect(Collectors
				.groupingBy(Employee::getGender, Collectors.counting()));
		return empGender;
	}
	
	//List Of Department Names
	public List ViewAllDepartment() {
		List<Employee> emp = employeeRepository.findAll();
		List department = emp.stream().map(Employee::getDepartment).distinct().sorted().toList();
		return department;
	}

	//List Of Employees Gender wise of a Department
	public List<Employee> ViewEmployeeGenderWiseInDepartment(String gender, String dept) {
		List<Employee> empList = employeeRepository.findAllByDepartment(dept);
		List<Employee> empGender = empList.stream().filter(e-> e.getGender().equals(gender)).toList();
		return empGender;
	}
	//Average of Employee in organization
	public Map<String, Double> ViewAverageAgeAll() {
		List<Employee> empList = employeeRepository.findAll();
		Map<String,Double> empAvg = empList.stream().collect(Collectors.groupingBy(Employee::getGender, Collectors.averagingInt(Employee::getAge)));
		return empAvg;
	}
	//Average of Employee Gender wise in a Department
	public Map<String, Double> ViewAverageAgeByDept(String dep) {
		List<Employee> empList = employeeRepository.findAll();
		Map<String,Double> empAvg = empList.stream().filter(e->e.getDepartment().equals(dep)).collect(Collectors.groupingBy(Employee::getGender, Collectors.averagingInt(Employee::getAge)));
		return empAvg;
	}
	// Employee Max Salary in organisation
	public Optional<Employee> ViewEmplMaxSal() {
		List<Employee> empList = employeeRepository.findAll();
		Optional<Employee> empMaxSal= empList.stream().collect(Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary)));
		return empMaxSal;
	}
	//Employe Max Salary in a Department
	public Optional<Employee> ViewEmplMaxSal(String dept) {
		List<Employee> empList = employeeRepository.findAll();
		Optional<Employee> empMaxSal= empList.stream()
				.filter(e -> e.getDepartment().equals(dept))
				.collect(Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary)));
		return empMaxSal;
		
	
	}

	public List<Employee> ViewEmplYoj(Integer yroj) {
		return employeeRepository.findAllByYearOfJoiningGreaterThanEqual(yroj);
	}

	public List ViewEmplYoj() {
		
		List<Employee> emp = employeeRepository.findAll();
		List yojList = emp.stream().map(Employee::getYearOfJoining).distinct().sorted().toList();
		return yojList;
	}
	//youngest employee
	public Optional<Employee> ViewEmplYoungest() 
	{
		List<Employee> emp = employeeRepository.findAll();
		Optional<Employee> emp1 = emp.stream().min(Comparator.comparingInt(Employee::getAge));
		return emp1;
	}
	// youngest male or female
	public Optional<Employee> ViewEmplYoungest(String gender) 
	{
		List<Employee> emp = employeeRepository.findAll();
		Optional<Employee> emp1 = emp.stream()
				.filter(e-> e.getGender().equals(gender))
				.min(Comparator.comparingInt(Employee::getAge));
		return emp1;
	}
	// younges male or female or all in a dept
	public Optional<Employee> ViewEmplYoungest(String gender,String dept) 
	{
		Optional<Employee> emp1; 
		List<Employee> emp = employeeRepository.findAll();
		if(gender.equals("ALL")) {
		emp1 = emp.stream()
					.filter(e-> e.getDepartment().equals(dept))
					.min(Comparator.comparingInt(Employee::getAge));
			
		}else {
		emp1 = emp.stream()
				.filter(e-> e.getGender().equals(gender) && e.getDepartment().equals(dept))
				.min(Comparator.comparingInt(Employee::getAge));
		}
		return emp1;
	}
	
	// Count of Total Male and Female in the Department
		public Map<String, Long> countByDepartmentWise2(String dept) {
			List<Employee> employeeList= employeeRepository.findAllByDepartment(dept);
			Map<String, Long> empGender = employeeList.stream().collect(Collectors
					.groupingBy(Employee::getGender, Collectors.counting()));
			return empGender;
		}


	
	



	



	
}
