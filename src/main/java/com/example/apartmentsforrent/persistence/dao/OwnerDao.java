package com.example.apartmentsforrent.persistence.dao;

import com.example.apartmentsforrent.persistence.model.Owner;

import java.util.List;

public interface OwnerDao extends CrudDao<Owner, Long> {
    List<Owner> findByEmail(String email);
}
