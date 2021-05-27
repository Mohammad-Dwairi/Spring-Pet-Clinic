package com.mdwairy.petclinic.controllers;

import com.mdwairy.petclinic.model.Owner;
import com.mdwairy.petclinic.services.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/owners")
public class OwnerController {

    private final OwnerService ownerService;

    @Autowired
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder webDataBinder) {
        // disable any external binding to id property.
        webDataBinder.setDisallowedFields("id");
    }

    @GetMapping("/find")
    public String initFindOwnersForm(Model model) {
        model.addAttribute("owner", Owner.builder().build());
        return "/owners/findOwners";
    }

    @PostMapping
    public String processFindOwnersForm(@ModelAttribute("owner") Owner owner, BindingResult bindingResult, Model model) {

        if (owner.getLastName() == null) {
            Set<Owner> results = ownerService.findAll();
            model.addAttribute("owners", results);
            return "owners/ownersList";
        }

        List<Owner> results = ownerService.findAllByLastNameLike("%" + owner.getLastName() + "%");

        if (results.isEmpty()) {
            bindingResult.rejectValue("lastName", "notFound", "not found");
            return "owners/findOwners";
        }
        else if (results.size() == 1) {
            return "redirect:/owners/" + results.get(0).getId();
        }

        model.addAttribute("owners", results);
        return "owners/ownersList";
    }

    @GetMapping("/{ownerId}")
    public String ownerDetails(Model model, @PathVariable Long ownerId) {
        model.addAttribute("owner", ownerService.findById(ownerId));
        return "owners/ownerDetails";
    }

    @GetMapping("/new")
    public String initNewOwnerForm(Model model) {
        model.addAttribute("owner", Owner.builder().build());
        return "owners/createOrUpdateOwnerForm";
    }

    @PostMapping("/new")
    public String processNewOwnerForm(@ModelAttribute Owner owner, BindingResult result) {
        if (result.hasErrors()) {
            return "owners/createOrUpdateOwnerForm";
        }
        Owner savedOwner = ownerService.save(owner);
        return "redirect:/owners/" + savedOwner.getId();
    }

    @GetMapping("/{ownerId}/edit")
    public String initUpdateOwnerForm(@PathVariable Long ownerId, Model model) {
        model.addAttribute("owner", ownerService.findById(ownerId));
        return "owners/createOrUpdateOwnerForm";
    }

    @PostMapping("/{ownerId}/edit")
    public String processUpdateOwnerForm(@PathVariable Long ownerId, @ModelAttribute Owner owner, BindingResult result) {
        if (result.hasErrors()) {
            return "owners/createOrUpdateOwnerForm";
        }
        owner.setId(ownerId);
        Owner savedOwner = ownerService.save(owner);
        return "redirect:/owners/" + savedOwner.getId();
    }
}
