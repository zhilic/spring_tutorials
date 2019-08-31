package zhili.springdemo.sourcecodeconfig;

public class SwimCoach implements Coach {

	private FortuneService fortuneService;
	
	public SwimCoach(FortuneService fortuneService) {
		this.fortuneService = fortuneService;
	}
	
	@Override
	public String getDailyWorkout() {
		return "Swim daily workout: swim 1000 meters as a warm up.";
	}

	@Override
	public String getDailyFortune() {
		return "Swim: " + fortuneService.getFortune();
	}

}
