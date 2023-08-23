package com.kipl.kiplbackendserver.repository;

import com.kipl.kiplbackendserver.entity.PersonalPrivacy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PersonalPrivacyRepository extends JpaRepository<PersonalPrivacy, Long> {
    PersonalPrivacy getPersonalPrivacyByPersonId(UUID personId);
}
