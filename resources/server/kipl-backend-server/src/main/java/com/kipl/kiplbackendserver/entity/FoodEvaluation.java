package com.kipl.kiplbackendserver.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class FoodEvaluation {
    @Id
    @GeneratedValue(generator = "custom-id")
    @GenericGenerator(
            name = "custom-id",
            strategy = "com.kipl.kiplbackendserver.config.FoodCustomIdGenerator"
    )
    private String foodEvaluationId;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private PersonalPrivacy personalPrivacy;

    @Column(name = "food_name")
    private String foodName;

    @Column(name = "food_evaluation")
    private Long foodEvaluation;
}
