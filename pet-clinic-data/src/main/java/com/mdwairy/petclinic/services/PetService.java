package com.mdwairy.petclinic.services;

import com.mdwairy.petclinic.model.Pet;

import java.util.Set;

public interface PetService {
    Pet findById(Long id);
    Pet Save(Pet pet);
    Set<Pet> findAll();
}
