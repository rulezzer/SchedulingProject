package com.company;

import java.util.List;

public class Fcfs implements SchedulingStrategy {
    @Override
    public void schedule(List<Process> processList) {


        int temp;
        for (int i = 0; i < processList.size(); i++) {


            for (int j = 0; j < processList.size() - (i + 1); j++) {
                if (processList.get(j).arrivalTime > processList.get(j + 1).arrivalTime) {

                    temp = processList.get(j).arrivalTime;
                    processList.get(j).arrivalTime = processList.get(j + 1).getArrivalTime();
                    processList.get(j + 1).arrivalTime = temp;

                    temp = processList.get(j).burst;
                    processList.get(j).burst = processList.get(j + 1).burst;
                    processList.get(j + 1).burst = temp;


                }
            }



        }

        System.out.println("***** Stampa ordinata *****");
        for (int i = 0; i < processList.size() ; i++) {

            System.out.println(i + ") Arrival Time = " + processList.get(i).arrivalTime +
                    " - Burst Time = " +  processList.get(i).burst);
            System.out.println("Completion = "+ processList.get(i).CalculateCompletion(processList, i)
             + " - Starting = " + processList.get(i).getStartingTime(processList, i));
            System.out.println("Turnaround = " + processList.get(i).CalculateTurnaroundTime(processList, i) +
                        " - Waitin = " + processList.get(i).CalculateWaitingTime(processList, i));

        }

    }
}
