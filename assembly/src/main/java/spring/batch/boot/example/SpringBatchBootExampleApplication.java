package spring.batch.boot.example;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.SpringServletContainerInitializer;

@SpringBootApplication
@EnableBatchProcessing
@EnableJpaRepositories(basePackages = {"spring"})
@ComponentScan(basePackages = {"spring"})
@EntityScan(basePackages = {"spring"})
public class SpringBatchBootExampleApplication extends SpringBootServletInitializer {

public static void main(String[] args) {
		SpringApplication.run(SpringBatchBootExampleApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(SpringBatchBootExampleApplication.class);
	}
}
