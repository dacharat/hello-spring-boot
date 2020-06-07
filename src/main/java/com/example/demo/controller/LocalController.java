package com.example.demo.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/locale")
public class LocalController {
	@Autowired
	private MessageSource messageSource;

	// /api/locale?language=th
	// /api/locale?language=fr
	@GetMapping
	public String getMessage(@RequestParam(name = "language", required = false) Locale locale) {
		return messageSource.getMessage("welcome.text", null, locale);
	}
}
