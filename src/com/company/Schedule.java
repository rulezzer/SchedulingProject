package com.company;

import javafx.scene.layout.GridPane;

import java.util.List;

public class Schedule {

    protected SchedulingStrategy schedulingStrategy;
    GridPane gridpane = new GridPane();


    public Schedule() {

        this.schedulingStrategy = null;
    }

    public void setSortStrategy(SchedulingStrategy schedulingStrategy) {
        this.schedulingStrategy = schedulingStrategy;
    }

    public void sort() {
        if (this.schedulingStrategy != null) {
          //  this.schedulingStrategy.schedule();
        }
    }



    public void gridMake(){

    }
}
