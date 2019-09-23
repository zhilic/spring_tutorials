# Spring Tutorials
This repository includes my codes when learning basic Spring concepts.

Main reference: https://www.udemy.com/spring-hibernate-tutorial/

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

	*Libraries needed for the example codes:*
	- Spring 5 .jar files
	- javax.annotation (for Java 9 and higher)

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

## Spring MVC (Model-View-Controller)

> Spring Web MVC is the original web framework built on the **Servlet API** and has been included in the Spring Framework from the very beginning.

> Spring MVC is designed around the front controller pattern where a central `Servlet`, the `DispatcherServlet`, provides a shared algorithm for request processing, while actual work is performed by configurable delegate components.

https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html

It is a **HTTP oriented** web application development framework.

**Spring Model** is a container for your application data.
- In **Controller**, you can put everything in the model.
	`model.addAttributes('attributeName', attributeValue)`
- Your **View** page can access data from the model via attribute names.

`@RequestParam("studentName") String theName`: Spring will read the param *studentName* from request and bind it to the variable *theName* in the corresponding method.

Use `modelAttribute` to bind form data in front-end file (.html or .jsp etc.), and `@modelAttribute` in controllers to use the data.

**Form Validation**/**Bean Validation** Features: required, validate length, validate numbers, validate with regular expressions, custom validation. Validation Annotations: `@Valid` & new param`BindingResult` (in controller, `BindingResult` parameter must appear immediately after the model attribute), `@NotNull`, `Min`, `Max`, `Size`, `Pattern`, `@Future`/`@Past` etc.

## Spring Boot

> Spring Boot makes it easy to create stand-alone, production-grade Spring based Applications that you can "just run".
https://spring.io/projects/spring-boot

Spring Boot saves developers a lot of configurations which are requested in Spring MVC such as component scan, dispatcher servlet, a view resolver, web jars etc.

Dependencies of Spring Boot Starter Web can be classified into:
- Spring - core, beans, context, aop
- Web MVC - Spring MVC
- Jackson - for JSON Binding
- Validation - Hibernate Validation, Validation API
- Embedded Servlet Container - Tomcat
- Logging - logback, slf4j

Spring Boot apps can be run from the IDE or command-line (.jar file including the embedded Tomcat server); It can also be deployed as a **WAR** file in the traditionaly way to an external server (Tomcat, JBoss, WebSphere etc.)

**Application Properties** file
- By default located at *src/main/resources/applcation.properties*
- Can define ANY custom properties in this file
- Spring Boot app can access properties using `@Value`

Some Useful Tools:
- **spring-boot-starter-\***: No need to list version for these dependencies. They inherit version from the Starter Parent.
- **spring-boot-devtools**: Automatically restarts your application when code is updated.
- **spring-boot-(starter-)actuator**: Expose endpoints to *monitor* and *manage* your application. Endpoints are prefixed with "/actuator"
	- "/health": Health info about your application
	- "/info": Info about your project
	- "/auditevents": Audit events for your application
	- "/beans": List of all beans registered in the Spring application context
	- "/mappings": List of all @RequestMapping paths
	- etc.

## Hibernate

*Requirements for the example codes:*

1. MySQL server;
2. Hibernate ORM jar files;
3. MySQL-connector Java;

[Hibernate Basic CRUD Functions](./Hibernate_CRUD_Basics)

**Hibernate** is a framework for persisting / saving Java objects in a database.

Benefits of Hibernate:
- Hibernate handles all of the low-level SQL
- Minimizes the amount of JDBC code you have to develop
- Hibernate provides the **Object-to-Relational Mapping (ORM)**

Hibernate Development Process:
1. Add Hibernate Configuration file ("hibernate.cfg.xml")
	
	Two options for mapping: (1) XML config file (legacy); (2) **Java Annotations** (modern, preferred)

2. Annotate Java Class
3. Develop Java Code to perform database operations

**Entity**
- Entity Class: Java class that is mapped to a database table.
- Entity Lifecycle: Detach (from a Hibernate session), Merge (to a session), Persist (transitions new instances to managed state), Remove, Refresh

**SessionFactory**: Reads the hibernate config file; Creates Session objects; Heavy-weight object; Only create once in your app

**Session**: Wraps a JDBC connection; Main object used to save/retrieve objects; Short-lived object; Retrieved from SessionFactory

[**Advanced Mapping**](./Hibernate_Advanced_Mapping):
- One-to-One Mapping (uni-directional vs. bi-directional)
- One-to-Many / Many-to-One Mapping
- Many-to-Many Mapping

*Lazy Loading* (load the main entity first and then load dependent entities on demand - preferred, require an open Hibernate session) vs. *Eager Loading*

## Aspect-Oriented Programming (AOP)

**Advantages** of AOP:
- Reusable modules
- Resolve code tangling
- Resolve code scatter
- Applied selectively based on configuration

**Disadvantages** of AOP:
- Too many aspects and app flow is hard to follow
- Minor performance cost for aspect execution (run-time weaving)

Some Terminologies:
- **Aspect**: An aspect can be reused at multiple locations; same as class, can be applied based on configuration. It is a module of code for a cross-cutting concern (i.e. logging, security ...)
- **Advice**: What action is taken and when it should be applied. Types: *Before advice*, *After finally advice*, *After returning advice*, *After throwing advice*, *Around advice*
- **Joint Point**: When to apply code during program execution
- **Pointcut**: A predicate expression for where advice should be applied.
- **Weaving**: Connecting aspects to target objects to create an advised object. Types: compile-time, load-time, run-time (slowest)

|**Spring AOP**|**AspectJ**|
|:-------------|:----------|
|Provided by Spring|Original AOP framework|
|Provide support for key Spring components:<br> Security, transactions, caching etc.|Provide complete support for AOP|
|Run-time weaving of aspects (slower than AspectJ)|Rich support for join points & code weaving|

## Maven

## Spring Security


