package com.kipl.kiplbackendserver.repository;

import com.kipl.kiplbackendserver.entity.FoodEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodEvaluationRepository extends JpaRepository<FoodEvaluation, String> {

}
