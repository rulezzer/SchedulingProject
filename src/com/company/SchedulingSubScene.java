package com.company;

import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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
        Rectangle rectProcess;


        root.getChildren().add(grid);

        Rectangle rectField2;

        for (int i = 0; i<processList.size(); i++) {


            this.processList.sort((o1, o2) -> o1.getArrivalTime().compareTo(o2.getArrivalTime()));

            System.out.println("burst"+ processList.get(i).burst);

            System.out.println("COMPLETION "+ processList.get(i).CalculateCompletion(processList, i));

            // peché la prima colonna è occupata da PRocess Number
            for (int j=1; j<processList.get(i).CalculateCompletion(processList, i); j++){


                for (Process proc : processList){
                    System.out.println(proc.arrivalTime + "indice  " + processList.indexOf(proc));
//                    if (proc.arrivalTime<proc.arrivalTime+1){
//
//                    }
                }

                if( processList.get(i).getStartingTime(processList, i)>=processList.get(i).arrivalTime) {
                    rectProcess = new Rectangle(TILE_SIZE, TILE_SIZE);

                    rectProcess.setStyle("-fx-fill: #00AB84; "); //fai colori random
                    rectProcess.setArcHeight(5);
                    rectProcess.setArcWidth(5);
                    GridPane.setConstraints(rectProcess, j, i);
                    grid.getChildren().addAll(rectProcess);




                    FadeTransition ft = new FadeTransition(Duration.millis(500), rectProcess);
                    ft.setFromValue(0.25);
                    ft.setToValue(1);
                    ft.play();

                    TranslateTransition openNav=new TranslateTransition(Duration.millis(500), rectProcess);
                    openNav.fromXProperty().setValue(-10);
                    openNav.setToX(0);

                    openNav.play();
                    TranslateTransition closeNav=new TranslateTransition(new Duration(500), rectProcess);

                    closeNav.setToX(-(rectProcess.getWidth()));


                } else {
                    rectProcess = new Rectangle(TILE_SIZE, TILE_SIZE);

                    rectProcess.setStyle("-fx-fill: pink; "); //fai colori random
                    rectProcess.setArcHeight(5);
                    rectProcess.setArcWidth(5);
                    GridPane.setConstraints(rectProcess, j, i);
                    grid.getChildren().addAll(rectProcess);

                }
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

//        System.out.println("gg " + processList.size());
//        System.out.println();
//        System.out.println("ff " + processList.get(processList.size()).CalculateCompletion(processList, processList.size()));
//
//        for (int i=0; i<processList.get(processList.size()).CalculateCompletion(processList, i);i++) {
//            Label xAxis = new Label(String.valueOf(processList.get(i).CalculateCompletion(processList, i)));
//            GridPane.setConstraints(xAxis, i, 0);
//
//            grid.getChildren().add(xAxis);
//
//        }

            return grid;

    }

}
