package com.mdwairy.petclinic.services.map;

import com.mdwairy.petclinic.model.Speciality;
import com.mdwairy.petclinic.services.SpecialityService;
import org.springframework.stereotype.Service;

@Service
public class SpecialityServiceMap extends AbstractMapService<Speciality, Long> implements SpecialityService {
}
