package com.company;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

//piece that we draw
public class RectUnit extends Rectangle {

    private List<Rectangle> rects = new ArrayList<>();

    public Rectangle makeRect(int x, int y, String name, String style) {
        RectType type = RectFactory.getRectType(name, style);
        Rectangle rect = new Rectangle(x, y);

        rect.setStyle(style);

        rects.add(rect);

        return rect;
    }


    public Rectangle makeRect(int x, int y, String name, String style, int arcW, int arcH) {
        RectType type = RectFactory.getRectType(name, style);
        Rectangle rect = new Rectangle(x, y);

        rect.setArcWidth(arcW);
        rect.setArcHeight(arcH);
        rect.setStyle(style);

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
