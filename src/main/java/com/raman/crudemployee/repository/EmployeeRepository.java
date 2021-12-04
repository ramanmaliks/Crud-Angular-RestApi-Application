package com.raman.crudemployee.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.raman.crudemployee.model.Employee;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
	public List<Employee> findAllByGender(String Gender);

	public List<Employee> findAllByDepartment(String dept); 
	
	public List<Employee> findAllByYearOfJoiningGreaterThanEqual(Integer yroj);
	
	
	
	
	
	
}
