package com.example.demo.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Course;

@Repository
public class CourseDataAccessService implements CourseDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int createCourse(Course course) {
		UUID courseId = UUID.randomUUID();
		String sql = "INSERT INTO course ( course_id,  name,  description,  department,  teacher_name) VALUES (?, ?, ?, ?, ?)";
		return jdbcTemplate.update(sql, courseId, course.getName(), course.getDescription(), course.getDepartment(),
				course.getTeacherName());
	}

	@Override
	public List<Course> getCourses() {
		String sql = "SELECT course_id, name, description, department, teacher_name FROM course";

		return jdbcTemplate.query(sql, mapCourseFomDb());
	}

	@Override
	public Optional<Course> getCourseById(UUID id) {
		final String sql = "SELECT course_id, name, description, department, teacher_name FROM course WHERE course_id = ?";
		Course course = jdbcTemplate.queryForObject(sql, new Object[] {id}, (resultSet, i) -> {
			UUID courseId = UUID.fromString(resultSet.getString("course_id"));
			String name = resultSet.getString("name");
			String description = resultSet.getString("description");
			String department = resultSet.getString("department");
			String teacherName = resultSet.getString("teacher_name");
			return new Course(courseId, name, description, department, teacherName);
		});

		return Optional.ofNullable(course);
	}

	private RowMapper<Course> mapCourseFomDb() {
		return (resultSet, i) -> {
			String courseIdStr = resultSet.getString("course_id");
			UUID courseId = UUID.fromString(courseIdStr);

			String name = resultSet.getString("name");
			String description = resultSet.getString("description");
			String department = resultSet.getString("department");

			String teacherName = resultSet.getString("teacher_name");
			return new Course(courseId, name, description, department, teacherName);
		};
	}
}
