package com.example.demo.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.demo.model.Course;

public interface CourseDao {
	int createCourse(Course course);

	List<Course> getCourses();

	Optional<Course> getCourseById(UUID id);
}
