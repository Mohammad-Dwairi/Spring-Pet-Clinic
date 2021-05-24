package com.mdwairy.petclinic.repositories;

import com.mdwairy.petclinic.model.PetType;
import org.springframework.data.repository.CrudRepository;

public interface PetTypeRepository extends CrudRepository<PetType, Long> {
}
