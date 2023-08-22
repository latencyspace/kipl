# -*- coding: utf-8 -*-

import numpy as np
from tensorflow import keras

model = keras.models.load_model("./kipl-python/food_filter/food_filtering_model.h5")

by_gram = ["시금치", "수수", "미역", "요구르트", "양배추", "찹쌀", "전분", "콩나물", "단호박", "밀가루", "쌀가루", "파프리카", "청경채", "호두", "파", "들깨가루",
           "김", "중력분", "가지", "적채", "건포도", "흑ㅁ;", "밤", "멸치", "부추"]
by_exist = ["설탕", "참기름", "고추", "소금", "마늘", "다진마늘", "고춧가루", "양파", "달걀", "간장", "올리고당", "생강", "깨소금", "우유", "식초", "맛술",
            "매실청", "후추"]


def is_baby_food_ingredient(request_json):
    nutrients, ingredient_gram, ingredient_exist = data_transform(request_json)
    return np.round(model.predict([nutrients, ingredient_gram, ingredient_exist], verbose=0)) == 0


def find_indices(a, b):
    result_indices = []

    for value in b:
        if value in a:
            index = a.index(value)
            result_indices.append(index)
        else:
            result_indices.append(-1)

    return result_indices


def data_transform(data):
    ingredient_gram = []

    for idx in find_indices(list(data["ingredient_name"]), by_gram):
        if idx != -1:
            ingredient_gram.append(data["ingredient_gram"][idx])

        else:
            ingredient_gram.append(0)

    ingredient_exist = []

    for idx in find_indices(list(data["ingredient_name"]), by_exist):
        if idx != -1:
            ingredient_exist.append(1)

        else:
            ingredient_exist.append(0)

    return np.array([data["calorie"], data["carbohydrate"], data["protein"], data["fat"]]).reshape(1, -1), np.array(
        ingredient_gram).reshape(1, -1), np.array(ingredient_exist).reshape(1, -1)
