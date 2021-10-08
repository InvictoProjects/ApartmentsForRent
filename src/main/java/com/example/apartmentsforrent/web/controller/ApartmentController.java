package com.example.apartmentsforrent.web.controller;

import com.example.apartmentsforrent.service.ApartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("apartment")
public class ApartmentController {

    private final ApartmentService apartmentService;

    @Autowired
    public ApartmentController(ApartmentService apartmentService) {
        this.apartmentService = apartmentService;
    }

    @DeleteMapping("/delete")
    public String deleteApartment(@RequestParam Long id) {
        boolean result = apartmentService.deleteById(id);
        if (result) {
            return "redirect:/";
        }
        return "error";
    }
}
