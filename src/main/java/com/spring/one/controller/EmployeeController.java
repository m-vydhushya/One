package com.spring.one.controller;

import com.spring.one.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.spring.one.mapper.EntityMapper;
import com.spring.one.vo.EmployeeVO;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;


@AllArgsConstructor
//@NoArgsConstructor
@Slf4j
@Data
@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
		@Autowired
	    public EntityMapper entityMapper;

		@Autowired
		public EmployeeService employeeService;
	    

	    @PostMapping("/create")
	    public ResponseEntity<String> createEmployee(@Valid @RequestBody EmployeeVO employeeVO) {
//			System.out.println("employeeVO: "+employeeVO);
			log.info("Create Employee method called in Controller");
			employeeService.createEmployee(employeeVO);
	        return ResponseEntity.status(HttpStatus.CREATED).body("Employee Created Successfully");
	    }

		@GetMapping("/check")
		public ResponseEntity<String> healthCheck() {
			log.info("Health Check method called in Controller");
			return ResponseEntity.ok(employeeService.checkHealth());
		}

		@GetMapping("/view/{id}")
		public ResponseEntity<EmployeeVO> getEmployeeById(@PathVariable Long id){
			log.info("View By Id method called in Controller");
			var employeeVO = employeeService.getEmployeeById(id);
			return ResponseEntity.status(HttpStatus.OK).body(employeeVO);
		}

		@GetMapping("/view/all")
		public ResponseEntity<List<EmployeeVO>> getAllEmployees(){
			log.info("View All method called in Controller");
			var employeeVOList = employeeService.getAllEmployees();
			return ResponseEntity.ok(employeeVOList);
		}
	    
	   
}
