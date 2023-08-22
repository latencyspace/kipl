# -*- coding: utf-8 -*-

import vertexai
from google.oauth2 import service_account
from vertexai.language_models import TextGenerationModel

credentials = service_account.Credentials.from_service_account_file("./kipl-python/food_filter/credential.json")

vertexai.init(project="jnu-idv-08", location="us-central1", credentials=credentials)
parameters = {
    "max_output_tokens": 256,
    "temperature": 0.2,
    "top_p": 0.8,
    "top_k": 1
}

model = TextGenerationModel.from_pretrained("text-bison")
prompt = open("./kipl-python/food_filter/prompt.txt", "r", encoding="utf-8").read()


def is_baby_food_name(food_name):
    return model.predict(prompt + "\nText : {}\nCategories : ".format(food_name), **parameters).text == "True"
