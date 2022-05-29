package com.connet.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableFeignClients
public class ConnetInstallerEngineApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConnetInstallerEngineApplication.class, args);
    }

}
