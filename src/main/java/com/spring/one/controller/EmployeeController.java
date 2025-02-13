package com.spring.one.controller;

import com.spring.one.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.spring.one.mapper.EntityMapper;
import com.spring.one.vo.EmployeeVO;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Slf4j
@Data
@RestController
@Tag(name = "Employee Retrieval", description = "Endpoints for retrieving employees")
@RequestMapping("/employee")
public class EmployeeController {
	
		@Autowired
	    public EntityMapper entityMapper;

		@Autowired
		public EmployeeService employeeService;


	@Operation(summary = "Simple hello endpoint", description = "Returns a greeting")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation")
	})
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

		@GetMapping(value = "/view/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<EmployeeVO> getEmployeeById(@PathVariable Long id){
			log.info("View By Id method called in Controller");
			var employeeVO = employeeService.getEmployeeById(id);
			System.out.println(employeeVO);
			return ResponseEntity.status(HttpStatus.OK).body(employeeVO);
		}

		@GetMapping("/view/all")
		public ResponseEntity<List<EmployeeVO>> getAllEmployees(){
			log.info("View All method called in Controller");
			var employeeVOList = employeeService.getAllEmployees();

			return ResponseEntity.ok(employeeVOList);
		}
	    
	   
}
