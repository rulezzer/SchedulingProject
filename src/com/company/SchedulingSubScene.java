package com.company;

import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
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

import java.net.CookieHandler;
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
        super(new AnchorPane(), 900, 250);

        this.processList = processList;
        createGrid();

    }


    public GridPane createGrid() {




        int n_col = processList.size();
        System.out.println(n_col);

        grid = new GridPane();
        Rectangle rectProcess;



        root.getChildren().add(grid);



for(Process process: processList) {




    //richiamare ordimamemto per arrival

    for (int j = 0; j < processList.get(processList.indexOf(process)).CalculateCompletion(processList, processList.indexOf(process)); j++){
        rectProcess = new Rectangle(TILE_SIZE, TILE_SIZE);
    rectProcess.setStyle("-fx-fill: pink; "); //fai colori random
    rectProcess.setArcHeight(5);
    rectProcess.setArcWidth(5);
    GridPane.setConstraints(rectProcess, j, process.getIdProc());
    grid.getChildren().addAll(rectProcess);


    FadeTransition ft = new FadeTransition(Duration.millis(500), rectProcess);
    ft.setFromValue(0.25);
    ft.setToValue(1);
    ft.play();

    TranslateTransition openNav = new TranslateTransition(Duration.millis(500), rectProcess);
    openNav.fromXProperty().setValue(-10);
    openNav.setToX(0);

    openNav.play();
    TranslateTransition closeNav = new TranslateTransition(new Duration(500), rectProcess);

    closeNav.setToX(-(rectProcess.getWidth()));
}

    for (int j = processList.get(processList.indexOf(process)).getStartingTime(processList, processList.indexOf(process));
         j < processList.get(processList.indexOf(process)).CalculateCompletion(processList, processList.indexOf(process)); j++) {

        //System.out.println(i + "o: " + processList.get(processList.indexOf(k)).getStartingTime(processList, processList.indexOf(k)) +
                //" - " + processList.get(processList.indexOf(k)).CalculateCompletion(processList, processList.indexOf(k)));


        rectProcess = new Rectangle(TILE_SIZE, TILE_SIZE);
        rectProcess.setStyle("-fx-fill: #00AB84; "); //fai colori random
        rectProcess.setArcHeight(5);
        rectProcess.setArcWidth(5);
        GridPane.setConstraints(rectProcess,j, process.getIdProc());
        grid.getChildren().addAll(rectProcess);


        FadeTransition ft = new FadeTransition(Duration.millis(500), rectProcess);
        ft.setFromValue(0.25);
        ft.setToValue(1);
        ft.play();

        TranslateTransition openNav = new TranslateTransition(Duration.millis(500), rectProcess);
        openNav.fromXProperty().setValue(-10);
        openNav.setToX(0);

        openNav.play();
        TranslateTransition closeNav = new TranslateTransition(new Duration(1000), rectProcess);

        closeNav.setToX(-(rectProcess.getWidth()));


    }


    for(int i=0; i<processList.get(processList.size()-1).completion; i++){
        if (i%5==0) {
            Label timeline = new Label(String.valueOf(i));
            timeline.setAlignment(Pos.CENTER);
            GridPane.setConstraints(timeline, i, processList.size() + 1);


            FadeTransition ft = new FadeTransition(Duration.millis(1500), timeline);
            ft.setFromValue(0);
            ft.setToValue(1);
            ft.play();

            grid.getChildren().addAll(timeline);

        }
    }

            StackPane stack = new StackPane();
            Label processNumber = new Label("P " +(1));
            processNumber.setTextFill(Color.WHITE);
//            rectField = new Rectangle(50, TILE_SIZE);

            stack.getChildren().addAll( processNumber);

//            rectField.setStyle("-fx-fill: F96231; -fx-stroke: FFFFFF; -fx-stroke-width: 1;");




            GridPane.setConstraints(stack, 0, processList.indexOf(process));
            grid.getChildren().addAll(stack);

}
          /*  StackPane stack = new StackPane();
            Label processNumber = new Label("P " +(1));
            processNumber.setTextFill(Color.WHITE);
            rectField = new Rectangle(50, TILE_SIZE);

            stack.getChildren().addAll(rectField, processNumber);

            rectField.setStyle("-fx-fill: F96231; -fx-stroke: FFFFFF; -fx-stroke-width: 1;");




            GridPane.setConstraints(stack, 0, i);
            grid.getChildren().addAll(stack);
*/




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
