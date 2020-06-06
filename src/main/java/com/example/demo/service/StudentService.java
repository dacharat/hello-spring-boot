package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.demo.EmailValidator;
import com.example.demo.dao.StudentDataAccessService;
import com.example.demo.exception.ApiRequestException;
import com.example.demo.model.Student;
import com.example.demo.model.StudentCourse;

@Service
public class StudentService {
	@Autowired
	private StudentDataAccessService studentDataAccessService;
	@Autowired
	private EmailValidator emailValidator;

	public List<Student> getAllStudents() {
		return studentDataAccessService.getStudents();
	}

	public Student getStudentById(UUID id) {
		return studentDataAccessService.getStudentById(id).orElse(null);
	}

	public void addNewStudent(Student student) {
		if (!emailValidator.test(student.getEmail())) {
			throw new ApiRequestException(student.getEmail() + " is not valid");
		}

		if (studentDataAccessService.isEmailTaken(student.getEmail())) {
			throw new ApiRequestException(student.getEmail() + " is taken");
		}

		studentDataAccessService.createStudent(student);
	}

	public List<StudentCourse> getAllCoursesForStudent(UUID studentId) {
		return studentDataAccessService.getAllStudentCourses(studentId);
	}

	public void updateStudent(UUID studentId, Student student) {
		Optional.ofNullable(student.getEmail()).ifPresent(email -> {
			boolean taken = studentDataAccessService.selectExistsEmail(studentId, email);
			if (!taken) {
				studentDataAccessService.updateEmail(studentId, email);
			} else {
				throw new IllegalStateException("Email already in use: " + student.getEmail());
			}
		});

		Optional.ofNullable(student.getFirstName())
				.filter(fistName -> !StringUtils.isEmpty(fistName))
				.map(StringUtils::capitalize)
				.ifPresent(firstName -> studentDataAccessService.updateFirstName(studentId, firstName));

		Optional.ofNullable(student.getLastName())
				.filter(lastName -> !StringUtils.isEmpty(lastName))
				.map(StringUtils::capitalize)
				.ifPresent(lastName -> studentDataAccessService.updateLastName(studentId, lastName));
	}

	public void deleteStudent(UUID studentId) {
		studentDataAccessService.deleteStudentById(studentId);
	}
}
