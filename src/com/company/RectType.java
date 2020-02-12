package com.company;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class RectType extends Rectangle {

    private String name;
    private Color color;
    private String otherTreeData;

    public RectType(String name, Color color) {
        this.name = name;
        this.color = color;
        this.setArcHeight(15);
        this.setArcWidth(15);

    }

//    public void draw(Graphics g, int x, int y) {
//        g.setColor(Color.BLACK);
//        g.fillRect(x - 1, y, 3, 5);
//        g.setColor(color);
//        g.fillOval(x - 5, y - 10, 10, 10);
//    }


}
