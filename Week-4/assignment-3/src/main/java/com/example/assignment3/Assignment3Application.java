package com.example.assignment3;

import com.example.assignment3.entity.User;
import com.example.assignment3.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@SpringBootApplication
@Controller
public class Assignment3Application {

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/")
	public String home(@RequestParam String email, @RequestParam String password, Model model) {
		// check if email is registered
		if (userRepository.existsByEmail(email)){
			model.addAttribute("signupError", "Email is already registered");
		}

		//find user by email and password
		User user = userRepository.findByEmailAndPassword(email, password); // Use the correct User class
		if (user == null) {
			model.addAttribute("signinError", "Invalid");
		}

		return "home.html"; // Return the name of the HTML view for the home page
	}

	@GetMapping("/member.html")
	public String memberPage(@RequestParam String email, @RequestParam String password, Model model) {
		model.addAttribute("Success", "Success");

		// Create a new user
		User user = new User(); // Use the correct User class
		user.setEmail(email);
		user.setPassword(password);
		userRepository.save(user);

		return "member.html"; // Return the name of the HTML view for the member page
	}

	public static void main(String[] args) {
		SpringApplication.run(Assignment3Application.class, args);
	}

}
