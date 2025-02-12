package com.spring.one.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.one.eo.EmployeeEO;



	
@Repository
public interface DAO extends JpaRepository<EmployeeEO, Long> {

}

