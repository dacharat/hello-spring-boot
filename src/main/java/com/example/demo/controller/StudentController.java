package com.example.demo.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Student;
import com.example.demo.model.StudentCourse;
import com.example.demo.service.StudentService;

@RestController
@RequestMapping("api/students")
public class StudentController {
	@Autowired
	private StudentService studentService;

	@GetMapping
	public List<Student> getAllStudents() {
		return studentService.getAllStudents();
	}

	@GetMapping(path = "{studentId}/courses")
	public List<StudentCourse> getAllCoursesForStudent(@PathVariable("studentId") UUID studentId) {
		return studentService.getAllCoursesForStudent(studentId);
	}

	@PostMapping
	public void addNewStudent(@RequestBody @Valid Student student) {
		studentService.addNewStudent(student);
	}

	@GetMapping(path = "{studentId}")
	public Student getStudent(@PathVariable("studentId") UUID studentId) {
		return studentService.getStudentById(studentId);
	}

	@PutMapping(path = "{studentId}")
	public void updateStudent(@PathVariable("studentId") UUID studentId, @RequestBody Student student) {
		studentService.updateStudent(studentId, student);
	}

	@DeleteMapping("{studentId}")
	public void deleteStudent(@PathVariable("studentId") UUID studentId) {
		studentService.deleteStudent(studentId);
	}
}
