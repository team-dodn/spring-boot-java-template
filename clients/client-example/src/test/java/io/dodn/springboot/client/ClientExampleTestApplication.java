package io.dodn.springboot.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class ClientExampleTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientExampleTestApplication.class, args);
    }

}
