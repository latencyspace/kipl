package com.kipl.kiplbackendserver.service;

import com.kipl.kiplbackendserver.DTO.FoodEvaluationDTO;
import com.kipl.kiplbackendserver.entity.FoodEvaluation;
import com.kipl.kiplbackendserver.repository.FoodEvaluationRepository;
import com.kipl.kiplbackendserver.repository.PersonalPrivacyRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoodEvaluationService {
    private final FoodEvaluationRepository foodEvaluationRepository;
    private final PersonalPrivacyRepository personalPrivacyRepository;

    @Autowired
    public FoodEvaluationService(
        FoodEvaluationRepository foodEvaluationRepository,
        PersonalPrivacyRepository personalPrivacyRepository
    ){
        this.foodEvaluationRepository = foodEvaluationRepository;
        this.personalPrivacyRepository = personalPrivacyRepository;
    }

    public void save(FoodEvaluationDTO dto){
        foodEvaluationRepository.save(
                FoodEvaluation.builder()
                        .personalPrivacy(personalPrivacyRepository.getPersonalPrivacyByPersonId(dto.getPersonId()))
                        .foodName(dto.getFoodName())
                        .foodEvaluation(dto.getFoodEvaluation())
                        .build()
        );
    }
}
