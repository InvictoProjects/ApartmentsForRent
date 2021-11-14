package com.example.apartmentsforrent.web.controller;

import com.example.apartmentsforrent.persistence.model.Apartment;
import com.example.apartmentsforrent.service.ApartmentService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.Year;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/apartments")
public class ApartmentController {

    private final ApartmentService apartmentService;

    public ApartmentController(ApartmentService apartmentService) {
        this.apartmentService = apartmentService;
    }

    @GetMapping
    public List<Apartment> getAll(@RequestParam int page,
                                  @RequestParam int size,
                                  @RequestParam(required = false) BigDecimal priceFrom,
                                  @RequestParam(required = false) BigDecimal priceTo,
                                  @RequestParam(required = false) Integer quantityOfRoomsFrom,
                                  @RequestParam(required = false) Integer quantityOfRoomsTo,
                                  @RequestParam(required = false) Float areaFrom,
                                  @RequestParam(required = false) Float areaTo,
                                  @RequestParam(required = false) Integer floorFrom,
                                  @RequestParam(required = false) Integer floorTo,
                                  @RequestParam(required = false) Year yearOfBuildFrom,
                                  @RequestParam(required = false) Year yearOfBuildTo) {
        return apartmentService.getAllWithFiltering(page, size, priceFrom, priceTo, quantityOfRoomsFrom, quantityOfRoomsTo,
                areaFrom, areaTo, floorFrom, floorTo, yearOfBuildFrom, yearOfBuildTo);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Optional<Apartment> optionalApartment = apartmentService.findById(id);
        optionalApartment.ifPresent(apartmentService::delete);
    }
}
