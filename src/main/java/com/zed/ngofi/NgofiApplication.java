package com.zed.ngofi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class NgofiApplication {

	public static void main(String[] args) {
		SpringApplication.run(NgofiApplication.class, args);
	}

	@GetMapping("/practice")
	public String practice(Model model){

		String message = "Basta ito na toh";
		model.addAttribute("message", message);

		Student stud = new Student(2402577, "Zed", "Matulin", "Rulloda", "Jr.", "male", "2002-12-08", "Baccuit Norte, Bauang, La Union");
		model.addAttribute("another", stud);

		return "practice";
	}

}
