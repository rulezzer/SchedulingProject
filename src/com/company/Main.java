package com.company;

import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {


        try {

            ViewManager manager = new ViewManager();
            primaryStage = manager.getMainStage();
            primaryStage.setTitle("CSAS - CPU Scheduling Algorithm Simulation");
            primaryStage.show();

        } catch(Exception e) {

            e.printStackTrace();

        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
