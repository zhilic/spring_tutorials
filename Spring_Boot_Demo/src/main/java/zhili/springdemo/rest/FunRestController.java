package zhili.springdemo.rest;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunRestController {

	@GetMapping("/")
	public String hello() {
		return "Hello form Spring Boot! Local time on the server is: " + 
					LocalDateTime.now();
	}
	
	@GetMapping("/workout")
	public String getDailyWorkout() {
		return "Run a hard 5k!";
	}
}
