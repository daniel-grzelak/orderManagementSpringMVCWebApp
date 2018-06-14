package com.daniel.dao.interfaces;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
@Transactional
public interface GenericDao<T> {
    T add(T t);
    T modify(T t);
    void delete(Long id);
    Optional<T> getById(Long id);
    List<T> getAll();
}
