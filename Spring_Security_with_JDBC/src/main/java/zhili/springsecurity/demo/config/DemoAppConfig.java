/**
 * @author zhilic created on 9/26/19
 */

package zhili.springsecurity.demo.config;

import java.beans.PropertyVetoException;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "zhili.springsecurity.demo")
@PropertySource("classpath:persistent-mysql.properties")  // read the properties file
public class DemoAppConfig {

	// set up variable to hold the properties
	// Environment is a special helper class from the Spring Framework, 
	// which will hold data read from properties files
	@Autowired
	private Environment env;
	
	// set up a logger for diagnostics
	private Logger logger = Logger.getLogger(getClass().getName());
	
	
    // define a bean for ViewResolver (using jsp files for frontend)
    @Bean
    public ViewResolver viewResolver() {

        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

        viewResolver.setPrefix("/WEB-INF/view/");
        viewResolver.setSuffix(".jsp");

        return viewResolver;
    }
    
    // define a bean for security datasource (using pure Java codes to config our app)
    @Bean
    public DataSource securityDataSource() {
    	
    	// create connection pool
    	ComboPooledDataSource dataSource = 
    			new ComboPooledDataSource();
    	
    	// set the JDBC driver class
    	try {
			dataSource.setDriverClass(env.getProperty("jdbc.driver"));
		} catch (PropertyVetoException exc) {
			throw new RuntimeException(exc);
		}
    	
    	// log the connection props
    	logger.info(">>> jdbc.url=" + env.getProperty("jdbc.url"));
    	logger.info(">>> jdbc.username=" + env.getProperty("jdbc.username"));
    	
    	// set database connection props
    	dataSource.setJdbcUrl(env.getProperty("jdbc.url"));
    	dataSource.setUser(env.getProperty("jdbc.username"));
    	dataSource.setPassword(env.getProperty("jdbc.password"));
    	
    	// set connection pool props
    	dataSource.setInitialPoolSize(getIntProperty("connection.pool.initialPoolSize"));
    	dataSource.setMinPoolSize(getIntProperty("connection.pool.minPoolSize"));
    	dataSource.setMaxPoolSize(getIntProperty("connection.pool.maxPoolSize"));
    	dataSource.setMaxIdleTime(getIntProperty("connection.pool.maxIdleTime"));
    	
    	return dataSource;
    }
    
    // A helper method to read environment property and convert to int
    private int getIntProperty(String propName) {
    	String propVal = env.getProperty(propName);
    	int intPropVal = Integer.parseInt(propVal);
    	return intPropVal;
    }
    
}
