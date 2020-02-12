package com.company;

import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.Map;

public class RectFactory {

    static Map<String, RectType> rectTypes = new HashMap<>();

    public static RectType getRectType(String name, Color color) {
        RectType result = rectTypes.get(name);
        if (result == null) {
            result = new RectType(name, color);
            rectTypes.put(name, result);
        }
        return result;
    }

}
