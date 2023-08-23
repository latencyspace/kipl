package com.kipl.kiplbackendserver.repository;

import com.kipl.kiplbackendserver.entity.Allergy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AllergyRepository extends JpaRepository<Allergy, String> {
    Allergy getAllergyByAllergyName(String allergyName);
}
