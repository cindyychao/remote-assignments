package com.example.assignment3;

import com.example.assignment3.entity.User;
import com.example.assignment3.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@SpringBootApplication
@Controller
public class Assignment3Application {

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/")
	public String home() {
		return "home.html"; // Return the name of the HTML view for the home page
	}

	@GetMapping("/member.html")
	public String memberPage() {
		return "member.html"; // Return the name of the HTML view for the member page
	}

	@PostMapping("/signup")
	public String signUp(@RequestParam String email, @RequestParam String password, RedirectAttributes attributes) {
		try {
			// Check if the email is already registered
			if (userRepository.existsByEmail(email)) {
				attributes.addFlashAttribute("signupError", "Email is already registered");
				return "redirect:/"; // Redirect to the root path
			}

			// Validate email format
			if (!isValidEmail(email)) {
				attributes.addFlashAttribute("signupError", "Invalid email format");
				return "redirect:/"; // Redirect to the root path
			}

			// Create a new user
			User user = new User(); // Use the correct User class
			user.setEmail(email);
			user.setPassword(password);
			userRepository.save(user);

			// Set success message
			attributes.addFlashAttribute("signupSuccess", "Sign up successful. You can now sign in.");
			return "redirect:/member.html"; // Redirect to the root path
		} catch (Exception e) {
			e.printStackTrace(); // Log the exception
			attributes.addFlashAttribute("signupError", "An unexpected error occurred");
			return "redirect:/"; // Redirect to the root path
		}
	}

	@PostMapping("/signin")
	public String signIn(@RequestParam String email, @RequestParam String password, RedirectAttributes attributes) {
		try {
			// Find user by email and password
			User user = userRepository.findByEmailAndPassword(email, password); // Use the correct User class
			if (user == null) {
				attributes.addFlashAttribute("signinError", "Invalid email or password");
				return "redirect:/"; // Redirect to the root path
			}

			// Set success message
			attributes.addFlashAttribute("signinSuccess", "Sign in successful. Welcome!");
			return "redirect:/member.html"; // Redirect to the member page
		} catch (Exception e) {
			e.printStackTrace(); // Log the exception
			attributes.addFlashAttribute("signinError", "An unexpected error occurred");
			return "redirect:/"; // Redirect to the root path
		}
	}



	// Utility method to validate email format
	private boolean isValidEmail(String email) {
		// You can implement your own email validation logic here
		// For simplicity, this example checks if the email contains "@" and "."
		return email != null && email.contains("@") && email.contains(".");
	}

	public static void main(String[] args) {
		SpringApplication.run(Assignment3Application.class, args);
	}
}
