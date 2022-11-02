package com.boot.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="student_tbl")
public class Student {
 
 @Id	
 @Column(name="student_id")
 @GeneratedValue(strategy =GenerationType.AUTO)
 private int studentId;
 
 @Column(name="student_name")
 private String studentName;

public int getStudentId() {
	return studentId;
}

public void setStudentId(int studentId) {
	this.studentId = studentId;
}

public String getStudentName() {
	return studentName;
}

public void setStudentName(String studentName) {
	this.studentName = studentName;
}

public Student(int studentId, String studentName) {
	super();
	this.studentId = studentId;
	this.studentName = studentName;
}

public Student(String studentName) {
	super();
	this.studentName = studentName;
}

public Student() {
	super();
	// TODO Auto-generated constructor stub
}

@Override
public String toString() {
	return "Student [studentId=" + studentId + ", studentName=" + studentName + "]";
}


 
	
}
