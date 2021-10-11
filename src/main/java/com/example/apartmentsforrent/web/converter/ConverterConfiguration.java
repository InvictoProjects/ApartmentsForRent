package com.example.apartmentsforrent.web.converter;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

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

    @Bean
    public OwnerDtoConverter ownerDtoConverter() {
        return new OwnerDtoConverter();
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public ApartmentConverter apartmentConverter() {
        return new ApartmentConverter();
    }
}
