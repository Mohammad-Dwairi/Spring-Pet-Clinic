package com.mdwairy.petclinic.services.map;

import com.mdwairy.petclinic.model.Vet;
import com.mdwairy.petclinic.services.VetService;
import org.springframework.stereotype.Service;

@Service
public class VetServiceMap extends AbstractMapService<Vet, Long> implements VetService {
}
