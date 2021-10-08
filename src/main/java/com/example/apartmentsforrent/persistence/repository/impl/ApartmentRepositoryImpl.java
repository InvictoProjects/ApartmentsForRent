package com.example.apartmentsforrent.persistence.repository.impl;

import com.example.apartmentsforrent.persistence.model.Apartment;
import com.example.apartmentsforrent.persistence.model.ApartmentDescription;
import com.example.apartmentsforrent.persistence.model.ApartmentDetails;
import com.example.apartmentsforrent.persistence.model.Owner;
import com.example.apartmentsforrent.persistence.repository.ApartmentRepository;
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
        Owner owner1 = new Owner("Name1", "Surname1", "owner1@gmail.com", "+38090452342",
                "ssdfsdf23d");
        Owner owner2 = new Owner("Name2", "Surname2", "owner2@gmail.com", "+38090452222",
                "ssdfsasdasd23d");
        ApartmentDescription description1 = new ApartmentDescription("qwr23rwersfsdrsdr3", "asd",
                "asdsd");
        ApartmentDescription description2 = new ApartmentDescription("qwr23rwersfsdrsdr3", "asd",
                "asdsd");
        ApartmentDetails details1 = new ApartmentDetails("asdasd", Year.of(1234), new BigDecimal(1234),
                12, 12, 12);
        ApartmentDetails details2 = new ApartmentDetails("dasd", Year.of(1324), new BigDecimal("234.12"),
                13, 12, 13);
        save(new Apartment(details1, description1, owner1));
        save(new Apartment(details2, description2, owner2));
    }

    @Override
    public Optional<Apartment> save(Apartment apartment) {
        apartment.setId(index);
        if (databaseMap.containsKey(index)) {
            return Optional.empty();
        }
        databaseMap.put(index, apartment);
        index++;
        return Optional.of(apartment);
    }

    @Override
    public Optional<Apartment> findById(long id) {
        Apartment found = databaseMap.get(id);
        if (found == null) {
            return Optional.empty();
        }
        return Optional.of(found);
    }

    @Override
    public boolean delete(Apartment apartment) {
        Apartment removed = databaseMap.remove(apartment.getId());
        return removed != null;
    }

    @Override
    public List<Apartment> search(BigDecimal priceFrom, BigDecimal priceTo, Integer quantityOfRoomsFrom,
                                  Integer quantityOfRoomsTo, Float areaFrom, Float areaTo, Integer floorFrom,
                                  Integer floorTo, Year yearOfBuildFrom, Year yearOfBuildTo) {
        List<Apartment> apartments = new ArrayList<>();
        for (Map.Entry<Long, Apartment> entry : databaseMap.entrySet()) {
            Apartment apartmentI = entry.getValue();
            if (checkBigDecimal(priceFrom, priceTo, apartmentI.getApartmentDetails().getPrice()) &&
                    checkFloat(areaFrom, areaTo, apartmentI.getApartmentDetails().getArea()) &&
                    checkInteger(quantityOfRoomsFrom, quantityOfRoomsTo, apartmentI.getApartmentDetails().getQuantityOfRooms()) &&
                    checkInteger(floorFrom, floorTo, apartmentI.getApartmentDetails().getFloor()) &&
                    checkYear(yearOfBuildFrom, yearOfBuildTo, apartmentI.getApartmentDetails().getBuildYear())) {
                apartments.add(apartmentI);
            }
        }
        return apartments;
    }

    private boolean checkYear(Year yearOfBuildFrom, Year yearOfBuildTo, Year buildYear) {
        if (yearOfBuildFrom == null && yearOfBuildTo == null) {
            return true;
        } else if (yearOfBuildTo == null) {
            return buildYear.compareTo(yearOfBuildFrom) > 0;
        } else {
            if (yearOfBuildFrom == null) {
                yearOfBuildFrom = Year.of(0);
            }
            return (buildYear.compareTo(yearOfBuildFrom) > 0 && yearOfBuildTo.compareTo(buildYear) > 0);
        }
    }

    private boolean checkBigDecimal(BigDecimal priceFrom, BigDecimal priceTo, BigDecimal priceToCheck) {
        if (priceFrom == null && priceTo == null) {
            return true;
        } else if (priceTo == null) {
            return priceToCheck.compareTo(priceFrom) > 0;
        } else {
            if (priceFrom == null) {
                priceFrom = BigDecimal.ZERO;
            }
            return ((priceToCheck.compareTo(priceFrom) > 0) || priceToCheck.equals(priceFrom) &&
                    (priceTo.compareTo(priceToCheck) > 0) || priceTo.equals(priceFrom));
        }
    }

    private boolean checkInteger(Integer priceFrom, Integer priceTo, Integer priceToCheck) {
        if (priceFrom == null && priceTo == null) {
            return true;
        } else if (priceTo == null) {
            return priceToCheck.compareTo(priceFrom) > 0;
        } else {
            if (priceFrom == null) {
                priceFrom = 0;
            }
            return (priceToCheck >= priceFrom && priceTo >= priceToCheck);
        }
    }

    private boolean checkFloat(Float priceFrom, Float priceTo, Float priceToCheck) {
        if (priceFrom == null && priceTo == null) {
            return true;
        } else if (priceTo == null) {
            return priceToCheck.compareTo(priceFrom) > 0;
        } else {
            if (priceFrom == null) {
                priceFrom = 0.0f;
            }
            return (priceToCheck >= priceFrom && priceTo >= priceToCheck);
        }
    }
}
