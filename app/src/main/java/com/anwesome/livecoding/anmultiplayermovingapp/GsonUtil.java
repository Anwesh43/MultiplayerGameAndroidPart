package com.anwesome.livecoding.anmultiplayermovingapp;

import com.anwesome.ui.gameviewmodul.GameObject;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Created by anweshmishra on 03/01/17.
 */
public class GsonUtil {
    private static Gson gson = new Gson();
    private static TypeToken<GameObject> gameObjectTypeToken = new TypeToken<GameObject>(){{

    }};
    public static String gameObjectToJson(GameObject gameObject) {
        return gson.toJson(gameObject,gameObjectTypeToken.getType());
    }
    public static GameObject gameObjectFromJson(String gameObjectJson) {
        return gson.fromJson(gameObjectJson,gameObjectTypeToken.getType());
    }
}
