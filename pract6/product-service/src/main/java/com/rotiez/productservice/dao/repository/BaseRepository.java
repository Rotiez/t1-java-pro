package com.rotiez.productservice.dao.repository;

import java.util.List;
import java.util.Optional;

public interface BaseRepository<T, K> {
    void upsert(T t);
    Optional<T> findById(K id);
    List<T> findAll();
    void deleteById(K id);
}
