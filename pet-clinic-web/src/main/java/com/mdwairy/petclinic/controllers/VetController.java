package com.mdwairy.petclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VetController {

    @GetMapping({"/vets", "/vets/index", "/vets.index"})
    public String vetsHome() {
        return "vets/home";
    }
}
