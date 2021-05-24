package com.mdwairy.petclinic.repositories;

import com.mdwairy.petclinic.model.Visit;
import org.springframework.data.repository.CrudRepository;

public interface VisitRepository extends CrudRepository<Visit, Long> {
}
