package edu.t1.pract4.dao;

import java.util.List;
import java.util.Optional;

public interface SimpleDao<T, K> {
    Optional<T> findById(K id);
    void save(T t);
    void deleteById(K id);
    List<T> findAll();
}
