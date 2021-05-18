package com.mdwairy.petclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @GetMapping({"", "/", "/index", "index.html"})
    public String index() {
        return "index";
    }

    @RequestMapping("/oups")
    public String error() {
        /*
        By default Spring Boot maps /error to BasicErrorController which populates model with error attributes and then
        returns 'error' as the view name to map application defined error pages.
        */
        throw new RuntimeException(
                "Expected: controller used to showcase what " + "happens when an exception is thrown");
    }
}
