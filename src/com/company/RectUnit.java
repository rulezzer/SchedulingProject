package com.company;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class RectUnit extends Rectangle {

    private List<Rectangle> rects = new ArrayList<>();

    public Rectangle makeRect(int x, int y, String name, Color color) {
        RectType type = RectFactory.getRectType(name, color);
        Rectangle rect = new Rectangle(x, y);
        rects.add(rect);

        return rect;
    }
//
//    @Override
//    public void paint(Graphics graphics) {
//        for (Tree tree : rects) {
//            tree.draw(graphics);
//        }
//    }

}
