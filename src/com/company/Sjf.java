package com.company;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Sjf implements SchedulingStrategy{

    @Override
    public void schedule(List<Process> processList, int contextSwitch) {



        var copiaaRRAY = processList;

        copiaaRRAY.sort(Comparator.comparing(Process::getArrivalTime).thenComparing(Process::getBurst));


        System.out.println("***** Stampa ordinata SJF (Non preempitive) *****");
        for (int i = 0; i < copiaaRRAY.size() ; i++) {

            System.out.println(i + ") Arrival Time = " + copiaaRRAY.get(i).arrivalTime +
                    " - Burst Time = " +  copiaaRRAY.get(i).burst);
            System.out.println("Completion = "+ copiaaRRAY.get(i).CalculateCompletion(copiaaRRAY, i, contextSwitch)
                    + " - Starting = " + copiaaRRAY.get(i).getStartingTime(copiaaRRAY, i, contextSwitch));
            System.out.println("Turnaround = " + copiaaRRAY.get(i).CalculateTurnaroundTime(copiaaRRAY, i) +
                    " - Waitin = " + copiaaRRAY.get(i).CalculateWaitingTime(copiaaRRAY, i));

        }

    }

}
