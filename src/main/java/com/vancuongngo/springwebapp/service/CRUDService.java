package com.vancuongngo.springwebapp.service;

import java.util.List;

public interface CRUDService<T> {
    List<?> listAll();

    T getById(int id);

    T saveOrUpdate(T domainObject);

    void delete(int id);
}
