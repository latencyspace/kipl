package com.kipl.kiplbackendserver.service;

import com.kipl.kiplbackendserver.DTO.AllergyDTO;
import com.kipl.kiplbackendserver.DTO.PersonalPrivacyDTO;
import com.kipl.kiplbackendserver.entity.Allergy;
import com.kipl.kiplbackendserver.entity.PersonalPrivacy;
import com.kipl.kiplbackendserver.repository.AllergyRepository;
import com.kipl.kiplbackendserver.repository.PersonalPrivacyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class PersonalPrivacyService {
    private final PersonalPrivacyRepository personalPrivacyRepository;
    private final AllergyRepository allergyRepository;

    @Autowired
    PersonalPrivacyService(
            PersonalPrivacyRepository personalPrivacyRepository,
            AllergyRepository allergyRepository
    ) {
        this.personalPrivacyRepository = personalPrivacyRepository;
        this.allergyRepository = allergyRepository;
    }

    public void addPersonalPrivacy(PersonalPrivacyDTO personalPrivacyDTO) {

        List<Allergy> allergies = new LinkedList();

        for (AllergyDTO allergy : personalPrivacyDTO.getAllergies()) {
            allergies.add(allergyRepository.getAllergyByAllergyName(allergy.getAllergyName()));
        }

        personalPrivacyRepository.save(
                PersonalPrivacy.builder()
                        .personName(personalPrivacyDTO.getPersonName())
                        .birthday(personalPrivacyDTO.getBirthday())
                        .allergies(allergies)
                        .build()
        );
    }
}
