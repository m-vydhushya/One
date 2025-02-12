package com.spring.one.service;


import com.spring.one.bo.BOImpl;
import com.spring.one.mapper.EntityMapper;
import com.spring.one.vo.EmployeeVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    public EntityMapper entityMapper;

    @Autowired
    public BOImpl BO;


    public void createEmployee(EmployeeVO employeeVO) {
        log.info("Create Employee method called in Service");
        var employeeDTO = entityMapper.toEmployeeDTO(employeeVO);
        System.out.println("employeeDTO: "+employeeDTO);
        BO.save(employeeDTO);
    }

    @Override
    public String checkHealth() {
        log.info("Health Check method called in Service");
        // TODO Auto-generated method stub
        try {
            return "Application is running and healthy!";
        } catch (Exception e) {
            throw new RuntimeException("Application health check failed", e);
        }
    }

    @Override
    public EmployeeVO getEmployeeById(Long id) {
        log.info("View By Id method called in Service");
        // TODO Auto-generated method stub
        try {
            var employeeVO = BO.findById(id);
            return employeeVO;
        }
        catch(Exception e) {
            throw new RuntimeException("Invalid id ",e);
        }

    }

    @Override
    public List<EmployeeVO> getAllEmployees() {
        log.info("View All method called in Service");
        // TODO Auto-generated method stub
        List<EmployeeVO> employeeVOList = BO.findAll();
        return employeeVOList;
    }
}
