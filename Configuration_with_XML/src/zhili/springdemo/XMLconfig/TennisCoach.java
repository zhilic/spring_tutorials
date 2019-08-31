package zhili.springdemo.XMLconfig;

public class TennisCoach implements Coach {

	private FortuneService fortuneService;
	
	// define a constructor for dependency injection
	public TennisCoach(FortuneService fortuneService) {
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

	// add an init method
	public void doMyInitStuff() {
		System.out.println("TennisCoach: doMyInitStuff method");
	}
	
	// add a destroy method
	public void doMyDestroyStuff() {
		System.out.println("TennisCoach: doMyDestroyStuff method");
	}
	
}
