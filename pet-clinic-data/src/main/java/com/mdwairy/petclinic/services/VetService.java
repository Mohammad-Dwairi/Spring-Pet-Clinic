package com.mdwairy.petclinic.services;

import com.mdwairy.petclinic.model.Vet;

import java.util.Set;

public interface VetService {
    Vet findById(Long id);
    Vet Save(Vet vet);
    Set<Vet> findAll();
}
