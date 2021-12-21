package com.example.apartmentsforrent.persistence.dao;

import java.util.Optional;

public interface CrudDao<T, I> {
    T create(T entity);

    Optional<T> read(I id);

    void update(T entity);

    void delete(T entity);

}
