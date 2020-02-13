package com.company;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Rect extends Rectangle {

    private int width;
    private int height;
    private RectType type;
    private Color color;

    public Rect(int width, int height, RectType type) {
        this.width = width;
        this.height = height;

        this.type = type;
    }

//    public void draw(Graphics g) {
//        type.draw(g, x, y);
//    }

}
