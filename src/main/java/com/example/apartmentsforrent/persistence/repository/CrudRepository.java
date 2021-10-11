package com.example.apartmentsforrent.persistence.repository;

import java.util.Optional;

public interface CrudRepository<T, I> {
    T save(T entity);
    Optional<T> findById(I id);
    boolean existsById(I id);
    Iterable<T> findAll();
    void deleteById(I id);
}
