package com.company;

import javafx.scene.Parent;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

public class SchedulingSubScene extends SubScene {

    //Scehduling scheme

    AnchorPane root3 = (AnchorPane) this.getRoot();

    private static final int BOARD_COLUMN = 20;
    private static final int BOARD_ROW = 20;
    private static final int TILE_SIZE = 40;

    private GridPane grid;


    public SchedulingSubScene() {
        super(new AnchorPane(), 800, 800);

        createGrid();

    }


    public GridPane createGrid() {


        grid = new GridPane();
        Rectangle rectField;

        Button button1 = new Button("Button 1");

        grid.add(button1, 0, 0, 1, 1);

        root3.getChildren().add(grid);



        rectField = new Rectangle(TILE_SIZE, TILE_SIZE);
//                rectField.setFill(new ImagePattern(img));
        rectField.setStyle("-fx-background-color: Black");



        GridPane.setConstraints(rectField, 0, 0);
        grid.getChildren().addAll(rectField);

        return grid;

    }

}
