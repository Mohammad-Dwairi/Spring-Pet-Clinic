package com.mdwairy.petclinic.controllers;

import com.mdwairy.petclinic.services.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/owners")
public class OwnerController {

    private final OwnerService ownerService;

    @Autowired
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping
    public String ownerHome(Model model) {
        model.addAttribute("owners", ownerService.findAll());
        return "owners/home";
    }

    @GetMapping("/{ownerId}")
    public String ownerDetails(Model model, @PathVariable Long ownerId) {
        model.addAttribute("owner", ownerService.findById(ownerId));
        return "owners/ownerDetails";
    }



}
