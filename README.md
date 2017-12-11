# MPK Osnove programiranja

## 11.2017
	- Java 1.8
	- Spring boot (data JPA)
	- Povezan sa Liquibase DB - h2
	- Tomcat embeded
	- Ubačen thymeleaf
	- webjars - bootstrap i jquery
### 



# Pokretanje i kompajliranje aplikacije
#### power shell ili cmd prompt

	mvn clean package -DskipTests
	mvn clean install -DskipTests?
  
Aplikaciju pokrenuti iz Eclipse: Debug as Java Application

#Baza podataka

/h2-console - otvara se forma za konekciju na bazu
Promeni:
JDBC URL: jdbc:h2:file:./target/h2db1/db/mcj
User name: mcj

#Opcije i dokumentacija
https://springframework.guru/spring-boot-web-application-part-4-spring-mvc/
 - https://github.com/springframeworkguru/springbootwebapp/tree/master

http://www.devglan.com/spring-boot/spring-boot-mvc-app-with-jsp
https://www.java2blog.com/spring-boot-hibernate-example/

#todo
Iskopiraj iz boot probe bazu