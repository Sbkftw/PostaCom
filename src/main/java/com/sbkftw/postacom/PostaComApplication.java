package com.sbkftw.postacom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import com.sbkftw.postacom.configuration.ApplicationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ApplicationProperties.class)
public class PostaComApplication {

    public static void main(String[] args) {
        SpringApplication.run(PostaComApplication.class, args);
    }

}
