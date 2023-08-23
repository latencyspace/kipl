package com.kipl.kiplbackendserver.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "ingredient")
public class Ingredient {
    @Id
    @Column(name = "ingredient_name")
    private String ingredientName;

    @ManyToOne
    @JoinColumn(name = "allergy_name")
    private Allergy allergy;
}
