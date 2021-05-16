package com.mdwairy.petclinic.services;

import com.mdwairy.petclinic.model.BaseEntity;

import java.util.Set;

public interface CrudService<T extends BaseEntity, ID extends Long> {
    T findById(ID id);
    T save(ID id, T type);
    Set<T> findAll();
    void delete(T type);
    void deleteById(ID id);
}
