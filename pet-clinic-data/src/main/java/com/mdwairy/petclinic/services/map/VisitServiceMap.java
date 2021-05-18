package com.mdwairy.petclinic.services.map;

import com.mdwairy.petclinic.model.Visit;
import com.mdwairy.petclinic.services.VisitService;
import org.springframework.stereotype.Service;

@Service
public class VisitServiceMap extends AbstractMapService<Visit, Long> implements VisitService {
}
