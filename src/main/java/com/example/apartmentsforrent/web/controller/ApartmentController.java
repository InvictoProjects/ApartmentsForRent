package com.example.apartmentsforrent.web.controller;

import com.example.apartmentsforrent.persistence.model.Apartment;
import com.example.apartmentsforrent.persistence.model.ApartmentDescription;
import com.example.apartmentsforrent.persistence.model.ApartmentDetails;
import com.example.apartmentsforrent.persistence.model.Owner;
import com.example.apartmentsforrent.service.ApartmentService;
import com.example.apartmentsforrent.web.converter.ApartmentDescriptionDtoConverter;
import com.example.apartmentsforrent.web.converter.ApartmentDetailsDtoConverter;
import com.example.apartmentsforrent.web.dto.ApartmentDescriptionDto;
import com.example.apartmentsforrent.web.dto.ApartmentDetailsDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("apartment")
public class ApartmentController {
    private final ApartmentService apartmentService;
    private final ApartmentDetailsDtoConverter apartmentDetailsDtoConverter;
    private final ApartmentDescriptionDtoConverter apartmentDescriptionDtoConverter;

    public ApartmentController(ApartmentService apartmentService, ApartmentDetailsDtoConverter apartmentDetailsDtoConverter, ApartmentDescriptionDtoConverter apartmentDescriptionDtoConverter) {
        this.apartmentService = apartmentService;
        this.apartmentDetailsDtoConverter = apartmentDetailsDtoConverter;
        this.apartmentDescriptionDtoConverter = apartmentDescriptionDtoConverter;
    }

    @GetMapping("/create")
    public String createApartment(Model model) {
        model.addAttribute("apartment_details", new ApartmentDetailsDto());
        model.addAttribute("apartment_description", new ApartmentDescriptionDto());
        return "create_apartment";
    }

    @PostMapping("/create")
    public String createApartment(@ModelAttribute("apartment_details") ApartmentDetailsDto detailsDto,
                                  @ModelAttribute("apartment_description") ApartmentDescriptionDto descriptionDto) {
        ApartmentDetails details = apartmentDetailsDtoConverter.convertToApartmentDetails(detailsDto);
        ApartmentDescription description = apartmentDescriptionDtoConverter.convertToApartmentDescription(descriptionDto);
        apartmentService.create(details, description, new Owner());
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String editApartment(Model model, @RequestParam Long id) {
        Apartment apartment = apartmentService.findById(id).orElseThrow(
                () -> new IllegalArgumentException(String.format("Apartment with id %s does not exist", id))
        );
        model.addAttribute("apartment_details", apartment.getApartmentDetails());
        model.addAttribute("apartment_description", apartment.getApartmentDescription());
        return "edit_apartment";
    }

    @PostMapping("/edit")
    public String editApartment(@ModelAttribute("apartment_details") ApartmentDetailsDto detailsDto,
                                @ModelAttribute("apartment_description") ApartmentDescriptionDto descriptionDto,
                                @RequestParam Long id) {
        Apartment apartment = apartmentService.findById(id).orElseThrow(
                () -> new IllegalArgumentException(String.format("Apartment with id %s does not exist", id))
        );
        ApartmentDetails details = apartmentDetailsDtoConverter.convertToApartmentDetails(detailsDto);
        ApartmentDescription description = apartmentDescriptionDtoConverter.convertToApartmentDescription(descriptionDto);
        apartment.setApartmentDetails(details);
        apartment.setApartmentDescription(description);
        apartmentService.update(apartment);
        return "redirect:/";
    }

    @DeleteMapping("/delete")
    public String deleteApartment(@RequestParam Long id) {
        apartmentService.deleteById(id);
        return "redirect:/";
    }
}
