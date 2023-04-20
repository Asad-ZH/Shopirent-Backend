package com.nerdware.deploymentdemo;


import com.nerdware.deploymentdemo.jwt.JwtConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(JwtConfig.class)
public class DeploymentdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeploymentdemoApplication.class, args);
    }

}
