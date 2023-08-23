package com.kipl.kiplbackendserver.controller;

import com.kipl.kiplbackendserver.DTO.AllergyDTO;
import com.kipl.kiplbackendserver.DTO.FoodDTO;
import com.kipl.kiplbackendserver.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    private final FoodEvaluationService foodEvaluateService;
    private final PersonalPrivacyService personalPrivacyService;
    private final AllergyService allergyService;
    private final BigqueryService bigqueryService;
    private final PythonService pythonService;

    @Autowired
    public TestController(
            FoodEvaluationService foodEvaluateService,
            PersonalPrivacyService personalPrivacyService,
            AllergyService allergyService,
            BigqueryService bigqueryService,
            PythonService pythonService
    ) {
        this.foodEvaluateService = foodEvaluateService;
        this.personalPrivacyService = personalPrivacyService;
        this.allergyService = allergyService;
        this.bigqueryService = bigqueryService;
        this.pythonService = pythonService;
    }

    @PostMapping("/addAllergy")
    public void addAllergy(@RequestBody AllergyDTO allergyDTO) {
        allergyService.saveAllergy(allergyDTO);
    }

    @GetMapping("/getAllFood")
    public ResponseEntity<List<FoodDTO>> getAllFood() throws Exception {
        return ResponseEntity.ok().body(bigqueryService.getAllFood());
    }
}
