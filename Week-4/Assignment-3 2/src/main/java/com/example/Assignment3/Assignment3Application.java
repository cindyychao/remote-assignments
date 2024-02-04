package com.example.Assignment3;

import com.example.Assignment3.entity.User;
import com.example.Assignment3.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@SpringBootApplication
@Controller
public class Assignment3Application {

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/")
	public String home() {
		return "home"; // Return the name of the HTML view for the home page
	}

	@PostMapping("/signup")
	public RedirectView signUp(@RequestParam String email, @RequestParam String password, RedirectAttributes attributes) {
		// Check if the email is already registered
		if (userRepository.existsByEmail(email)) {
			attributes.addFlashAttribute("signupError", "Email is already registered");
			return new RedirectView("/");
		}

		// Create a new user
		User user = new User(); // Use the correct User class
		user.setEmail(email);
		user.setPassword(password);
		userRepository.save(user);

		return new RedirectView("member.html");
	}

	@PostMapping("/signin")
	public RedirectView signIn(@RequestParam String email, @RequestParam String password, RedirectAttributes attributes) {
		// Find user by email and password
		User user = userRepository.findByEmailAndPassword(email, password); // Use the correct User class
		if (user == null) {
			attributes.addFlashAttribute("signinError", "Invalid email or password");
			return new RedirectView("/");
		}
		return new RedirectView("member.html");
	}

	public static void main(String[] args) {
		SpringApplication.run(Assignment3Application.class, args);
	}
}