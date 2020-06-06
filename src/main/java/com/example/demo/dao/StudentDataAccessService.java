package com.example.demo.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Student;
import com.example.demo.model.Student.Gender;
import com.example.demo.model.StudentCourse;

@Repository
public class StudentDataAccessService implements StudentDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int createStudent(Student student) {
		UUID studentId = UUID.randomUUID();
		String sql =
				"INSERT INTO student ( student_id,  first_name,  last_name,  email,  gender) VALUES (?, ?, ?, ?, " +
						"?::gender)";
		return jdbcTemplate.update(sql, studentId, student.getFirstName(), student.getLastName(), student.getEmail(),
				student.getGender().name().toUpperCase());
	}

	@Override
	public List<Student> getStudents() {
		String sql = "SELECT student_id, first_name, last_name, email, gender FROM student";

		return jdbcTemplate.query(sql, mapStudentFomDb());
	}

	@Override
	public Optional<Student> getStudentById(UUID id) {
		final String sql = "SELECT student_id, first_name, last_name, email, gender FROM student WHERE student_id = ?";
		Student student = jdbcTemplate.queryForObject(sql, new Object[] {id}, (resultSet, i) -> {
			UUID studentId = UUID.fromString(resultSet.getString("student_id"));
			String firstName = resultSet.getString("first_name");
			String lastName = resultSet.getString("last_name");
			String email = resultSet.getString("email");
			String gender = resultSet.getString("gender");
			return new Student(studentId, firstName, lastName, email, Gender.valueOf(gender));
		});

		return Optional.ofNullable(student);
	}

	@Override
	public int updateEmail(UUID studentId, String email) {
		String sql = "UPDATE student SET email = ? WHERE student_id = ?";
		return jdbcTemplate.update(sql, email, studentId);
	}

	@Override
	public int updateFirstName(UUID studentId, String firstName) {
		String sql = "UPDATE student SET first_name = ? WHERE student_id = ?";
		return jdbcTemplate.update(sql, firstName, studentId);
	}

	@Override
	public int updateLastName(UUID studentId, String lastName) {
		String sql = "UPDATE student SET last_name = ? WHERE student_id = ?";
		return jdbcTemplate.update(sql, lastName, studentId);
	}

	@Override
	public int deleteStudentById(UUID id) {
		String sql = "" + "DELETE FROM student " + "WHERE student_id = ?";
		return jdbcTemplate.update(sql, id);
	}

	@Override
	public List<StudentCourse> getAllStudentCourses(UUID studentId) {
		String sql = "SELECT student.student_id, course.course_id, course.name, course.description, course.department, course"
				+ ".teacher_name, student_course.start_date, student_course.end_date, "
				+ " student_course.grade FROM student JOIN student_course USING (student_id) "
				+ "JOIN course USING (course_id) WHERE student.student_id = ?";
		return jdbcTemplate.query(sql, new Object[] {studentId}, mapStudentCourseFromDb());
	}

	private RowMapper<StudentCourse> mapStudentCourseFromDb() {
		return (resultSet, i) -> new StudentCourse(UUID.fromString(resultSet.getString("student_id")),
				UUID.fromString(resultSet.getString("course_id")), resultSet.getString("name"),
				resultSet.getString("description"), resultSet.getString("department"), resultSet.getString("teacher_name"),
				resultSet.getDate("start_date").toLocalDate(), resultSet.getDate("end_date").toLocalDate(),
				Optional.ofNullable(resultSet.getString("grade")).map(Integer::parseInt).orElse(null));
	}

	@SuppressWarnings("ConstantConditions")
	public boolean isEmailTaken(String email) {
		String sql = "SELECT EXISTS (  SELECT 1  FROM student  WHERE email = ?)";
		return jdbcTemplate.queryForObject(sql, new Object[] {email}, (resultSet, i) -> resultSet.getBoolean(1));
	}

	@SuppressWarnings("ConstantConditions")
	public boolean selectExistsEmail(UUID studentId, String email) {
		String sql = "SELECT EXISTS (SELECT 1 FROM student WHERE student_id <> ? AND email = ? )";
		return jdbcTemplate.queryForObject(sql, new Object[] {studentId, email},
				(resultSet, columnIndex) -> resultSet.getBoolean(1));
	}

	private RowMapper<Student> mapStudentFomDb() {
		return (resultSet, i) -> {
			String studentIdStr = resultSet.getString("student_id");
			UUID studentId = UUID.fromString(studentIdStr);

			String firstName = resultSet.getString("first_name");
			String lastName = resultSet.getString("last_name");
			String email = resultSet.getString("email");

			String genderStr = resultSet.getString("gender").toUpperCase();
			Gender gender = Gender.valueOf(genderStr);
			return new Student(studentId, firstName, lastName, email, gender);
		};
	}
}
