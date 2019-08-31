# Spring Tutorials
This repository includes my codes when learning basic Spring concepts.

Main source: https://www.udemy.com/spring-hibernate-tutorial/

Libraries needed: 
- Spring 5 .jar files downloaded at https://repo.spring.io/release/org/springframework/spring/
- javax.annotation (for Java 9 and higher) downloaded at http://central.maven.org/maven2/javax/annotation/javax.annotation-api/1.2/javax.annotation-api-1.2.jar

## Spring Container
**Spring container** is generically known as **_ApplicationContext_**. It's usually **_CONFIGURABLE_**.

1. Primary function of Spring Container:
- Create and manage objects - a.k.a. **Inversion of Control (IoC)**
	- The approach of outsourcing the construction and management of objects. (outsource to an object factory)
- Inject object's dependencies - a.k.a. **Dependency Injection**
	- "dependency" same thing as "helper objects", a Java object may depend on another Java object (i.e. additional services etc.)
	- Two common types of dependency injection:
		1. Constructor injection
		2. Setter injection
			1. Use literal values in the configuration file
			2. Load values from properties file

2. Ways to configure Spring Container:
- [XML configuration file](./Configuration_with_XML) (legacy, but most legacy apps still use this)
- [Java Annotations](./Configuration_with_Annotations) (modern, minimize XML file)
	- Default Bean ID is the class name with the first letter being lower-case. **_Special case_**: If both the first and second characters of the class name are upper case, the name is NOT converted.
	- You can also specify a Bean ID in the `@Component` annotation
	- **Autowiring** injection types: Constructor injection, Setter injection, Field injections
	- Use `@Qualifier("the desired bean id")` to specify which bean to use if there are multiple beans to be autowired.
	- *When using `@PostConstruct` and `@PreDestroy` in Java 9 and higher, you need additional javax.annotation lib to enable the annotation imports.*
- [Java Source Code](./Configuration_with_Java_Source_Codes) (modern, no XML file)
	- Add a configuration class in src directory


## Spring Bean

A **Spring Bean** is simply a Java object. When Java objects are created by the Spring Container, then Spring refers to them as "Spring Beans".

Scopes of Spring Bean:
- **Singleton** (default): ALL requests for the bean will return a SHARED reference to the SAME bean.
- **Prototype**: Create a new bean instance for EACH container request. *Spring doesn't call the destroy method for prototype scoped beans.*
- Other three types used in web apps:
	- Request: scoped to an HTTP web request
	- Session: scoped to an HTTP web session
	- Global-session: scoped to a global HTTP web session

Bean Lifecycle:

Container Started --> Bean Instantiated --> Dependencies Injected --> Internal Spring Processing --> **_Custom Init Method_** --> (Bean is ready for use) --> (Container is shut down) --> **_Custom Destroy Method_** --> STOP

Special note about init and destroy method signatures (`init-method` & `destroy-method`) when using XML configuration:
- The method can have any access modifier (public, protected, private).
- The method can have any return type, but usually "void".
- The method can have any method name.
- The method can NOT accept any arguments.