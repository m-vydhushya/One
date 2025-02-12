package com.spring.one.bo;

import java.util.List;
import java.util.Optional;

import com.spring.one.DTO.EmployeeDTO;
import com.spring.one.mapper.EntityMapper;
import com.spring.one.vo.EmployeeVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.one.eo.EmployeeEO;
import com.spring.one.DAO.DAO;

import lombok.RequiredArgsConstructor;
@Slf4j
@Repository
@RequiredArgsConstructor
public class BOImpl implements BO {

	@Autowired
	public DAO DAO;
	@Autowired
	public EntityMapper entityMapper;

	@Override
	public EmployeeEO save(EmployeeDTO employeeDTO) {
		var employeeEO = entityMapper.toEmployeeEO(employeeDTO);
		log.info("Create Employee method called in Service");log.info("Create Employee method called in Service");		// TODO Auto-generated method stub
		return DAO.save(employeeEO);
	}

	@Override
	public EmployeeVO findById(Long id) {
		log.info("View By Id method called in BO");
		EmployeeEO employeeEO = DAO.findById(id).orElseThrow(()-> new RuntimeException("Employee not found"));
		EmployeeVO employeeVO = entityMapper.toEmployeeVO(employeeEO);
		// TODO Auto-generated method stub
		return employeeVO;
	}

	@Override
	public List<EmployeeVO> findAll() {
		// TODO Auto-generated method stub
		log.info("View All method called in Controller");
		List<EmployeeEO> employeeEOList = DAO.findAll();
		List<EmployeeVO> employeeVOList = entityMapper.toEmployeeVOList(employeeEOList);
		return employeeVOList;
	}

}


