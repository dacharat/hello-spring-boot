package com.example.demo.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Course;
import com.example.demo.service.CourseService;

@RestController
@RequestMapping("api/courses")
public class CourseController {
	@Autowired
	private CourseService courseService;

	@GetMapping
	public List<Course> getCourses() {
		return courseService.getCourses();
	}

	@GetMapping(path = "{courseId}")
	public Course getCourseById(@PathVariable("courseId") UUID courseId) {
		return courseService.getCourseById(courseId);
	}

	@PostMapping
	public int createCourse(@RequestBody @Valid Course course) {
		return courseService.createCourse(course);
	}
}
