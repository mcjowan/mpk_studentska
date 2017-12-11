package edu.mpk;

import java.net.InetAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.env.Environment;

import edu.mpk.config.DefaultProfileUtil;


@SpringBootApplication
@EnableConfigurationProperties({ LiquibaseProperties.class })
public class AppMpk {

	private static final Logger log = LoggerFactory.getLogger(AppMpk.class);
    
    public static void main(String[] args) throws Exception {
    	SpringApplication app = new SpringApplication(AppMpk.class);
    	DefaultProfileUtil.addDefaultProfile(app);
    	Environment env = app.run(args).getEnvironment();
        log.info("\n----------------------------------------------------------\n\t" +
                "Application '{}' is running! Access URLs:\n\t" +
                "Local: \t\thttp://localhost:{}\n\t" +
                "External: \thttp://{}:{}\n----------------------------------------------------------",
            env.getProperty("spring.application.name"),
            env.getProperty("server.port"),
            InetAddress.getLocalHost().getHostAddress(),
            env.getProperty("server.port"));
    }
}
