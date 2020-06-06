package com.example.demo.model;

import java.util.UUID;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@Getter
public class Person {
	private final UUID id;

	@NotBlank
	private final String name;

	public Person(@JsonProperty("id") UUID id, @JsonProperty("name") String name) {
		this.id = id;
		this.name = name;
	}

	@Override
	public String toString() {
		return String.format("Person[%s]: %s", id.toString(), name);
	}
}
