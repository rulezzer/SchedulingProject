package com.company;

import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class SchedulingSubScene extends SubScene {

    //Scehduling scheme

    AnchorPane root = (AnchorPane) this.getRoot();

    private static final int BOARD_COLUMN = 20;
    private static final int BOARD_ROW = 20;
    private static final int TILE_SIZE = 20;

    private GridPane grid;

    private List<Process> processList ;

    public SchedulingSubScene(List processList) {
        super(new AnchorPane(), 800, 800);

        this.processList = processList;

        createGrid();

    }


    public GridPane createGrid() {



        Process process = new Process();

        int n_col = processList.size();
        System.out.println(n_col);

        grid = new GridPane();
        Rectangle rectField;


        root.getChildren().add(grid);


        for (int i = 0; i<processList.size(); i++) {

            for (int j=0; j<processList.get(i).CalculateCompletion(processList, i); j++){
                rectField = new Rectangle(50, TILE_SIZE);


                rectField.setStyle("-fx-fill: F96531; -fx-stroke: FFFFFF; -fx-stroke-width: 1;");


                GridPane.setConstraints(rectField, j, i);
                grid.getChildren().addAll(rectField);

            }

            StackPane stack = new StackPane();
            Label processNumber = new Label("P " +(i+1));
            processNumber.setTextFill(Color.WHITE);
            rectField = new Rectangle(50, TILE_SIZE);

            stack.getChildren().addAll(rectField, processNumber);

            rectField.setStyle("-fx-fill: F96231; -fx-stroke: FFFFFF; -fx-stroke-width: 1;");


            GridPane.setConstraints(stack, 0, i);
            grid.getChildren().addAll(stack);


        }
        return grid;

    }

}
