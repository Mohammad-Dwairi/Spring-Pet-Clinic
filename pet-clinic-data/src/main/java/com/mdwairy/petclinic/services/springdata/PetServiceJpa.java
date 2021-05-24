package com.mdwairy.petclinic.services.springdata;

import com.mdwairy.petclinic.model.Pet;
import com.mdwairy.petclinic.repositories.PetRepository;
import com.mdwairy.petclinic.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class PetServiceJpa implements PetService {

    private final PetRepository petRepository;

    @Autowired
    public PetServiceJpa(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public Pet findById(Long id) {
        return petRepository.findById(id).orElse(null);
    }

    @Override
    public Pet save(Pet type) {
        return petRepository.save(type);
    }

    @Override
    public Set<Pet> findAll() {
        Set<Pet> pets = new HashSet<>();
        petRepository.findAll().forEach(pets::add);
        return pets;
    }

    @Override
    public void delete(Pet type) {
        petRepository.delete(type);
    }

    @Override
    public void deleteById(Long id) {
        petRepository.deleteById(id);
    }
}
