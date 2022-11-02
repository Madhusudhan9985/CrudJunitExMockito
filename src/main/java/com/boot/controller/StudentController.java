package com.boot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boot.entity.Student;
import com.boot.service.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {
	@Autowired
	private StudentService studentService;
	
	@PostMapping("/add")
	public Student createStudent(@RequestBody Student student) {
		return studentService.createStudent(student);
		
	}
	
	@GetMapping("/")
	public List<Student> getAllStudents() {
		return studentService.getStudents();
		
	}
	
	@GetMapping("/{studentId}")
	public Student getStudentById(@PathVariable (value="studentId") Integer studentId) {
		return this.studentService.getStudentById(studentId);
	}
	
	@DeleteMapping("/{studentId}")
	public String deleteStudentById(@PathVariable Student studentId) {
		studentService.deleteStudentById(studentId);
		return "Deleted";
	}
	
	@PutMapping("/{studentId}")
	public Student updateStudentById(@PathVariable Integer studentId) {
		Student student=studentService.getStudentById(studentId);
		student.setStudentName("Ajay");
		return studentService.updateStudentById(student);
	}
}


