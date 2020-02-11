package com.company;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Process implements ProcessInterface{
    private String id;
    protected Integer arrivalTime;
    protected Integer burst;
    protected Integer completion = 0;
    private Integer turnAround;
    private Integer waitingTime;
    private String type;
    private Integer startingTime;
    private Integer contextSwitch;



    //costruttore
    public Process(){

        System.out.println("Process is made");

        this.arrivalTime = setArrivalTime();
        this.burst = setBurst();
//        this.contextSwitch = 3;
    }

    private int setArrivalTime(){
        Random rand = new Random();
        arrivalTime = rand.nextInt(15);

        return arrivalTime;
    }

    private int setBurst(){
        Random rand = new Random();
        burst = rand.nextInt(15)+1;

        return burst;
    }


    @Override
    public ProcessInterface makeCopy() {

        this.arrivalTime = setArrivalTime();
        System.out.println("Process is being made");
        Process processObject = null;

        try {
            //Calls the ProcessInterface super class clone() and casts the results to Process
            processObject = (Process) super.clone();

        } catch (CloneNotSupportedException e) {

            e.printStackTrace();
        }
//        setArrivalTime();
        return processObject;
    }

    public String toString(){

        return "PROCESSOOOOO";
    }

    public Integer getArrivalTime() {
        return arrivalTime;
    }

    public Integer getBurst(){
        return burst;
    }

    public void setId(String id) {
        this.id = id;
    }



    protected Integer CalculateCompletion(List<Process> processList, int index){

        if (index == 0){
            processList.get(index).completion = processList.get(index).arrivalTime + processList.get(index).burst;
        }else
        {
            if(processList.get(index).arrivalTime > processList.get(index-1).completion){
                processList.get(index).completion = processList.get(index).arrivalTime + processList.get(index).burst;
            }
            else{
                processList.get(index).completion = processList.get(index-1).completion + processList.get(index).burst + processList.get(index-1).contextSwitch;
            }
        }
        return completion;

    }


    public Integer getStartingTime(List<Process> processList, int index){

        if(index == 0){
            processList.get(index).startingTime = processList.get(index).arrivalTime;
        }else
        {   if(processList.get(index).arrivalTime > processList.get(index-1).completion){
              if(processList.get(index-1).startingTime + processList.get(index-1).contextSwitch > processList.get(index).arrivalTime) {
                  processList.get(index).startingTime = processList.get(index - 1).startingTime + processList.get(index - 1).completion + processList.get(index - 1).contextSwitch;
              }else {
                  processList.get(index).startingTime = processList.get(index).arrivalTime;
              }

        }else
            processList.get(index).startingTime =  processList.get(index-1).completion + processList.get(index).contextSwitch;
        }

        return startingTime;
    }


    //************* aggiunto da davide, domenica 9/2/20 verso le 16.30
    public Integer CalculateTurnaroundTime(List<Process> processList, int index){
        processList.get(index).turnAround = processList.get(index).completion - processList.get(index).arrivalTime;

        return turnAround;

    }


    //************* aggiunto da davide, domenica 9/2/20 verso le 16.30
    public Integer CalculateWaitingTime(List<Process> processList, int index){

        processList.get(index).waitingTime = processList.get(index).turnAround - processList.get(index).burst;

        return waitingTime;
    }


    public void setContextSwitch(int contextSwitch) {
        this.contextSwitch = contextSwitch;
    }




}
