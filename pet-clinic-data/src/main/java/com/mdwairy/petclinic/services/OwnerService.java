package com.mdwairy.petclinic.services;

import com.mdwairy.petclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long> {
    Owner findByLastName(String lastName);
}
