package com.zerogravitysolutions.digitalschool.configs;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    private Info apiInfo() {
        return new Info()
                .title("DigitalSchool API")
                .description("API documentation for Digitalschool service")
                .version("1.0.0")
                .license(apiLicense())
                .contact(apiContact());
    }

    private License apiLicense(){
        return new License()
                .name("MIT License")
                .url("https://opensource.org/license/mit");
    }

    private Contact apiContact(){
        return new Contact()
                .name("Arton Bilalli")
                .email("arton.bilalli1@gmail.com")
                .url("https://zerogravitysolutions.org");

    }

}
