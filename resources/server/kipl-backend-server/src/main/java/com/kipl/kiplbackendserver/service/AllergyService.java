package com.kipl.kiplbackendserver.service;

import com.kipl.kiplbackendserver.DTO.AllergyDTO;
import com.kipl.kiplbackendserver.entity.Allergy;
import com.kipl.kiplbackendserver.repository.AllergyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AllergyService {
    private final AllergyRepository allergyRepository;

    @Autowired
    public AllergyService(
            AllergyRepository allergyRepository
    ){
        this.allergyRepository = allergyRepository;
    }

    public void saveAllergy(AllergyDTO allergyDTO){
        allergyRepository.save(
                Allergy.builder()
                        .allergyName(allergyDTO.getAllergyName())
                        .build()
        );
    }
}
