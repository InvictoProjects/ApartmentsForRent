package com.example.apartmentsforrent.persistence.repository.impl;

import com.example.apartmentsforrent.persistence.Checker;
import com.example.apartmentsforrent.persistence.model.Apartment;
import com.example.apartmentsforrent.persistence.model.ApartmentDescription;
import com.example.apartmentsforrent.persistence.model.ApartmentDetails;
import com.example.apartmentsforrent.persistence.model.Owner;
import com.example.apartmentsforrent.persistence.repository.ApartmentRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.Year;
import java.util.*;

@Repository
public class ApartmentRepositoryImpl implements ApartmentRepository {

    private final HashMap<Long, Apartment> databaseMap = new HashMap<>();
    private long index = 0;

    @PostConstruct
    private void createDatabase() {
        Owner owner1 = new Owner.Builder()
                .setName("Name1")
                .setSurname("Surname1")
                .setEmail("owner1@hazain.com")
                .setPhoneNumber("+380111111111")
                .setPasswordHash("2e23rwefstgsdg")
                .build();

        Owner owner2 = new Owner.Builder()
                .setName("Name2")
                .setSurname("Surname2")
                .setEmail("owner2@hazain.com")
                .setPhoneNumber("+380111112222")
                .setPasswordHash("2e212323tgsdg")
                .build();

        ApartmentDescription description1 = new ApartmentDescription.Builder()
                .setBuildingType("Some type")
                .setCondition("Some condition")
                .setAdditionalInfo("Additional information")
                .build();

        ApartmentDescription description2 = new ApartmentDescription.Builder()
                .setBuildingType("Some type2")
                .setCondition("Some condition2")
                .setAdditionalInfo("Additional information2")
                .build();

        ApartmentDetails details1 = new ApartmentDetails.Builder()
                .setAddress("St. Peremohy, bldg. 12")
                .setBuildYear(Year.of(2012))
                .setPrice(new BigDecimal("70000.50"))
                .setArea(163.12f)
                .setFloor(10)
                .setQuantityOfRooms(3)
                .build();

        ApartmentDetails details2 = new ApartmentDetails.Builder()
                .setAddress("Sq. Troyana, bldg. 32")
                .setBuildYear(Year.of(2019))
                .setPrice(new BigDecimal("1170000.50"))
                .setArea(200)
                .setFloor(3)
                .setQuantityOfRooms(4)
                .build();

        Apartment apartment1 = new Apartment.Builder()
                .setOwner(owner1)
                .setApartmentDetails(details1)
                .setApartmentDescription(description1)
                .build();

        Apartment apartment2 = new Apartment.Builder()
                .setOwner(owner2)
                .setApartmentDetails(details2)
                .setApartmentDescription(description2)
                .build();

        this.save(apartment1);
        this.save(apartment2);
    }

    @Override
    public Apartment save(Apartment apartment) {
        if (apartment.getId() != null) {
            databaseMap.put(apartment.getId(), apartment);
        } else {
            apartment.setId(index);
            databaseMap.put(index, apartment);
            index++;
        }
        return apartment;
    }

    @Override
    public Optional<Apartment> findById(Long id) {
        Apartment found = databaseMap.get(id);
        if (found == null) {
            return Optional.empty();
        }
        return Optional.of(found);
    }

    @Override
    public boolean existsById(Long id) {
        return databaseMap.containsKey(id);
    }

    @Override
    public Iterable<Apartment> findAll() {
        return databaseMap.values();
    }

    @Override
    public void deleteById(Long id) {
        databaseMap.remove(id);
    }

    @Override
    public List<Apartment> search(BigDecimal priceFrom, BigDecimal priceTo, Integer quantityOfRoomsFrom,
                                  Integer quantityOfRoomsTo, Float areaFrom, Float areaTo, Integer floorFrom,
                                  Integer floorTo, Year yearOfBuildFrom, Year yearOfBuildTo) {
        List<Apartment> apartments = new ArrayList<>();
        for (Map.Entry<Long, Apartment> entry : databaseMap.entrySet()) {
            Apartment apartmentI = entry.getValue();

            boolean isPriceSuitable = checkerProvider().check(priceFrom, priceTo, apartmentI.getApartmentDetails().getPrice(),
                    BigDecimal.ZERO);

            boolean isQuantityOfRoomsSuitable = checkerProvider().check(quantityOfRoomsFrom, quantityOfRoomsTo,
                    apartmentI.getApartmentDetails().getQuantityOfRooms(), 0);

            boolean isAreaSuitable = checkerProvider().check(areaFrom, areaTo, apartmentI.getApartmentDetails().getArea(), .0f);

            boolean isFloorSuitable = checkerProvider().check(floorFrom, floorTo, apartmentI.getApartmentDetails().getFloor(), 0);

            boolean isYearOfBuildSuitable = checkerProvider().check(yearOfBuildFrom, yearOfBuildTo,
                    apartmentI.getApartmentDetails().getBuildYear(), Year.of(0));

            if (isPriceSuitable && isQuantityOfRoomsSuitable && isAreaSuitable && isFloorSuitable && isYearOfBuildSuitable) {
                apartments.add(apartmentI);
            }
        }
        return apartments;
    }

    @Bean
    private Checker checkerProvider() {
        return new Checker();
    }
}
