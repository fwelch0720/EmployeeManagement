package com.binary.employeeManagement.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.binary.employeeManagement.models.Employee;

@Repository
public class EmployeeRepo {
	
	
	private final List <Employee> empList = new ArrayList<>();
	private int idCount=1;
	
	public EmployeeRepo() {
		Employee emp1 =new Employee();
		emp1.setId(54);
		emp1.setName("Fred");
		emp1.setRole("Manager");
		emp1.setDepartment("IT");
		
		Employee emp2 =new Employee();
		emp2.setId(34);
		emp2.setName("Tom");
		emp2.setRole("Sales");
		emp2.setDepartment("HR");
		
		Employee emp3 =new Employee();
		emp3.setId(55);
		emp3.setName("Dale");
		emp3.setRole("Janitor");
		emp3.setDepartment("Custodial");
		
		Employee emp4 =new Employee();
		emp4.setId(23);
		emp4.setName("Hank");
		emp4.setRole("CEO");
		emp4.setDepartment("Corp");
		
		empList.add(emp1);
		empList.add(emp2);
		empList.add(emp3);
		empList.add(emp4);
		
	}
	
	
	
	public void addEmployee(Employee emp) {
		emp.setId(idCount);
		empList.add(emp);
		idCount++;
		
		
	}
	
	public List<Employee> getAllEmployees(){
		return empList;
	}
	
	public Optional<Employee> getEmployeeById(int id){
		return empList.stream().filter((employee -> employee.getId() == id)).findFirst();
	}
	
	public void update(Employee updatedEmployee) {
		for(int i =0; i <empList.size(); i++){
	           if(empList.get(i).getId().equals(updatedEmployee.getId())){
	               empList.set(i, updatedEmployee);
	               break;
	           }
	       }
	}
	
	 public void removeEmployeeById(Integer id){

	        for(int i =0; i <empList.size(); i++){
	            if(empList.get(i).getId() == id){
	                  empList.remove(i);
	                  break;
	            }
	        }
	    }
	
	

}
