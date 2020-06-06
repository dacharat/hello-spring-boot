package com.example.demo.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.CourseDao;
import com.example.demo.model.Course;

@Service
public class CourseService {
	@Autowired
	private CourseDao courseDao;

	public List<Course> getCourses() {
		return courseDao.getCourses();
	}

	public Course getCourseById(UUID id) {
		return  courseDao.getCourseById(id).orElse(null);
	}

	public int createCourse(Course course) {
		return courseDao.createCourse(course);
	}
}
