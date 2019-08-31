package zhili.springdemo.sourcecodeconfig;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class JavaConfigDemoApp {

	public static void main(String[] args) {
		
		// read Spring config Java class
		AnnotationConfigApplicationContext context = 
				new AnnotationConfigApplicationContext(SportConfig.class);
		
		// get the Bean from Spring container
		
		// test constructor injection
		Coach theCoach = context.getBean("footballCoach", Coach.class);
		
		// test setter/method injection
//		Coach theCoach = context.getBean("tennisCoach", Coach.class);
		
		// test field injection
//		Coach theCoach = context.getBean("tennisCoach2", Coach.class);
		
		// call a method on the Bean
		System.out.println(theCoach.getDailyWorkout());
		System.out.println(theCoach.getDailyFortune());
		
		// test loading values from properties file and lifecycle methods
		System.out.println();
		System.out.println("......Start test loading values from properties file & lifecycle methods......");
		TennisCoach2 theLoadingCoach = context.getBean("tennisCoach2", TennisCoach2.class);
		System.out.println(theLoadingCoach.getEmail());
		System.out.println(theLoadingCoach.getTeam());
		
		// test bean configuration thru config Java class
		System.out.println();
		System.out.println("......Start test bean configuration thru config class......");
		SwimCoach theClassCoach = context.getBean("swimCoach", SwimCoach.class);
		System.out.println(theClassCoach.getDailyWorkout());
		System.out.println(theClassCoach.getDailyFortune());
		
		// close the context
		context.close();
	}

}
