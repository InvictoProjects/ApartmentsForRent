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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.Year;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/apartments")
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
    public ResponseEntity<List<ApartmentDto>> listAll(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size) {
        if (page != null && size != null) {
            if (page <= 0 || size <= 0) return ResponseEntity.badRequest().build();
            return ResponseEntity.ok(apartmentService
                    .findAll(page, size)
                    .stream()
                    .map(apartmentConverter::convertToApartmentDto)
                    .collect(Collectors.toList()));
        } else if (page == null && size == null) {
            return ResponseEntity.ok(apartmentService
                    .findAll()
                    .stream()
                    .map(apartmentConverter::convertToApartmentDto)
                    .collect(Collectors.toList()));
        } else return ResponseEntity.badRequest().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApartmentDto> getApartment(@PathVariable Long id) {
        Optional<Apartment> apartment = apartmentService.findById(id);
        if (apartment.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(apartmentConverter.convertToApartmentDto(apartment.get()));
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
            if (patchDetails.getQuantityOfRooms() != 0)
                oldDetails.setQuantityOfRooms(patchDetails.getQuantityOfRooms());
        }
        ApartmentDescriptionDto patchDescription = patch.getApartmentDescription();
        if (patchDescription != null) {
            ApartmentDescription oldDescription = apartment.getApartmentDescription();
            if (patchDescription.getCondition() != null) oldDescription.setCondition(patchDescription.getCondition());
            if (patchDescription.getBuildingType() != null)
                oldDescription.setBuildingType(patchDescription.getBuildingType());
            if (patchDescription.getAdditionalInfo() != null)
                oldDescription.setAdditionalInfo(patchDescription.getAdditionalInfo());
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

    @GetMapping("/filter")
    public List<ApartmentDto> getAll(@RequestParam int page,
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
        if (page <= 0 || size <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return apartmentService.getAllWithFiltering(page, size, priceFrom, priceTo, quantityOfRoomsFrom, quantityOfRoomsTo,
                        areaFrom, areaTo, floorFrom, floorTo, yearOfBuildFrom, yearOfBuildTo).stream()
                .map(apartmentConverter::convertToApartmentDto)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Apartment apartment = apartmentService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        apartmentService.delete(apartment);
    }
}
