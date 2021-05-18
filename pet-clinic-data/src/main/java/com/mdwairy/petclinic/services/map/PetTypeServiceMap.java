package com.mdwairy.petclinic.services.map;

import com.mdwairy.petclinic.model.PetType;
import com.mdwairy.petclinic.services.PetTypeService;
import org.springframework.stereotype.Service;

@Service
public class PetTypeServiceMap extends AbstractMapService<PetType, Long> implements PetTypeService {
}
