package com.example.apartmentsforrent.web.converter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConverterConfiguration {

    @Bean()
    public OwnerDtoConverter ownerDtoConverter() {
        return new OwnerDtoConverter();
    }
}
