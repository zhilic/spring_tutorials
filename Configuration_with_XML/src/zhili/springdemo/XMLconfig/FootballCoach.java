package zhili.springdemo.XMLconfig;

public class FootballCoach implements Coach {

	private FortuneService fortuneService;
	
	// define a constructor for dependency injection
	public FootballCoach(FortuneService fortuneService) {
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
