package com.mdwairy.petclinic.services.map;

import com.mdwairy.petclinic.model.PetType;
import com.mdwairy.petclinic.services.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile({"default", "map"})
public class PetTypeServiceMap extends AbstractMapService<PetType, Long> implements PetTypeService {
}
