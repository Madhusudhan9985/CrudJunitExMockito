package com.boot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.boot.dao.StudentDao;
import com.boot.entity.Student;
import com.boot.service.StudentService;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)

class StudentControllerTest {

	@MockBean
	private StudentDao studentDao;
	@Autowired
	private StudentService studentService;
	
	
	
	

	@Test
	@Order(1)
	public void saveUserTest() throws Exception {
		Student student = new Student(10, "Madhu");
		when(studentDao.save(student)).thenReturn(student);
		assertEquals(student, studentService.createStudent(student));

	}

	/*
	 * @Test
	 * 
	 * @Order(2) public void testGetAllStudent() throws Exception { List<Student>
	 * list = (List) studentDao.findAll(); assertThat(list).size().isGreaterThan(0);
	 * // true
	 * 
	 * }
	 */
	
	
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
		assertEquals(17000, student.getStudentId());

	}
	
	/*
	 * @Test
	 * 
	 * @Order(3) public void testGetStudentById() throws Exception { Student student
	 * = studentDao.findById(16666).get(); assertEquals(16613,
	 * student.getStudentId());
	 * 
	 * }
	 */

	 
	
	
	/*
	 * @Test public void testGetStudentById() { String getStudentId = "16666";
	 * when(studentDao.findById(studentId)) .thenReturn(Stream.of(new Student(376,
	 * "Danile")).collect(Collectors.toList())); assertEquals(1,
	 * studentService.getStudById(studentId).size()); }
	 */
	 

	@Test
	@Order(4)
	public void testUpdateStudentById() throws Exception {
		Student student = studentDao.findById(16607).get();
		student.setStudentName("Piyu");
		when(studentDao.save(student)).thenReturn(student);
		assertNotNull("Madhu", studentDao.findById(16607).get().getStudentName());

	}

	@Test
	@Order(5)
	public void testDeleteStudentById() throws Exception {
		Student student = new Student(5, "Vivek");
		studentService.deleteStudentById(student);
		verify(studentDao, times(1)).delete(student);
	}

}
