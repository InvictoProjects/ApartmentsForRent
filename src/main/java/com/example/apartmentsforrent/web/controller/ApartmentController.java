package com.example.apartmentsforrent.web.controller;

import com.example.apartmentsforrent.persistence.model.Apartment;
import com.example.apartmentsforrent.persistence.model.ApartmentDescription;
import com.example.apartmentsforrent.persistence.model.ApartmentDetails;
import com.example.apartmentsforrent.persistence.model.Owner;
import com.example.apartmentsforrent.service.ApartmentService;
import com.example.apartmentsforrent.web.converter.ApartmentConverter;
import com.example.apartmentsforrent.web.converter.ApartmentDtoConverter;
import com.example.apartmentsforrent.web.dto.ApartmentDescriptionDto;
import com.example.apartmentsforrent.web.dto.ApartmentDetailsDto;
import com.example.apartmentsforrent.web.dto.ApartmentDto;
import com.example.apartmentsforrent.web.dto.OwnerDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("apartments")
public class ApartmentController {
    private final ApartmentService apartmentService;
    private final ApartmentConverter apartmentConverter;
    private final ApartmentDtoConverter apartmentDtoConverter;

    public ApartmentController(ApartmentService apartmentService,
                               ApartmentConverter apartmentConverter,
                               ApartmentDtoConverter apartmentDtoConverter) {
        this.apartmentService = apartmentService;
        this.apartmentConverter = apartmentConverter;
        this.apartmentDtoConverter = apartmentDtoConverter;
    }

    @GetMapping
    public List<ApartmentDto> listAll(@RequestParam Optional<Integer> page, @RequestParam Optional<Integer> size) {
        if (page.isPresent() && size.isPresent()) {
            try {
                return apartmentService
                        .findAll(page.get(), size.get())
                        .stream()
                        .map(apartmentConverter::convertToApartmentDto)
                        .collect(Collectors.toList());
            } catch (IndexOutOfBoundsException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
        } else if (page.isEmpty() && size.isEmpty()) {
            return apartmentService
                    .findAll()
                    .stream()
                    .map(apartmentConverter::convertToApartmentDto)
                    .collect(Collectors.toList());
        } else throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{id}")
    public ApartmentDto getApartment(@PathVariable Long id) {
        Apartment apartment = apartmentService.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        return apartmentConverter.convertToApartmentDto(apartment);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApartmentDto createApartment(@RequestBody ApartmentDto apartmentDto) {
        Apartment apartment = apartmentDtoConverter.convertToApartment(apartmentDto);
        Apartment createdApartment = apartmentService.create(apartment);
        return apartmentConverter.convertToApartmentDto(createdApartment);
    }

    @PutMapping("/{id}")
    public ApartmentDto updateApartment(@PathVariable Long id, @RequestBody ApartmentDto apartmentDto) {
        Apartment apartment = apartmentService.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        Apartment newApartment = apartmentDtoConverter.convertToApartment(apartmentDto);
        apartment.setApartmentDetails(newApartment.getApartmentDetails());
        apartment.setApartmentDescription(newApartment.getApartmentDescription());
        Apartment updatedApartment = apartmentService.update(apartment);
        return apartmentConverter.convertToApartmentDto(updatedApartment);
    }

    @PatchMapping("/{id}")
    public ApartmentDto patchApartment(@PathVariable Long id, @RequestBody ApartmentDto patch) {
        Apartment apartment = apartmentService.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        ApartmentDetailsDto patchDetails = patch.getApartmentDetails();
        if (patchDetails != null) {
            ApartmentDetails oldDetails = apartment.getApartmentDetails();
            if (patchDetails.getAddress() != null) oldDetails.setAddress(patchDetails.getAddress());
            if (patchDetails.getBuildYear() != null) oldDetails.setBuildYear(patchDetails.getBuildYear());
            if (patchDetails.getPrice() != null) oldDetails.setPrice(patchDetails.getPrice());
            if (patchDetails.getFloor() != 0) oldDetails.setFloor(patchDetails.getFloor());
            if (patchDetails.getArea() != 0) oldDetails.setArea(patchDetails.getArea());
            if (patchDetails.getQuantityOfRooms() != 0) oldDetails.setQuantityOfRooms(patchDetails.getQuantityOfRooms());
        }
        ApartmentDescriptionDto patchDescription = patch.getApartmentDescription();
        if (patchDescription != null) {
            ApartmentDescription oldDescription = apartment.getApartmentDescription();
            if (patchDescription.getCondition() != null) oldDescription.setCondition(patchDescription.getCondition());
            if (patchDescription.getBuildingType() != null) oldDescription.setBuildingType(patchDescription.getBuildingType());
            if (patchDescription.getAdditionalInfo() != null) oldDescription.setAdditionalInfo(patchDescription.getAdditionalInfo());
        }
        OwnerDto patchOwner = patch.getOwner();
        if (patchOwner != null) {
            Owner oldOwner = apartment.getOwner();
            if (patchOwner.getName() != null) oldOwner.setName(patchOwner.getName());
            if (patchOwner.getSurname() != null) oldOwner.setSurname(patchOwner.getSurname());
            if (patchOwner.getEmail() != null) oldOwner.setEmail(patchOwner.getEmail());
            if (patchOwner.getPhoneNumber() != null) oldOwner.setPhoneNumber(patchOwner.getPhoneNumber());
        }
        Apartment updatedApartment = apartmentService.update(apartment);
        return apartmentConverter.convertToApartmentDto(updatedApartment);
    }
}
