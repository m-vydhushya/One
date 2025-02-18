package com.spring.one.controller;

import com.spring.one.mapper.EntityMapper;
import com.spring.one.service.EmployeeService;
import com.spring.one.vo.EmployeeVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTest {

    @InjectMocks
    private EmployeeController employeeController;


    private MockMvc mockMvc;

    @Mock
    private EntityMapper entityMapper;

    @Mock
    private EmployeeService employeeService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
    }

    @Test
    public void testCreateEmployee() throws Exception{
        doNothing().when(employeeService).createEmployee(any(EmployeeVO.class));
        mockMvc.perform(post("/employee/create")
                .contentType("application/json")
                .content("{\"id\":1,\"name\":\"Vydhushya\",\"age\":21,\"salary\":25000}"))
                .andExpect(status().isCreated())
                .andExpect(content().string("Employee Created Successfully"));
        verify(employeeService,times(1)).createEmployee(any(EmployeeVO.class));
    }

    @Test
    public void testHealthCheck() throws Exception {
        when(employeeService.checkHealth()).thenReturn("Application is running and healthy!");

        mockMvc.perform(get("/employee/check"))
                .andExpect(status().isOk())
                .andExpect(content().string("Application is running and healthy!"));

        verify(employeeService, times(1)).checkHealth();
    }

    @Test
    public void testGetEmployeeById() throws Exception {
        EmployeeVO employeeVO = new EmployeeVO(1L, "Vydhushya", 21, 25000);
        when(employeeService.getEmployeeById(1L)).thenReturn(employeeVO);

        mockMvc.perform(get("/employee/view/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"name\":\"Vydhushya\",\"age\":21,\"salary\":25000}"));

        verify(employeeService, times(1)).getEmployeeById(1L);
    }

    @Test
    public void testGetAllEmployees() throws Exception {
        List<EmployeeVO> employees = List.of(
                new EmployeeVO(1L, "Vydhushya", 21, 25000),
                new EmployeeVO(2L, "Abcd", 22, 50000)
        );

        when(employeeService.getAllEmployees()).thenReturn(employees);

        mockMvc.perform(get("/employee/view/all"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":1,\"name\":\"Vydhushya\",\"age\":21,\"salary\":25000}," +
                        "{\"id\":2,\"name\":\"Abcd\",\"age\":22,\"salary\":50000}]"));

        verify(employeeService, times(1)).getAllEmployees();
    }

}
