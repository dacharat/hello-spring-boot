package com.example.demo.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.demo.model.Student;
import com.example.demo.model.StudentCourse;

public interface StudentDao {
	int createStudent(Student student);

	List<Student> getStudents();

	Optional<Student> getStudentById(UUID id);

	int updateEmail(UUID studentId, String email);

	int updateFirstName(UUID studentId, String firstName);

	int updateLastName(UUID studentId, String lastName);

	int deleteStudentById(UUID id);

	List<StudentCourse> getAllStudentCourses(UUID studentId);
}
