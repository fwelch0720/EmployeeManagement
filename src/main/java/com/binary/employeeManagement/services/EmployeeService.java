package com.binary.employeeManagement.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.binary.employeeManagement.models.Employee;
import com.binary.employeeManagement.repositories.EmployeeRepo;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class EmployeeService {

	private final EmployeeRepo repo;

	
	public void addEmployee(Employee emp) {
		repo.addEmployee(emp);
	}
	
	public List <Employee> findAllEmployees() {
		return repo.getAllEmployees();
	}
	
	public Optional<Employee> findById(int id){
		return repo.getEmployeeById(id);
	}
	
	public void updateEmployee(Employee updatedEmp) {
		repo.update(updatedEmp);
	}
	
	public void deleteEmployeeById(Integer integer) {
		repo.removeEmployeeById(integer);
	}

}
