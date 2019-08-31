package zhili.springdemo.annotationconfig;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TennisCoach2 implements Coach {

	@Autowired
	@Qualifier("randomFortuneService")
	private FortuneService fortuneService;
	
	@Value("${foo.email}")
	private String email;
	
	@Value("${foo.team}")
	private String team;
	
	public TennisCoach2() {
		System.out.println(">> TennisCoach2: inside default constructor");
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	@Override
	public String getDailyWorkout() {
		return "Tennis daily workout";
	}

	@Override
	public String getDailyFortune() {
		return "Just Do It: " + fortuneService.getFortune();
	}

	// add an init method
	@PostConstruct  // need additional javax.annotation lib for Java 9 and higher
	public void doMyInitStuff() {
		System.out.println("TennisCoach2: doMyInitStuff method");
	}
	
	// add a destroy method
	@PreDestroy
	public void doMyDestroyStuff() {
		System.out.println("TennisCoach2: doMyDestroyStuff method");
	}

}
