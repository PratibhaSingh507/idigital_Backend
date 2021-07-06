package com.farm;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@RestController
public class FarmingApplication {
	private static Logger log = Logger.getLogger(FarmingApplication. class);
	@GetMapping("/status")
	public String deploy()
	{
		return "Application deployed to AWS beanstalk...";
	}

	public static void main(String[] args) {
		//BasicConfigurator.configure();
		
		SpringApplication.run(FarmingApplication.class, args);
		log.info("Application Started");
		
	}
	@Bean
    public Docket surveyAPI() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.farm"))
                .build();

    }

}
