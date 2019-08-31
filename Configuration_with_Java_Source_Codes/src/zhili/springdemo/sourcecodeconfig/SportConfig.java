package zhili.springdemo.sourcecodeconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("zhili.springdemo.sourcecodeconfig")
@PropertySource("classpath:sport.properties")
public class SportConfig {

	// define bean for fortune service
	@Bean
	public FortuneService happyFortuneService() {
		return new HappyFortuneService();
	}
	
	// define bean for swim coach AND inject dependency
	@Bean  // method name is the bean ID.
	public Coach swimCoach() {
		return new SwimCoach(happyFortuneService());
	}
	
}
