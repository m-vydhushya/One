package com.spring.one.service;


import com.spring.one.vo.EmployeeVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {
    void createEmployee(EmployeeVO employeeVO);
    String checkHealth();
    EmployeeVO getEmployeeById(Long id);
    List<EmployeeVO> getAllEmployees();

}
