package zhili.springdemo.annotationconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class FootballCoach implements Coach {
	
	private FortuneService fortuneService;

	/**
	 * Constructor Injection
	 * Starting from Spring Framework 4.3, an @Autowired annotation on such a constructor
	 * is no longer necessary if the target bean only defines one constructor to begin with.
	 * However, if several constructors are available, at least one must be annotated to
	 * teach the container which one to use.
	 */
	@Autowired
	public FootballCoach(@Qualifier("happyFortuneService") FortuneService fortuneService) {
		this.fortuneService = fortuneService;
	}
	
	@Override
	public String getDailyWorkout() {
		return "Football daily workout: Run 5 miles";
	}

	@Override
	public String getDailyFortune() {
		return fortuneService.getFortune();
	}
	

}
