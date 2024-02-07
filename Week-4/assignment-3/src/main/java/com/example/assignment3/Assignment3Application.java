package com.example.assignment3;

import com.example.assignment3.entity.User;
import com.example.assignment3.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@SpringBootApplication
@Controller
public class Assignment3Application {

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/home")
	public String index() {
		return "home";
	}

	@GetMapping("/member")
	public String member() {
		return "member";
	}

	@PostMapping("/signup")
	public String signUp(@RequestParam String email, @RequestParam String password, Model model) {
		if (userRepository.existsByEmail(email)) {
			model.addAttribute("signupError", "Email is already registered");
			return "home"; // Redirect to the sign-up page
		}
		// Create a new user
		User user = new User();
		user.setEmail(email);
		user.setPassword(password);
		userRepository.save(user);
		return "redirect:/member"; // Redirect to the member page
	}

	@PostMapping("/signin")
	public String signIn(@RequestParam String email, @RequestParam String password, Model model) {
		User user = userRepository.findByEmailAndPassword(email, password);
		if (user == null) {
			model.addAttribute("signinError", "Invalid email or password");
			return "home"; // Redirect to the sign-in page
		}
		return "redirect:/member"; // Redirect to the member page
	}

	public static void main(String[] args) {
		SpringApplication.run(Assignment3Application.class, args);
	}

}
