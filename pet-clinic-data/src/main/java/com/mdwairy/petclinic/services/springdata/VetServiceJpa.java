package com.mdwairy.petclinic.services.springdata;

import com.mdwairy.petclinic.model.Vet;
import com.mdwairy.petclinic.repositories.VetRepository;
import com.mdwairy.petclinic.services.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springjpa")
public class VetServiceJpa implements VetService {

    private final VetRepository vetRepository;

    @Autowired
    public VetServiceJpa(VetRepository vetRepository) {
        this.vetRepository = vetRepository;
    }

    @Override
    public Vet findById(Long id) {
        return vetRepository.findById(id).orElse(null);
    }

    @Override
    public Vet save(Vet type) {
        return vetRepository.save(type);
    }

    @Override
    public Set<Vet> findAll() {
        Set<Vet> vets = new HashSet<>();
        vetRepository.findAll().forEach(vets::add);
        return vets;
    }

    @Override
    public void delete(Vet type) {
        vetRepository.delete(type);
    }

    @Override
    public void deleteById(Long id) {
        vetRepository.deleteById(id);
    }
}
