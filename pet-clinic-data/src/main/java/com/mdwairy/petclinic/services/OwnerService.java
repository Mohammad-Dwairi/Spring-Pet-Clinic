package com.mdwairy.petclinic.services;

import com.mdwairy.petclinic.model.Owner;

import java.util.Set;

public interface OwnerService {
    Owner findById(Long id);
    Owner findByLastName(String lastName);
    Owner Save(Owner owner);
    Set<Owner> findAll();
}
