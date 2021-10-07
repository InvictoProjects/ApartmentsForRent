package com.example.apartmentsforrent.web.controller;

import com.example.apartmentsforrent.persistence.model.Apartment;
import com.example.apartmentsforrent.service.ApartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("apartment")
public class ApartmentController {

    private final ApartmentService apartmentService;

    @Autowired
    public ApartmentController(ApartmentService apartmentService) {
        this.apartmentService = apartmentService;
    }

    @GetMapping("/delete")
    public String deleteApartment(@RequestParam String id) {
        try {
            Optional<Apartment> optionalApartment = apartmentService.findById(Long.parseLong(id));
            boolean result = apartmentService.deleteApartment(optionalApartment.orElseThrow(IllegalArgumentException::new));
            if (result) {
                return "redirect:/";
            }
            return "error";
        } catch (IllegalArgumentException e) {
            return "error";
        }
    }
}
