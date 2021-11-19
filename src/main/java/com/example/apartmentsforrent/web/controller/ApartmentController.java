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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Get apartment list",
            description = "This method returns list of apartment DTO's with status 200 according to page number and size. If no parameters provided returns list of all apartment DTO's. Returns empty list if apartments cannot be found by provided page and size. Returns 400 if page and size are not positive integers.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Apartment's list returned",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid page or size values supplied", content = @Content)})
    @GetMapping
    public ResponseEntity<List<ApartmentDto>> listAll(@Parameter(description = "Page to search")
                                                      @RequestParam(required = false) Integer page,
                                                      @Parameter(description = "Size of page")
                                                      @RequestParam(required = false) Integer size) {
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

    @Operation(summary = "Get apartment by it's id",
            description = "This method returns apartment DTO with status 200 if id supplied is correct. Returns 400 if id is not integer. Returns 404 if apartment is not found.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Apartment found",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApartmentDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Apartment not found", content = @Content)})
    @GetMapping("/{id}")
    public ResponseEntity<ApartmentDto> getApartment(@Parameter(description = "Id of apartment", required = true)
                                                     @PathVariable Long id) {
        Optional<Apartment> apartment = apartmentService.findById(id);
        if (apartment.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(apartmentConverter.convertToApartmentDto(apartment.get()));
    }

    @Operation(summary = "Create apartment",
            description = "This method creates apartment by provided apartment DTO with status 201 if apartment DTO supplied is correct. Returns 400 if apartment dto provided is invalid")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Apartment created"),
            @ApiResponse(responseCode = "400", description = "Invalid apartment dto supplied", content = @Content)})
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApartmentDto createApartment(@Parameter(description = "Apartment to create",
            required = true, schema = @Schema(implementation = ApartmentDto.class))
                                        @RequestBody ApartmentDto apartmentDto) {
        Apartment apartment = apartmentDtoConverter.convertToApartment(apartmentDto);
        Apartment createdApartment = apartmentService.create(apartment);
        return apartmentConverter.convertToApartmentDto(createdApartment);
    }

    @Operation(summary = "Update apartment by it's id and DTO",
            description = "This method returns updated apartment DTO with status 200 if apartment was updated successfully. Returns 400 if id or apartment DTO are invalid. Returns 404 if apartment is not found")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Apartment updated",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApartmentDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id or apartment DTO supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Apartment not found", content = @Content)})
    @PutMapping("/{id}")
    public ApartmentDto updateApartment(@Parameter(description = "Id of apartment", required = true)
                                        @PathVariable Long id,
                                        @Parameter(description = "Apartment to update",
                                                required = true, schema = @Schema(implementation = ApartmentDto.class))
                                        @RequestBody ApartmentDto apartmentDto) {
        Apartment apartment = apartmentService.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        Apartment newApartment = apartmentDtoConverter.convertToApartment(apartmentDto);
        apartment.setApartmentDetails(newApartment.getApartmentDetails());
        apartment.setApartmentDescription(newApartment.getApartmentDescription());
        Apartment updatedApartment = apartmentService.update(apartment);
        return apartmentConverter.convertToApartmentDto(updatedApartment);
    }

    @Operation(summary = "Patch apartment by it's id",
            description = "This method returns updated apartment DTO with status 200 if apartment was patched successfully. Returns 400 if id or apartment DTO are invalid. Returns 404 if apartment is not found")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Apartment patched",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApartmentDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id or apartment DTO supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Apartment not found", content = @Content)})
    @PatchMapping("/{id}")
    public ApartmentDto patchApartment(@Parameter(description = "Id of apartment", required = true)
                                       @PathVariable Long id,
                                       @Parameter(description = "Apartment to patch",
                                               required = true, schema = @Schema(implementation = ApartmentDto.class))
                                       @RequestBody ApartmentDto patch) {
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

    @Operation(summary = "Get apartment list applying filters",
            description = "This method returns apartment DTO list with status 200 if filter attributes are correct. Returns empty list if apartments cannot be found by provided page, size or filter attributes. Returns 400 if page, size or filter attributes are invalid")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Apartment's list returned",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid page, size or filter attributes supplied", content = @Content)})
    @GetMapping("/filter")
    public List<ApartmentDto> getAll(@Parameter(description = "Page to search", required = true) @RequestParam int page,
                                     @Parameter(description = "Size of page", required = true) @RequestParam int size,
                                     @Parameter(description = "Minimum price") @RequestParam(required = false) BigDecimal priceFrom,
                                     @Parameter(description = "Maximum price") @RequestParam(required = false) BigDecimal priceTo,
                                     @Parameter(description = "Minimum quantity of rooms") @RequestParam(required = false) Integer quantityOfRoomsFrom,
                                     @Parameter(description = "Maximum quantity of rooms") @RequestParam(required = false) Integer quantityOfRoomsTo,
                                     @Parameter(description = "Minimum area") @RequestParam(required = false) Float areaFrom,
                                     @Parameter(description = "Maximum area") @RequestParam(required = false) Float areaTo,
                                     @Parameter(description = "Minimum floor") @RequestParam(required = false) Integer floorFrom,
                                     @Parameter(description = "Maximum floor") @RequestParam(required = false) Integer floorTo,
                                     @Parameter(description = "Minimum build year") @RequestParam(required = false) Year yearOfBuildFrom,
                                     @Parameter(description = "Maximum build year") @RequestParam(required = false) Year yearOfBuildTo) {
        if (page <= 0 || size <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return apartmentService.getAllWithFiltering(page, size, priceFrom, priceTo, quantityOfRoomsFrom, quantityOfRoomsTo,
                        areaFrom, areaTo, floorFrom, floorTo, yearOfBuildFrom, yearOfBuildTo).stream()
                .map(apartmentConverter::convertToApartmentDto)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Delete apartment by it's id",
            description = "This method deletes apartment with status 200 if apartment was deleted. Returns 400 if id is not integer. Returns 404 if apartment is not found")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Apartment deleted", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Apartment not found", content = @Content)})
    @DeleteMapping("/{id}")
    public void delete(@Parameter(description = "Apartment id", required = true) @PathVariable Long id) {
        Apartment apartment = apartmentService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        apartmentService.delete(apartment);
    }
}
