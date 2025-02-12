package com.spring.one.mapper;
//
import java.util.List;
//
import org.mapstruct.Mapper;

import com.spring.one.DTO.EmployeeDTO;
import com.spring.one.eo.EmployeeEO;
import com.spring.one.vo.EmployeeVO;

//
@Mapper(componentModel = "spring")
public interface EntityMapper {

    EmployeeDTO toEmployeeDTO(EmployeeVO employeeVO);

    EmployeeEO toEmployeeEO(EmployeeDTO employeeDTO);

    EmployeeVO toEmployeeVO(EmployeeEO employeeEO);

    List<EmployeeVO> toEmployeeVOList(List<EmployeeEO> employeeEOList);



}


//bo = dto
//dao = bo
//repo = dao