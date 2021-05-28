package com.mdwairy.petclinic.controllers;

import com.mdwairy.petclinic.model.Pet;
import com.mdwairy.petclinic.model.Visit;
import com.mdwairy.petclinic.services.PetService;
import com.mdwairy.petclinic.services.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/owners/{ownerId}/pets/{petId}/visits")
public class VisitController {

    private final PetService petService;
    private final VisitService visitService;

    @Autowired
    public VisitController(PetService petService, VisitService visitService) {
        this.petService = petService;
        this.visitService = visitService;
    }

    @ModelAttribute("visit")
    public Visit loadPet(@PathVariable Long petId) {
        Pet pet =  petService.findById(petId);
        return Visit.builder().pet(pet).build();
    }

    @GetMapping("/new")
    public String initNewVisitForm() {
        return "pets/createOrUpdateVisitForm";
    }

    @PostMapping("/new")
    public String processNewVisitForm(Visit visit, @PathVariable("ownerId") Long ownerId) {
        visit.getPet().getVisits().add(visit);
        visitService.save(visit);
        return "redirect:/owners/" + ownerId;
    }
}
