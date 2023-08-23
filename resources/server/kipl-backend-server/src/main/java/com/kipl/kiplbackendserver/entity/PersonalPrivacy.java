package com.kipl.kiplbackendserver.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "personal_privacy")
public class PersonalPrivacy {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "person_id", columnDefinition = "BINARY(16)", insertable = false, updatable = false, nullable = false)
    private UUID personId;

    @Column(name = "person_name")
    private String personName;

    @Column(name = "birthday")
    private Date birthday;

    @ManyToMany
    @JoinTable(
            name = "personal_allergy",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "allergy_name")
    )
    private List<Allergy> allergies;

    @OneToMany(mappedBy = "personalPrivacy")
    private List<FoodEvaluation> foodEvaluations;
}
