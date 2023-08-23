package com.kipl.kiplbackendserver.DTO;

import lombok.Getter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Getter
@ToString
public class PersonalPrivacyDTO {
    private String personName;
    private Date birthday;
    private List<AllergyDTO> allergies;
}