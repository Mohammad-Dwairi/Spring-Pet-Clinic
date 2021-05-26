package com.mdwairy.petclinic.bootstrap;

import com.mdwairy.petclinic.model.*;
import com.mdwairy.petclinic.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialtyService;
    private final VisitService visitService;

    @Autowired
    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService,
                      SpecialityService specialtyService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialtyService = specialtyService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {

        PetType dog = PetType.builder().name("Dog").build();
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = PetType.builder().name("Cat").build();
        PetType savedCatPetType = petTypeService.save(cat);

        Speciality radiology = Speciality.builder().description("Radiology").build();
        Speciality savedRadiology = specialtyService.save(radiology);

        Speciality surgery = Speciality.builder().description("Surgery").build();
        Speciality savedSurgery = specialtyService.save(surgery);

        Speciality dentistry = Speciality.builder().description("Surgery").build();
        Speciality savedDentistry = specialtyService.save(dentistry);

        Owner owner1 = Owner.builder()
                .firstName("Michael")
                .lastName("Weston")
                .address("123 Brickerel")
                .city("Miami")
                .phoneNumber("1231231234").build();

        Pet mikesPet = Pet.builder()
                .petType(savedDogPetType)
                .owner(owner1)
                .birthDate(LocalDate.now())
                .name("Rosco").build();

        owner1.getPets().add(mikesPet);

        ownerService.save(owner1);

        Owner owner2 = Owner.builder()
                .firstName("Fiona")
                .lastName("Glenanne")
                .address("123 Brickerel")
                .city("Miami")
                .phoneNumber("1231231234").build();

        Pet fionasCat = Pet.builder()
                .petType(savedCatPetType)
                .owner(owner2)
                .birthDate(LocalDate.now())
                .name("Just Cat").build();

        owner2.getPets().add(fionasCat);

        ownerService.save(owner2);

        Visit catVisit = Visit.builder().pet(fionasCat).date(LocalDate.now()).description("Sneezy Kitty").build();
        visitService.save(catVisit);

        System.out.println("Loaded Owners....");

        Vet vet1 = Vet.builder().firstName("Sam").lastName("Axe").build();
        vet1.getSpecialities().add(savedRadiology);

        vetService.save(vet1);

        Vet vet2 = Vet.builder().firstName("Jessie").lastName("Porter").build();
        vet2.getSpecialities().add(savedSurgery);

        vetService.save(vet2);

        System.out.println("Loaded Vets....");
    }
}
