package com.kipl.kiplbackendserver.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "allergy")
public class Allergy {
    @Id
    @Column(name = "allergy_name")
    private String allergyName;

    @ManyToMany(mappedBy = "allergies")
    private List<PersonalPrivacy> relatedPeople;
}
