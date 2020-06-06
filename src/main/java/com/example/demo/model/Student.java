package com.example.demo.model;

import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Student {
	@JsonProperty("studentId")
	private UUID studentId;

	@NotBlank
	@JsonProperty("firstName")
	private String firstName;

	@NotBlank
	@JsonProperty("lastName")
	private String lastName;

	@NotBlank
	@JsonProperty("email")
	private String email;

	@NotNull
	@JsonProperty("gender")
	private Gender gender;

	@Override
	public String toString() {
		return "Student{" + "studentId=" + studentId + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\''
				+ ", email='" + email + '\'' + ", gender=" + gender + '}';
	}

	public enum Gender {
		MALE, FEMALE
	}

}
