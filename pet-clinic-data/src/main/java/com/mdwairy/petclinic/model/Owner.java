package com.mdwairy.petclinic.model;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class Owner extends Person {

    private String address;
    private String city;
    private String phoneNumber;
    private Set<Pet> pets = new HashSet<>();

}
