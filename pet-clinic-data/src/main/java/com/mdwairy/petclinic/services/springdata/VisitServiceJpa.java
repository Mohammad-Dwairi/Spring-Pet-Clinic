package com.mdwairy.petclinic.services.springdata;

import com.mdwairy.petclinic.model.Visit;
import com.mdwairy.petclinic.repositories.VisitRepository;
import com.mdwairy.petclinic.services.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springjpa")
public class VisitServiceJpa implements VisitService {

    private final VisitRepository visitRepository;

    @Autowired
    public VisitServiceJpa(VisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }

    @Override
    public Visit findById(Long aLong) {
        return visitRepository.findById(aLong).orElse(null);
    }

    @Override
    public Visit save(Visit type) {
        return visitRepository.save(type);
    }

    @Override
    public Set<Visit> findAll() {
        Set<Visit> visits = new HashSet<>();
        visitRepository.findAll().forEach(visits::add);
        return visits;
    }

    @Override
    public void delete(Visit type) {
        visitRepository.delete(type);
    }

    @Override
    public void deleteById(Long aLong) {
        visitRepository.deleteById(aLong);
    }
}
