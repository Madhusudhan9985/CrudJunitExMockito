package com.boot;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.boot.dao.StudentDao;
import com.boot.entity.Student;
import com.boot.service.StudentService;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class StudentServiceTest {


	@MockBean
	private StudentDao studentDao;
	@Autowired
	private StudentService studentService;

	
	@Test
	@Order(1)
	public void saveUserTest() throws Exception{
		Student student = new Student(10,"Madhu");
		when(studentDao.save(student)).thenReturn(student);
		assertEquals(student, studentService.createStudent(student));
		
	}

	@Test
	@Order(2)
	public void testGetAllStudent() throws Exception {
		when(studentDao.findAll()).thenReturn(Stream
				.of(new Student(376, "Madhu"), new Student(958, "Siva")).collect(Collectors.toList()));
		assertEquals(2, studentService.getStudents().size());
	}

	@Test
	@Order(3)
	public void testGetStudentById() throws Exception {
		Student student = studentDao.findById(16666).get();
		assertEquals(16613, student.getStudentId());

	}

	@Test
	@Order(4)
	public void testUpdateStudentById() throws Exception {
		Student student =studentDao.findById(16606).get();
		student.setStudentName("Piyu");
		studentDao.save(student);
		assertNotNull("amruta",studentDao.findById(16606).get().getStudentName());
		
	}
	
	@Test
	@Order(5)
	public void testDeleteStudentById() throws Exception {
		Student student = new Student(5, "Vivek");
		studentService.deleteStudentById(student);
		verify(studentDao, times(1)).delete(student);
	}

}
