package com.company;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Sjf implements SchedulingStrategy{

    @Override
    public void schedule(ProcessCollections processCollections, int contextSwitch) {


//        for (Process proc : processList) System.out.println("1 arr " +proc.getArrivalTime() + " bu " + proc.burst);
//
//        Collections.sort(processList, Comparator.comparing(Process::getArrivalTime).thenComparing(Process::getBurst));
//
//        for (Process proc : processList) System.out.println("2 arr " +proc.getArrivalTime() + " bu " + proc.burst);
//
//         List<Process> copiaaRRAY = processList;
//
//         int ars= copiaaRRAY.size();
//
//        for (int i = 1; i < ars-1; i++) {
//            for (int k=i+1; k < ars; k++){
//                if (copiaaRRAY.get(i).burst > copiaaRRAY.get(k).burst && copiaaRRAY.get(k).arrivalTime <= copiaaRRAY.get(i).arrivalTime+copiaaRRAY.get(i).burst) {
//
//                    Collections.swap(processList, i, k);
//                    System.out.println("scambio");
//                }
//        }
//        }
//        for (Process proc : processList) System.out.println("3 arr " +proc.getArrivalTime() + " bu " + proc.burst);

        List<Process> processList = processCollections.getList();
        Collections.sort(processList, Comparator.comparing(Process::getArrivalTime).thenComparing(Process::getBurst));

        final List<Process> copiaaRRAY = processList;
        for (int i = 0; i < copiaaRRAY.size()-1; i++){
            if (copiaaRRAY.get(i).burst > copiaaRRAY.get(i+1).burst && copiaaRRAY.get(i).arrivalTime > copiaaRRAY.get(i+1).arrivalTime){
                Collections.swap(processList, i, i+1);
            }
        }



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
