package com.binary.employeeManagement.controllers;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.binary.employeeManagement.models.Employee;
import com.binary.employeeManagement.services.EmployeeService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {
	
	private final EmployeeService employeeService;
	
	@GetMapping({"" ,"/list" ,"employeeList"})
	public String employeePage(Model m) {
		m.addAttribute("timeline", employeeService.findAllEmployees());
		return "employee/employeeIndex";
		
	}
	
	@GetMapping("/create")
	public String createEmployeePage(Model m) {
		m.addAttribute("newEmployee", new Employee());
		return "employee/createEmployee";
		
	}
	
	@PostMapping("/create")
	public String createEmployee(@ModelAttribute @Valid Employee newEmp, Errors result) {
	if(result.hasErrors()) {
		return "redirect:/employees/create";
	}
	employeeService.addEmployee(newEmp);
	return "redirect:/employees/list";
	}
	
	@GetMapping("/update/{id}")
	public String updateEmployeePage(@PathVariable("id") Integer id, Model model) {
		Optional<Employee> optionalEmployee = employeeService.findById(id);
		Employee updatedEmployee =null;
		if(optionalEmployee.isPresent()) {
			updatedEmployee = optionalEmployee.get();
			
		}else {
			return "redirect:/employees/list";
		}
		model.addAttribute("employeeNeedToUpdate", updatedEmployee);
		System.out.println(updatedEmployee);
		return "employee/updateEmployee";
	}
	
	@PostMapping("/update/{id}")
	public String updateEmployee(@PathVariable("id") Integer id, @ModelAttribute("employeeNeedToUpdate") @Valid Employee updatedEmployee, Errors errors) {
		if(errors.hasErrors()) {
			System.out.println(errors.getAllErrors());
			return "employee/updateEmployee";
		}
		System.out.println(updatedEmployee);
        employeeService.updateEmployee(updatedEmployee);

        return "redirect:/employees/list";
		
	}
	
	 @GetMapping("/delete/{id}")
	public String deleteEmployeePage(@PathVariable("id") Integer id) {
		return "employee/deleteEmployee";
		
	} 
	
	@PostMapping("/delete/{id}")
	public String deleteEmployee(@PathVariable("id") Integer id) {
		employeeService.deleteEmployeeById(id);
		return "redirect:/employees/list";
	}
}
