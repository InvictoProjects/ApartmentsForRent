package com.example.apartmentsforrent.web.controller;

import com.example.apartmentsforrent.persistence.model.Apartment;
import com.example.apartmentsforrent.service.ApartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.time.Year;
import java.util.List;

@Controller
public class SearchController {

    private final ApartmentService apartmentService;

    @Autowired
    public SearchController(ApartmentService apartmentService) {
        this.apartmentService = apartmentService;
    }

    @GetMapping("/search")
    public String getSearchPage() {
        return "search";
    }

    @PostMapping("/search")
    public String searchApartments(Model model, @RequestParam(required = false) BigDecimal priceFrom,
                                   @RequestParam(required = false) BigDecimal priceTo,
                                   @RequestParam(required = false) Integer quantityOfRoomsFrom,
                                   @RequestParam(required = false) Integer quantityOfRoomsTo,
                                   @RequestParam(required = false) Float areaFrom,
                                   @RequestParam(required = false) Float areaTo,
                                   @RequestParam(required = false) Integer floorFrom,
                                   @RequestParam(required = false) Integer floorTo,
                                   @RequestParam(required = false) Year yearOfBuildFrom,
                                   @RequestParam(required = false) Year yearOfBuildTo) {
        List<Apartment> apartments = apartmentService.search(priceFrom, priceTo, quantityOfRoomsFrom, quantityOfRoomsTo,
                areaFrom, areaTo, floorFrom, floorTo, yearOfBuildFrom, yearOfBuildTo);
        model.addAttribute("apartments", apartments);
        return "/search";
    }
}
