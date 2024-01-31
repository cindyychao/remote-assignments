package com.example.demo;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DemoApplication {

	public static void main(String[] args) {

		SpringApplication.run(DemoApplication.class, args);
	}

	@GetMapping("/")
	public String index() {
		return "<!DOCTYPE html><html><head><title>My Server</title></head>" +
				"<body><h1>Hello, My Server!</h1></body></html>";
	}

	@GetMapping("/data")
	public String handleDataRequest(@RequestParam(required = false) String number) {
		if (number == null) {
			return "Lack of Parameter";
		}

		try {
			int n = Integer.parseInt(number);
			if (n <= 0) {
				return "Wrong Parameter";
			}
			return "Result: " + calculateSum(n);
		} catch (NumberFormatException e) {
			return "Wrong Parameter";
		}
	}

	@GetMapping("/myName")
	public String myName(@CookieValue(value = "username", defaultValue = "") String username){
		if (username.isEmpty()) {
			return "<!DOCTYPE html><html><head><title>Enter Your Name</title></head>" +
					"<body><h1>Enter Your Name</h1>" +
					"<form action='/trackName' method='GET'>" +
					"<label for='usernameInput'>Enter your name:</label>" +
					"<input type='text' id='usernameInput' name='name'>" +
					"<button type='submit'>Submit</button>" +
					"</form></body></html>";
		} else {
			return "<!DOCTYPE html><html><head><title>My Name</title></head>" +
					"<body><h1>My Name</h1><p>Your Name: " + username + "</p></body></html>";
		}
	}
	@GetMapping("/trackName")
	public ResponseEntity<String> trackName(@RequestParam String name, HttpServletResponse response) {
		name = name.replaceAll("[^a-zA-Z0-9]", "");

		Cookie cookie = new Cookie("username", name);
		cookie.setPath("/");
		cookie.setMaxAge(24 * 60 * 60); // set cookie expiration time in seconds to max 24 hours
		response.addCookie(cookie);

		return ResponseEntity.ok("Your Name: " + name);
	}

	private int calculateSum(int n) {
		return n * (n + 1) / 2;
	}
}