package com.mdwairy.petclinic.controllers;

import com.mdwairy.petclinic.services.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("/vets")
public class VetController {

    private final VetService vetService;

    @Autowired
    public VetController(VetService vetService) {
        this.vetService = vetService;
    }

    @GetMapping({"/vets.html", "/vets/index", "/vets.index"})
    public String vetsHome(Model model) {
        model.addAttribute("vets", vetService.findAll());
        return "vets/vets";
    }
}
