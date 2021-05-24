package com.mdwairy.petclinic.services.map;

import com.mdwairy.petclinic.model.Pet;
import com.mdwairy.petclinic.services.PetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile({"default", "map"})
public class PetServiceMap extends AbstractMapService<Pet, Long> implements PetService {

}
