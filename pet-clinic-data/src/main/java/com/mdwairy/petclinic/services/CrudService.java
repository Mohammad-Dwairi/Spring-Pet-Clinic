package com.mdwairy.petclinic.services;

import java.util.Set;

public interface CrudService<T, ID> {
    T findById(ID id);
    T Save(T type);
    Set<T> findAll();
}
