package com.mdwairy.petclinic.services.map;

import com.mdwairy.petclinic.model.Speciality;
import com.mdwairy.petclinic.model.Vet;
import com.mdwairy.petclinic.services.SpecialityService;
import com.mdwairy.petclinic.services.VetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile({"default", "map"})
public class VetServiceMap extends AbstractMapService<Vet, Long> implements VetService {

    private final SpecialityService specialtyService;

    public VetServiceMap(SpecialityService specialtyService) {
        this.specialtyService = specialtyService;
    }

    @Override
    public Vet save(Vet object) {
        if (!object.getSpecialities().isEmpty()){
            object.getSpecialities().forEach(speciality -> {
                if(speciality.getId() == null){
                    Speciality savedSpecialty = specialtyService.save(speciality);
                    speciality.setId(savedSpecialty.getId());
                }
            });
        }
        return super.save(object);
    }
}
