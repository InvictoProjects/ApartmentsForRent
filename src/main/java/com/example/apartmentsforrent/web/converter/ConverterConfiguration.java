package com.example.apartmentsforrent.web.converter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConverterConfiguration {
    @Bean
    public ApartmentDtoConverter apartmentDtoConverter() {
        return new ApartmentDtoConverter();
    }

    @Bean
    public ApartmentDetailsDtoConverter apartmentDetailsDtoConverter() {
        return new ApartmentDetailsDtoConverter();
    }

    @Bean
    public ApartmentDescriptionDtoConverter apartmentDescriptionDtoConverter() {
        return new ApartmentDescriptionDtoConverter();
    }
}
