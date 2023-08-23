package com.kipl.kiplbackendserver.DTO;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@Getter
@ToString
public class FoodEvaluationDTO {
    @JsonDeserialize
    private UUID personId;
    private String foodName;
    private Long foodEvaluation;
}
