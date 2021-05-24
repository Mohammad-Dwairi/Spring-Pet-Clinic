package com.mdwairy.petclinic.services.map;

import com.mdwairy.petclinic.model.Owner;
import com.mdwairy.petclinic.model.Pet;
import com.mdwairy.petclinic.services.OwnerService;
import com.mdwairy.petclinic.services.PetService;
import com.mdwairy.petclinic.services.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile({"default", "map"})
public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements OwnerService {

    private final PetTypeService petTypeService;
    private final PetService petService;

    public OwnerServiceMap(PetTypeService petTypeService, PetService petService) {
        this.petTypeService = petTypeService;
        this.petService = petService;
    }

    @Override
    public Owner save(Owner object) {
        if(object != null){
            if (!object.getPets().isEmpty()) {
                object.getPets().forEach(pet -> {
                    if (pet.getPetType() != null){
                        if(pet.getPetType().getId() == null){
                            pet.setPetType(petTypeService.save(pet.getPetType()));
                        }
                    } else {
                        throw new RuntimeException("Pet Type is required");
                    }

                    if(pet.getId() == null){
                        Pet savedPet = petService.save(pet);
                        pet.setId(savedPet.getId());
                    }
                });
            }
            return super.save(object);

        } else {
            return null;
        }
    }

    @Override
    public Owner findByLastName(String lastName) {
        return null;
    }
}
