package com.company;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Fcfs implements SchedulingStrategy {
    @Override
    public void schedule(List<Process> processList) {


        //int temp;
       /* for (int i = 0; i < processList.size(); i++) {


            for (int j = 0; j < processList.size() - (i + 1); j++) {
                if (processList.get(j).arrivalTime > processList.get(j + 1).arrivalTime) {

                    temp = processList.get(j).arrivalTime;
                    processList.get(j).arrivalTime = processList.get(j + 1).arrivalTime;
                    processList.get(j + 1).arrivalTime = temp;

                    temp = processList.get(j).burst;
                    processList.get(j).burst = processList.get(j + 1).burst;
                    processList.get(j + 1).burst = temp;





                }
            }



        }*/

        var copiaaRRAY = processList;
       copiaaRRAY.sort(Comparator.comparing(Process::getArrivalTime));



        System.out.println("***** Stampa ordinata *****");
        for (int i = 0; i < copiaaRRAY.size() ; i++) {

            System.out.println(i + ") Arrival Time = " + copiaaRRAY.get(i).arrivalTime +
                    " - Burst Time = " +  copiaaRRAY.get(i).burst);
            System.out.println("Completion = "+ copiaaRRAY.get(i).CalculateCompletion(copiaaRRAY, i)
             + " - Starting = " + copiaaRRAY.get(i).getStartingTime(copiaaRRAY, i));
            System.out.println("Turnaround = " + copiaaRRAY.get(i).CalculateTurnaroundTime(copiaaRRAY, i) +
                        " - Waitin = " + copiaaRRAY.get(i).CalculateWaitingTime(copiaaRRAY, i));

        }


    }
}
