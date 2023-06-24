package com.SzyRosz.jazs25323nbp.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {
    @Bean
    public OpenAPI OpenAPI() {
        Contact contact = new Contact();
        contact.setEmail("s25323@pjwskt.edu.pl");
        contact.setName("Pjatk");
        contact.setUrl("https://www.pjatk-gdansk.com");


        Info info = new Info()
                .title("NBP Service")
                .version("1.0")
                .contact(contact)
                .description("NBP currency rate rest api");


        return new OpenAPI().info(info);

    }
}