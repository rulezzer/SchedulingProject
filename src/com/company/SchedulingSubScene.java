package com.company;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.SubScene;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.*;

public class SchedulingSubScene extends SubScene {

    //Scehduling scheme

    AnchorPane root = (AnchorPane) this.getRoot();

    private static final int BOARD_COLUMN = 20;
    private static final int BOARD_ROW = 20;
    private static final int TILE_SIZE = 20;

    private GridPane grid;
    private GridPane processColumn;
    private GridPane timelineGrid;

    protected ProcessCollections processCollections;
    private Iterator procIterator;
    private List<Process> processList;
    private int contextSwitch;

    //flyweight init
    RectUnit rectUnit = new RectUnit();


    public SchedulingSubScene( ProcessCollections processCollections, int cs) {
        super(new AnchorPane(), 1000, 250);

        this.processCollections = processCollections;
        this.contextSwitch = cs;
        procIterator= processCollections.createIterator();

        createGrid();
        createProcessColumn();
        createTimeline();
    }


    public GridPane createProcessColumn() {
        processColumn = new GridPane();

        root.getChildren().add(processColumn);

//        root.setStyle("-fx-background-color: rgba(255, 255, 255, 0.5);");


        StackPane vuoto = new StackPane();
        Label timeLine = new Label("");
        timeLine.setTextFill(Color.WHITE);

        Rectangle vuotoRet = rectUnit.makeRect(50, TILE_SIZE, "ccc", "-fx-fill: transparent; -fx-stroke: FFFFFF; -fx-stroke-width: 1; -fx-stroke-type: inside; ");
        vuoto.getChildren().addAll(vuotoRet,timeLine);

        GridPane.setConstraints(vuoto,0,0);
        processColumn.getChildren().addAll(vuoto);

        // for (Process process : processList) {

        while(procIterator.hasNext()){
            Process proc=(Process) procIterator.next();
            Rectangle procNumber = rectUnit.makeRect(50, TILE_SIZE, "ccc", "-fx-fill: F96231; -fx-stroke: FFFFFF; -fx-stroke-width: 1; -fx-stroke-type: inside; ");
            StackPane stack = new StackPane();
            Label processNumber = new Label("P " + (procIterator.getPosition()));
            processNumber.setTextFill(Color.WHITE);
            stack.getChildren().addAll(procNumber, processNumber);
            GridPane.setConstraints(stack, 0, (proc.getIdProc()+1));
            processColumn.getChildren().addAll(stack);

        }


        return processColumn;
    }


    public GridPane createGrid() {

        grid = new GridPane();
        grid.setLayoutX(50);

        root.getChildren().add(grid);

        // for (Process process : processList) {

        while(procIterator.hasNext()){
            Process process=(Process) procIterator.next();
            //richiamare ordimamemto per arrival

            for (int j = 0; j <  processCollections.getCompletion(process, contextSwitch); j++) {

                //PINK process unit
                Rectangle pinkUnit = rectUnit.makeRect(TILE_SIZE, TILE_SIZE, "pU", "-fx-fill: transparent; -fx-stroke: grey; -fx-stroke-width: 0.1; -fx-stroke-type: inside;");

                GridPane.setConstraints(pinkUnit, j, process.getIdProc()+1);
                grid.getChildren().addAll(pinkUnit);


                FadeTransition ft = new FadeTransition(Duration.millis(500), pinkUnit);
                ft.setFromValue(0.25);
                ft.setToValue(1);
                ft.play();

                TranslateTransition openNav = new TranslateTransition(Duration.millis(500), pinkUnit);
                openNav.fromXProperty().setValue(-10);
                openNav.setToX(0);

                openNav.play();
                TranslateTransition closeNav = new TranslateTransition(new Duration(500), pinkUnit);

                closeNav.setToX(-(pinkUnit.getWidth()));
            }


            for(int j = processCollections.getCompletion(process, contextSwitch); j < processCollections.getCompletion(process, contextSwitch)+ contextSwitch; j++) {
                //CONTEXT SWITCH unit
                Rectangle contextSwitchUnit = rectUnit.makeRect(TILE_SIZE, TILE_SIZE, "csU", "-fx-background-fill: grey;", 5, 5);


                if (processCollections.getProcIndex(process) != processCollections.getSize()-1) {
                    GridPane.setConstraints(contextSwitchUnit, j, process.getIdProc()+1);
                    grid.getChildren().addAll(contextSwitchUnit);
                    contextSwitchUnit.setFill(new ImagePattern(new Image("com/company/resources/stripes.png")));

                    specialEffects(contextSwitchUnit);
                }
            }

            for (int j =processCollections.getStartingTime(process, contextSwitch); j < processCollections.getCompletion(process, contextSwitch); j++) {

                //GREEN process unit
                Rectangle processUnit = rectUnit.makeRect(TILE_SIZE, TILE_SIZE, "pU", "-fx-fill: #00AB84;", 5, 5);

                GridPane.setConstraints(processUnit, j, process.getIdProc()+1);
                grid.getChildren().add(processUnit);


                specialEffects(processUnit);


            }

        }

        processCollections.sort();
        return grid;

    }

    private void specialEffects(Rectangle processUnit) {
        FadeTransition ft = new FadeTransition(Duration.millis(500), processUnit);
        ft.setFromValue(0.25);
        ft.setToValue(1);
        ft.play();

        TranslateTransition openNav = new TranslateTransition(Duration.millis(500), processUnit);
        openNav.fromXProperty().setValue(-10);
        openNav.setToX(0);
        openNav.play();
        TranslateTransition closeNav = new TranslateTransition(new Duration(1000), processUnit);
        closeNav.setToX(-(processUnit.getWidth()));
    }

    public GridPane createTimeline() {

        timelineGrid = new GridPane();

        //  root.getChildren().add(timelineGrid);
        Observable completionChange = new Observable();
        Observer observer = new ObserverCompletion(grid);
        completionChange.addObserver(observer);

        int maxcompl = 0;
        while(procIterator.hasNext()) {
            Process proc = (Process) procIterator.next();
            System.out.println("TIMRLINR"+proc.completion);
            //  for (Process proc : processList) {
            if (maxcompl < proc.completion){
                maxcompl=proc.completion;
                completionChange.setMaxCS(proc.completion);}
        }

        return timelineGrid;
    }
}
