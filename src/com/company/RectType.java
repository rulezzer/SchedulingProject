package com.company;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class RectType extends Rectangle {

    private String name;
    private String style;

    public RectType(String name, String style) {
        this.name = name;
        this.setArcHeight(50);
        this.setArcWidth(50);
        this.setStyle(style);
    }

//    public void draw(Graphics g, int x, int y) {
//        g.setColor(Color.BLACK);
//        g.fillRect(x - 1, y, 3, 5);
//        g.setColor(color);
//        g.fillOval(x - 5, y - 10, 10, 10);
//    }


}
