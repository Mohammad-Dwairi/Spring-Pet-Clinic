package com.mdwairy.petclinic.controllers;

import com.mdwairy.petclinic.model.Owner;
import com.mdwairy.petclinic.model.Pet;
import com.mdwairy.petclinic.model.PetType;
import com.mdwairy.petclinic.services.OwnerService;
import com.mdwairy.petclinic.services.PetService;
import com.mdwairy.petclinic.services.PetTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
@RequestMapping("/owners/{ownerId}/pets")
@SessionAttributes("pet")
public class PetController {

    private final PetService petService;
    private final OwnerService ownerService;
    private final PetTypeService petTypeService;

    @Autowired
    public PetController(PetService petService, OwnerService ownerService, PetTypeService petTypeService) {
        this.petService = petService;
        this.ownerService = ownerService;
        this.petTypeService = petTypeService;
    }

    @ModelAttribute("types")
    public Collection<PetType> populateModelPetTypes() {
        return petTypeService.findAll();
    }

    @ModelAttribute("owner")
    public void addModelOwner(@PathVariable Long ownerId, Model model) {
        model.addAttribute("owner", ownerService.findById(ownerId));
    }

    @InitBinder("owner")
    public void initOwnerBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/new")
    public String initNewPetForm(@ModelAttribute("owner") Owner owner, Model model) {
        model.addAttribute("pet", Pet.builder().owner(owner).build());
        return "pets/createOrUpdatePetForm";
    }

    @PostMapping("/new")
    public String processNewPetForm(@ModelAttribute("pet") Pet pet) {

        // Cannot use pet.getOwner().getPets(). because the session is closed (Lazy initialization).
        Long ownerId = pet.getOwner().getId();
        Owner owner = ownerService.findById(ownerId);

        owner.getPets().add(pet);
        petService.save(pet);

        return "redirect:/owners/" + ownerId;
    }

    @GetMapping("/{petId}/edit")
    public String initUpdatePetForm(@PathVariable Long petId, Model model) {
        model.addAttribute("pet", petService.findById(petId));
        return "pets/createOrUpdatePetForm";
    }

    @PostMapping("/{petId}/edit")
    public String processUpdatePetForm(@ModelAttribute("pet") Pet pet) {
        Pet savedPet = petService.save(pet);
        return "redirect:/owners/" + savedPet.getOwner().getId();
    }
}
