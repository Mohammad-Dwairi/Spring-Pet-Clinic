package com.mdwairy.petclinic.services.springdata;

import com.mdwairy.petclinic.model.Speciality;
import com.mdwairy.petclinic.repositories.SpecialtyRepository;
import com.mdwairy.petclinic.services.SpecialityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class SpecialtyServiceJpa implements SpecialityService {

    private final SpecialtyRepository specialtyRepository;

    @Autowired
    public SpecialtyServiceJpa(SpecialtyRepository specialtyRepository) {
        this.specialtyRepository = specialtyRepository;
    }

    @Override
    public Speciality findById(Long aLong) {
        return specialtyRepository.findById(aLong).orElse(null);
    }

    @Override
    public Speciality save(Speciality type) {
        return specialtyRepository.save(type);
    }

    @Override
    public Set<Speciality> findAll() {
        Set<Speciality> specialities = new HashSet<>();
        specialtyRepository.findAll().forEach(specialities::add);
        return specialities;
    }

    @Override
    public void delete(Speciality type) {
        specialtyRepository.delete(type);
    }

    @Override
    public void deleteById(Long aLong) {
        specialtyRepository.deleteById(aLong);
    }
}
