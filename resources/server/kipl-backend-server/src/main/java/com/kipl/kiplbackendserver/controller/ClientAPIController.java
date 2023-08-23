package com.kipl.kiplbackendserver.controller;


import com.kipl.kiplbackendserver.DTO.FoodDTO;
import com.kipl.kiplbackendserver.DTO.FoodEvaluationDTO;
import com.kipl.kiplbackendserver.DTO.MenuBoardDTO;
import com.kipl.kiplbackendserver.DTO.PersonalPrivacyDTO;
import com.kipl.kiplbackendserver.service.BigqueryService;
import com.kipl.kiplbackendserver.service.FoodEvaluationService;
import com.kipl.kiplbackendserver.service.PersonalPrivacyService;
import com.kipl.kiplbackendserver.service.PythonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class ClientAPIController {

    private final FoodEvaluationService foodEvaluateService;
    private final PersonalPrivacyService personalPrivacyService;
    private final BigqueryService bigqueryService;
    private final PythonService pythonService;

    @Autowired
    public ClientAPIController(
            FoodEvaluationService foodEvaluateService,
            PersonalPrivacyService personalPrivacyService,
            BigqueryService bigqueryService,
            PythonService pythonService
    ) {
        this.foodEvaluateService = foodEvaluateService;
        this.personalPrivacyService = personalPrivacyService;
        this.bigqueryService = bigqueryService;
        this.pythonService = pythonService;
    }

    @PostMapping("/addPersonalPrivacy")
    public ResponseEntity<PersonalPrivacyDTO> addPersonalPrivacy(@RequestBody PersonalPrivacyDTO personalPrivacyDTO) {
        personalPrivacyService.addPersonalPrivacy(personalPrivacyDTO);
        return ResponseEntity.ok().body(personalPrivacyDTO);
    }

    @PostMapping("/addMenuBoard")
    public void addMenuBoard(@RequestParam UUID userId, @RequestBody MenuBoardDTO menuBoardDTO) {
        // 미구현
    }

    @PostMapping("/addNewFood")
    public ResponseEntity<Boolean> addNewFood(@RequestBody FoodDTO foodDTO) throws Exception {
        if (pythonService.isBabyFood(foodDTO)) {
            // DB에 저장한다.
            System.out.println("Success!");
            return ResponseEntity.ok().body(true);
        } else {
            // DB에 저장하지 않는다.
            System.out.println("Fail!");
            return ResponseEntity.badRequest().body(false);
        }
    }

    @PostMapping("/evaluateFood")
    public ResponseEntity<FoodEvaluationDTO> evaluateMenu(@RequestBody FoodEvaluationDTO foodEvaluationDTO) {
        foodEvaluateService.save(foodEvaluationDTO);
        return ResponseEntity.ok().body(foodEvaluationDTO);
    }

    @GetMapping("/getMenuBoard")
    public void getMenuBoard(@RequestParam UUID userId) throws Exception {
        // 미구현
        return;
    }

    @GetMapping("/getMenuBoardSuggestion")
    public void getMenuBoardSuggestion() throws IOException {
        pythonService.generateMenuBoard();
        return;
    }
}
