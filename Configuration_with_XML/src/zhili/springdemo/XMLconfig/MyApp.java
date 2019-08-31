package zhili.springdemo.XMLconfig;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyApp {
	
	public static void main(String[] args) {
		
		System.out.println("......Start the main method......");
		
		// load the Spring configuration file
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

		System.out.println();
		System.out.println("......Application context is initialized......");
		// retrieve bean from spring container, .getBean("beanId", interface)
		Coach theCoach = context.getBean("myCoach", Coach.class);

		// call methods on the bean
		System.out.println(theCoach.getDailyWorkout());
		
		// call new methods based on dependent bean
		System.out.println(theCoach.getDailyFortune());
		
		// setter injection
		System.out.println();
		System.out.println("......Start setter injection test......");
		CricketCoach theSetterCoach = context.getBean("mySetterCoach", CricketCoach.class);
		System.out.println(theSetterCoach.getDailyWorkout());
		System.out.println(theSetterCoach.getDailyFortune());
		System.out.println(theSetterCoach.getEmail());
		System.out.println(theSetterCoach.getTeam());
		
		// init and destroy methods in Bean lifecycle
		System.out.println();
		System.out.println("......Start init and destroy methods test......");
		TennisCoach theMethodCoach = context.getBean("myMethodCoach", TennisCoach.class);
		System.out.println(theMethodCoach.getDailyWorkout());
		
		// close the context
		context.close();
	}

}
