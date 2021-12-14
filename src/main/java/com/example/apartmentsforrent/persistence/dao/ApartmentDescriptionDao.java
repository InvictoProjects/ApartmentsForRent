package com.example.apartmentsforrent.persistence.dao;

import com.example.apartmentsforrent.persistence.model.ApartmentDescription;
import com.example.apartmentsforrent.persistence.model.BuildingType;

import java.util.List;

public interface ApartmentDescriptionDao extends CrudDao<ApartmentDescription, Long> {
    List<ApartmentDescription> findByBuildingType(BuildingType buildingType);
}
