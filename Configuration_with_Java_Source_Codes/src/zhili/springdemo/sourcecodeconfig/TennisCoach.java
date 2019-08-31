package zhili.springdemo.sourcecodeconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class TennisCoach implements Coach {

	private FortuneService fortuneService;
	
	public TennisCoach() {
		System.out.println(">> TennisCoach: inside default constructor");
	}
	
	/**
	 * Setter Injection
	 * It works not only for setter method, but any other methods.
	 * For example, "public void doSomeCrazyStuff(FortuneService fortuneService) {
	 * this.fortuneService = fortuneService; }"
	 * @param fortuneService
	 */
	@Autowired
	@Qualifier("happyFortuneService")
	public void setFortuneService(FortuneService fortuneService) {
		System.out.println(">> TennisCoach: inside setFortuneService() method");
		this.fortuneService = fortuneService;
	}

	@Override
	public String getDailyWorkout() {
		return "Tennis daily workout";
	}

	@Override
	public String getDailyFortune() {
		return "Just Do It: " + fortuneService.getFortune();
	}

}
