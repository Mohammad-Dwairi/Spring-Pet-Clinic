package com.mdwairy.petclinic.controllers;

import com.mdwairy.petclinic.model.Owner;
import com.mdwairy.petclinic.model.Pet;
import com.mdwairy.petclinic.model.PetType;
import com.mdwairy.petclinic.services.OwnerService;
import com.mdwairy.petclinic.services.PetService;
import com.mdwairy.petclinic.services.PetTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class PetControllerTest {

    @Mock
    PetService petService;

    @Mock
    OwnerService ownerService;

    @Mock
    PetTypeService petTypeService;

    @InjectMocks
    PetController petController;

    MockMvc mockMvc;

    Set<PetType> types;

    Owner returnedOwner;


    @BeforeEach
    void setUp() {
        returnedOwner = Owner.builder().id(1L).build();

        types = new HashSet<>();
        types.add(PetType.builder().id(1L).name("Dog").build());
        types.add(PetType.builder().id(2L).name("Cat").build());

        mockMvc = MockMvcBuilders.standaloneSetup(petController).build();
    }

    @Test
    void initNewPetForm() throws Exception {
        when(petTypeService.findAll()).thenReturn(types);
        when(ownerService.findById(1L)).thenReturn(returnedOwner);

        mockMvc.perform(get("/owners/1/pets/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("pets/createOrUpdatePetForm"))
                .andExpect(model().attributeExists("owner"))
                .andExpect(model().attribute("types", hasSize(2)))
                .andExpect(model().attributeExists("pet"));
    }

    @Test
    void processNewPetForm() throws Exception {
        when(ownerService.findById(anyLong())).thenReturn(returnedOwner);
        when(petTypeService.findAll()).thenReturn(types);

        mockMvc.perform(post("/owners/1/pets/new").sessionAttr("pet", Pet.builder().owner(returnedOwner).build()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"))
                .andExpect(model().attributeExists("owner"))
                .andExpect(model().attributeExists("pet"));

        verify(petService).save(any());
    }
}