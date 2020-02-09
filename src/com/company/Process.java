package com.company;


import java.util.List;
import java.util.Random;

public class Process implements ProcessInterface{
    private String id;
    protected Integer arrivalTime;
    protected Integer burst;
    private Integer completion =0;
    private float turnAround;
    protected String type;


    //costruttore
    public Process(){

        System.out.println("Process is made");

        this.arrivalTime = setArrivalTime();
        this.burst = setBurst();
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

        System.out.println("");
        if (index == 0){
            processList.get(index).completion = processList.get(index).arrivalTime + processList.get(index).burst;
        }else
        {
            if(processList.get(index).arrivalTime > processList.get(index-1).completion){
                processList.get(index).completion = processList.get(index).arrivalTime + processList.get(index).burst;
            }
            else{
                processList.get(index).completion = processList.get(index-1).completion + processList.get(index).burst;
            }
        }
        return completion;

    }


}
