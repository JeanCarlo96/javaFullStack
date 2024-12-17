package com.mitocode.service;

import java.util.List;

public interface ICRUD<T, ID> {

    T save(T t);
    T updade(ID id, T patient);
    List<T> findAll();
    T findById(ID id);
    void delete(ID id);

}
