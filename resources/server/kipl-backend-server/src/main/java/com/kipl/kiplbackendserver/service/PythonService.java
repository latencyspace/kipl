package com.kipl.kiplbackendserver.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kipl.kiplbackendserver.DTO.FoodDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Service
public class PythonService {

    private final BigqueryService bigqueryService;

    @Autowired
    public PythonService(
            BigqueryService bigqueryService
    ) {
        this.bigqueryService = bigqueryService;
    }

    private BufferedReader executePython(String pythonFile, String argument) {
        try {
            ProcessBuilder pb = new ProcessBuilder("python3", pythonFile, argument);
            // ProcessBuilder pb = new ProcessBuilder("./venv/bin/python", pythonFile, argument);
            Process process = pb.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            process.waitFor();

            return reader;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Boolean isBabyFood(FoodDTO foodDTO) throws Exception {
        if (isEnglish(foodDTO.getFood_name())) {
            if (hasSameFoodName(foodDTO.getFood_name())) {
                BufferedReader reader = executePython(
                        "./kipl-python/food_filter/main.py",
                        new ObjectMapper().writeValueAsString(foodDTO).toString()
                );

                Map<String, Boolean> result = new ObjectMapper().readValue(reader.readLine(), Map.class);
                return result.get("result");
            }
        }
        return false;
    }

    private Boolean isEnglish(String foodName) {
        return !Pattern.compile(".*[a-zA-Z].*").matcher(foodName).matches();
    }

    private Boolean hasSameFoodName(String foodName) throws Exception {
        List<String> foodNames = bigqueryService.getFoodNames();
        boolean flag = true;
        for (String existFoodName : foodNames) {
            if (foodName.equals(existFoodName)) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    public void generateMenuBoard() throws IOException {
        BufferedReader reader = executePython("./kipl-python/menuboard_generator/main.py", "");
    }
}
