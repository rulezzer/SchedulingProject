package com.company;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Process implements ProcessInterface{
    private int idProc;
    private static int lastAssignedId = 0;
    protected int arrivalTime;
    protected int burst;
    protected int completion;
    private int turnAround;
    private int waitingTime;
    private String type;
    private int startingTime;
    private float turnAroundNorm;
    private int contextSwitch;

    private List<Process> state;



    //costruttore
    public Process(){

        System.out.println("Process is made");

        this.arrivalTime = setArrivalTime();
        this.burst = setBurst();
        System.out.println("id prima " + idProc);
        idProc = lastAssignedId;
        lastAssignedId++;
        System.out.println("id dopo " + idProc);


    }



    public void setMememento(Memento m){
        state = m.getState();

    }
    public Memento createMemento(){
        return new Memento();
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

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getBurst(){
        return burst;
    }

    public void setBurst(Integer burst, List<Process> processList, int index) {
        processList.get(index).burst = burst;
    }
    public void setArrivalTime(Integer at, List<Process> processList, int index){
        processList.get(index).arrivalTime=at;


    }




    protected int CalculateCompletion(List<Process> processList, int index, int contextSwitch){

        if (index == 0){
            processList.get(index).completion = processList.get(index).arrivalTime + processList.get(index).burst;
        }else
        {
            if(processList.get(index).arrivalTime > processList.get(index-1).completion){
                processList.get(index).completion = processList.get(index).arrivalTime + processList.get(index).burst;
            }
            else{
                processList.get(index).completion = processList.get(index-1).completion + processList.get(index).burst + contextSwitch;
            }
        }
        return completion;

    }


    public int getStartingTime(List<Process> processList, int index, int contextSwitch){

        if(index == 0){
            processList.get(index).startingTime = processList.get(index).arrivalTime;
        }else {
            if(processList.get(index).arrivalTime > processList.get(index-1).completion)
            {
                if(processList.get(index-1).startingTime + contextSwitch > processList.get(index).arrivalTime)
                {
                    processList.get(index).startingTime = processList.get(index - 1).startingTime +
                            processList.get(index - 1).completion + contextSwitch;
                }else
                {
                    processList.get(index).startingTime = processList.get(index).arrivalTime;
                }

            }else
                processList.get(index).startingTime =  processList.get(index-1).completion + contextSwitch;
        }

        return startingTime;
    }


    //************* aggiunto da davide, domenica 9/2/20 verso le 16.30
    public int CalculateTurnaroundTime(List<Process> processList, int index){
        processList.get(index).turnAround = processList.get(index).completion - processList.get(index).arrivalTime;

        return turnAround;

    }


    //************* aggiunto da davide, domenica 9/2/20 verso le 16.30
    public int CalculateWaitingTime(List<Process> processList, int index){

        processList.get(index).waitingTime = processList.get(index).turnAround - processList.get(index).burst;

        return waitingTime;
    }

    public float calculateNormalizedTurnaround(List<Process> processList, int index){
        processList.get(index).turnAroundNorm = (float) processList.get(index).turnAround/processList.get(index).burst;
        return turnAroundNorm;
    }


    public void setIdProc(Integer idProc) {


        this.idProc = idProc;
    }

    public Integer getIdProc() {

        return idProc;
    }



}
