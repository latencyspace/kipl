# -*- coding: utf-8 -*-

import json
import sys

from is_baby_food_ingredient import is_baby_food_ingredient
from is_baby_food_name import is_baby_food_name

if __name__ == "__main__":
    try:
        foodDTO_json = json.loads(sys.argv[1])
        if 'food_name' in foodDTO_json:
            if is_baby_food_name(foodDTO_json["food_name"]):
                if is_baby_food_ingredient(foodDTO_json):
                    print(json.dumps({"result": True}))
                    sys.exit(0)

        print(json.dumps({"result": False}))
    except json.JSONDecodeError:
        sys.exit(1)
