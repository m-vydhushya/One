package com.spring.one.service;

import com.spring.one.DTO.EmployeeDTO;
import com.spring.one.bo.BOImpl;
import com.spring.one.mapper.EntityMapper;
import com.spring.one.vo.EmployeeVO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
    @Mock
    private BOImpl BO;

    @Mock
    private EntityMapper entityMapper;

    @InjectMocks
    private EmployeeServiceImpl employeeService;



    @Test
    public void testCreateEmployee(){
        EmployeeVO employeeVO = new EmployeeVO(1L, "Vydhushya", 21, 25000);
        employeeService.createEmployee(employeeVO);
        EmployeeDTO employeeDTO = entityMapper.toEmployeeDTO(employeeVO);
        verify(BO,times(1)).save(employeeDTO);
    }

    @Test
    public void testCheckHealth(){
        String healthStatus = employeeService.checkHealth();
        Assertions.assertEquals("Application is running and healthy!",healthStatus);
    }

    @Test
    public void testGetEmployeeById(){
        EmployeeVO employeeVO = new EmployeeVO(1L, "Vydhushya", 21, 25000);
        when(BO.findById(1L)).thenReturn(employeeVO);
        EmployeeVO employeeVO1 = employeeService.getEmployeeById(1L);
        verify(BO,times(1)).findById(1L);
        Assertions.assertEquals(employeeVO,employeeVO1);
    }

    @Test
    public void testGetAllEmployees(){
        EmployeeVO employeeVO1 = new EmployeeVO(1L, "Vydhushya", 21, 25000);
        EmployeeVO employeeVO2 = new EmployeeVO(2L, "Sona", 23, 50000);

        List<EmployeeVO> employeeVOList1 = java.util.List.of(employeeVO1,employeeVO2);
        when(BO.findAll()).thenReturn(employeeVOList1);
        List<EmployeeVO> employeeVOList = employeeService.getAllEmployees();
        verify(BO,times(1)).findAll();
        Assertions.assertEquals(employeeVOList1,employeeVOList);
    }
}
