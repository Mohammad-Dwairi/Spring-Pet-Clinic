package com.mdwairy.petclinic.services.map;

import com.mdwairy.petclinic.model.Owner;
import com.mdwairy.petclinic.services.OwnerService;
import org.springframework.stereotype.Service;

@Service
public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements OwnerService {

    @Override
    public Owner findByLastName(String lastName) {
        return null;
    }
}
