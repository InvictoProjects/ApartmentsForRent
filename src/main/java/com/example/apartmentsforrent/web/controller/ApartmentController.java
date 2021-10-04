package com.example.apartmentsforrent.web.controller;

import com.example.apartmentsforrent.persistence.model.Apartment;
import com.example.apartmentsforrent.service.ApartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ApartmentController {

    private final ApartmentService apartmentService;

    @Autowired
    public ApartmentController(ApartmentService apartmentService) {
        this.apartmentService = apartmentService;
    }

    @GetMapping("/deleteApartment")
    public String deleteApartment(@RequestParam String id) {
        Apartment apartment = apartmentService.findById(id);
        boolean result = apartmentService.deleteApartment(apartment);
        if (!result) {
            return "error";
        }
        //TODO: specify url or template to go over
        return "redirect:/";
    }
}
