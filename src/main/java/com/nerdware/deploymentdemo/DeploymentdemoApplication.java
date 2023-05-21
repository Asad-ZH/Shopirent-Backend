package com.nerdware.deploymentdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.nerdware.deploymentdemo.repository")
public class DeploymentdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeploymentdemoApplication.class, args);
    }


}
