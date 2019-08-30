# Spring Tutorials
This repository includes my codes when learning basic Spring concepts.

Main source: https://www.udemy.com/spring-hibernate-tutorial/

### Spring Container & Spring Bean
Spring container is generically known as **_ApplicationContext_**. It's usually **_CONFIGURABLE_**.

A **Spring Bean** is simply a Java object. When Java objects are created by the Spring Container, then Spring refers to them as "Spring Beans".

1. Primary function of Spring Container:
- Create and manage objects - a.k.a. **Inversion of Control (IoC)**
	- The approach of outsourcing the construction and management of objects. (outsource to an object factory)
- Inject object's dependencies - a.k.a. **Dependency Injection**
	- "dependency" same thing as "helper objects", a Java object may depend on another Java object (i.e. additional services etc.)

2. Ways to configure Spring Container:
- [XML configuration file](./Configuration_with_XML) (legacy, but most legacy apps still use this)
- Java Annotations (modern)
- Java Souce Code (modern)
