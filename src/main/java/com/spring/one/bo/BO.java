package com.spring.one.bo;

import java.util.List;
import java.util.Optional;

import com.spring.one.DTO.EmployeeDTO;
import com.spring.one.eo.EmployeeEO;
import com.spring.one.vo.EmployeeVO;
import org.springframework.stereotype.Service;


public interface BO {
	EmployeeEO save(EmployeeDTO EmployeeDTO);

    EmployeeVO findById(Long id);

	List<EmployeeVO> findAll();
}
