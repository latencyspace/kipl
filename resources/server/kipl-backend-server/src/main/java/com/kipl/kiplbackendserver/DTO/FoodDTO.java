package com.kipl.kiplbackendserver.DTO;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.cloud.bigquery.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@NoArgsConstructor
@Setter
@Getter
@JsonSerialize
public class FoodDTO {
    private String food_name;
    private List<String> ingredient_name;
    private List<Double> ingredient_gram;
    private Float calorie;
    private Float carbohydrate;
    private Float protein;
    private Float fat;

    public static List<FoodDTO> fromTableResult(TableResult tableResult) {
        List<FoodDTO> result = new LinkedList<>();
        Schema schema = tableResult.getSchema();
        for (FieldValueList row : tableResult.iterateAll()) {
            FoodDTO foodDTO = new FoodDTO();
            for (Field field : schema.getFields()) {
                String fieldName = field.getName();
                FieldValue fieldValue = row.get(fieldName);

                switch (fieldName) {
                    case "food_name":
                        foodDTO.setFood_name(fieldValue.getStringValue());
                        break;
                    case "ingredient_name":
                        List<String> ingredientNames = new ArrayList<>();
                        fieldValue.getRepeatedValue().forEach(value -> ingredientNames.add(value.getStringValue()));
                        foodDTO.setIngredient_name(ingredientNames);
                        break;

                    case "ingredient_gram":
                        List<Double> ingredientGrams = new ArrayList<>();
                        fieldValue.getRepeatedValue().forEach(value -> ingredientGrams.add(value.getDoubleValue()));
                        foodDTO.setIngredient_gram(ingredientGrams);
                        break;

                    case "calorie":
                        foodDTO.setCalorie((float) fieldValue.getDoubleValue());
                        break;
                    case "carbohydrate":
                        foodDTO.setCarbohydrate((float) fieldValue.getDoubleValue());
                        break;
                    case "protein":
                        foodDTO.setProtein((float) fieldValue.getDoubleValue());
                        break;
                    case "fat":
                        foodDTO.setFat((float) fieldValue.getDoubleValue());
                        break;
                }
            }
            result.add(foodDTO);
        }
        return result;
    }
}
