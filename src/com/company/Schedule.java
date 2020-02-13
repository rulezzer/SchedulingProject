package com.company;

import javafx.scene.layout.GridPane;

import java.util.List;

public class Schedule {

    protected SchedulingStrategy schedulingStrategy;


    public Schedule() {

        this.schedulingStrategy = null;
    }

    public void setSchedulingStrategy(String strategy) {
        if(strategy == "FCFS"){
            this.schedulingStrategy = new Fcfs();
        }else if(strategy == "SJF"){
            this.schedulingStrategy = new Sjf();
        }
    }

    public void execute(List<Process> processList, int contextSwitch) {
        if (this.schedulingStrategy != null) {
          this.schedulingStrategy.schedule(processList,contextSwitch);
        }
    }


    public float averageWaitingTime(List<Process> processList) {

        float somma = 0;
        float media;
        for (int index = 0; index < processList.size(); index++)
        {
            somma = somma + processList.get(index).CalculateWaitingTime(processList, index);
        }
        media = somma/processList.size();
        return media;
    }

    public float averageTurnaroundTime(List<Process> processList) {
        float somma = 0;
        float media;
        for (int index = 0; index < processList.size(); index++)
        {
            somma = somma + processList.get(index).CalculateTurnaroundTime(processList, index);
        }
        media = somma/processList.size();
        return media;

    }

    public float averageTurnaroundNormalized(List<Process> processList) {
        float somma = 0;
        float media;
        for (int index = 0; index < processList.size(); index++)
        {
            somma = somma + processList.get(index).calculateNormalizedTurnaround(processList, index);
        }
        media = somma/processList.size();
        return media;

    }
}
