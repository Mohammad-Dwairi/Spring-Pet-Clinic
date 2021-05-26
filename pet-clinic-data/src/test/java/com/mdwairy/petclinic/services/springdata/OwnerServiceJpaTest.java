package com.mdwairy.petclinic.services.springdata;

import com.mdwairy.petclinic.model.Owner;
import com.mdwairy.petclinic.repositories.OwnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerServiceJpaTest {

    final String LAST_NAME = "Dwairi";
    final Long ID = 1L;

    @Mock
    OwnerRepository ownerRepository;

    @InjectMocks
    OwnerServiceJpa ownerServiceJpa;

    Owner returnedOwner;

    @BeforeEach
    void setUp() {
        returnedOwner = Owner.builder().id(ID).lastName(LAST_NAME).build();
    }

    @Test
    void findAll() {
        Set<Owner> returnedOwners = new HashSet<>();

        Owner owner1 = Owner.builder().id(1L).build();
        Owner owner2 = Owner.builder().id(2L).lastName(LAST_NAME).build();

        returnedOwners.add(owner1);
        returnedOwners.add(owner2);

        when(ownerRepository.findAll()).thenReturn(returnedOwners);

        Set<Owner> owners = ownerServiceJpa.findAll();

        assertNotNull(owners);
        assertEquals(2, owners.size());
    }

    @Test
    void findById() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(returnedOwner));
        Owner owner = ownerServiceJpa.findById(ID);

        assertNotNull(owner);
        assertEquals(ID, owner.getId());
    }

    @Test
    void findByIdNotFound() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.empty());
        Owner owner = ownerServiceJpa.findById(ID);
        assertNull(owner);
    }

    @Test
    void findByLastName() {
        when(ownerRepository.findByLastName(LAST_NAME)).thenReturn(returnedOwner);
        Owner owner = ownerServiceJpa.findByLastName(LAST_NAME);
        assertNotNull(owner);
        assertEquals(ID, owner.getId());
        assertEquals(LAST_NAME, owner.getLastName());
    }

    @Test
    void saveWithId() {
        when(ownerRepository.save(any())).thenReturn(returnedOwner);

        Owner owner = Owner.builder().id(ID).build();

        Owner savedOwner = ownerServiceJpa.save(owner);

        assertNotNull(savedOwner);
        assertEquals(ID, savedOwner.getId());
    }

    @Test
    void saveWithoutID() {
        when(ownerRepository.save(any())).thenReturn(returnedOwner);
        Owner savedOwner = ownerServiceJpa.save(new Owner());
        assertEquals(ID, savedOwner.getId());
    }

    @Test
    void delete() {
        ownerServiceJpa.delete(returnedOwner);
        verify(ownerRepository, times(1)).delete(any());
    }

    @Test
    void deleteById() {
        ownerServiceJpa.deleteById(ID);
        verify(ownerRepository, times(1)).deleteById(anyLong());
    }


}