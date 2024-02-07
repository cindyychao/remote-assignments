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

	@GetMapping("/index")
	public String index() {
		return "index";
	}

	@GetMapping("/member")
	public String member() {
		return "member";
	}

	@GetMapping("/home")
	public String error(@RequestParam String email, @RequestParam String password, Model model) {
		User user = userRepository.findByEmailAndPassword(email, password); // Use the correct User class
		// Check if the email is already registered
		if (userRepository.existsByEmail(email)) {
			model.addAttribute("signupError", "Email is already registered");
			return "home";
		} else if (user == null) {
			model.addAttribute("signinError", "Invalid");
			return "home";
		}
		return "home"; // Redirect to the root path
	}


//		// Create a new user
//		User user = new User(); // Use the correct User class
//		user.setEmail(email);
//		user.setPassword(password);
//		userRepository.save(user);
//		model.addAttribute("Success", "Success");
//		return "member";
//	}


	public static void main(String[] args) {
		SpringApplication.run(Assignment3Application.class, args);
	}

}