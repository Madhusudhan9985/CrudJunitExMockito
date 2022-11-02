package com.boot.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.boot.entity.Student;

public interface StudentDao extends CrudRepository<Student, Integer>{
	
	

	
}
