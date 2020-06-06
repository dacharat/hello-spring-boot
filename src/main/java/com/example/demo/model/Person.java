package com.example.demo.model;

import java.util.UUID;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Person {
	@JsonProperty("id")
	private final UUID id;

	@NotBlank
	@JsonProperty("name")
	private final String name;

	@Override
	public String toString() {
		return String.format("Person[%s]: %s", id.toString(), name);
	}
}
