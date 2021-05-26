package com.mdwairy.petclinic.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "specialties")
public class Speciality extends BaseEntity {

    @Builder
    public Speciality(Long id, String description) {
        super(id);
        this.description = description;
    }

    @Column(name = "description")
    private String description;

}
