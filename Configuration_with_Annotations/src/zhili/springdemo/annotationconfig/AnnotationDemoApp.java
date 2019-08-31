package zhili.springdemo.annotationconfig;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnotationDemoApp {

	public static void main(String[] args) {
		
		// read Spring config file
		ClassPathXmlApplicationContext context = 
				new ClassPathXmlApplicationContext("applicationContext.xml");
		
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
		
		// close the context
		context.close();
	}

}
