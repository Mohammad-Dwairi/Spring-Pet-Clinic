package com.mdwairy.petclinic.repositories;

import com.mdwairy.petclinic.model.Vet;
import org.springframework.data.repository.CrudRepository;

public interface VetRepository extends CrudRepository<Vet, Long> {
}
