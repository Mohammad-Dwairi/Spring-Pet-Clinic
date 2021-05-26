package com.mdwairy.petclinic.services.map;

import com.mdwairy.petclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerServiceMapTest {

    OwnerServiceMap ownerServiceMap;
    Long ownerId = 1L;
    String lastName = "Dwairi";

    @BeforeEach
    void setUp() {
        ownerServiceMap = new OwnerServiceMap(new PetTypeServiceMap(), new PetServiceMap());
        ownerServiceMap.save(Owner.builder().id(ownerId).lastName(lastName).build());
    }

    @Test
    void findById() {
        Owner owner = ownerServiceMap.findById(ownerId);
        assertEquals(ownerId, owner.getId());
    }

    @Test
    void saveWithId() {
        Owner owner = Owner.builder().id(2L).lastName("Ahmad").build();
        Owner savedOwner = ownerServiceMap.save(owner);
        assertEquals(2L, savedOwner.getId());
    }

    @Test
    void saveNoId() {
        Owner owner = Owner.builder().lastName("Ahmad").build();
        Owner savedOwner = ownerServiceMap.save(owner);
        assertNotNull(savedOwner);
        assertNotNull(savedOwner.getId());
    }

    @Test
    void findAll() {
        Set<Owner> owners = ownerServiceMap.findAll();
        assertEquals(1, owners.size());
    }

    @Test
    void delete() {
        ownerServiceMap.delete(ownerServiceMap.findById(ownerId));
        assertEquals(0, ownerServiceMap.findAll().size());
    }

    @Test
    void deleteById() {
        ownerServiceMap.deleteById(ownerId);
        assertEquals(0, ownerServiceMap.findAll().size());
    }

    @Test
    void findByLastName() {
        Owner owner = ownerServiceMap.findByLastName(lastName);
        assertEquals(lastName, owner.getLastName());
    }

    @Test
    void lastNameNotFound() {
        Owner owner = ownerServiceMap.findByLastName("Mohammad");
        assertNull(owner);
    }
}