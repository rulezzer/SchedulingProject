package com.company;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ProcessCollections implements CollectionSet {

    private List<Process> processList= new ArrayList<Process>();;
    private int numOfProc=0;
    private ProcessFactory processMaker = new ProcessFactory();
    protected  Process proces;
    public ProcessCollections()
    {
        Process process = new Process();
        processList.add(process);
        numOfProc=1;
        addProc();

    }
    public int getSize(){
        return processList.size();
    }

    public int getProcIndex(Process proc){
        return processList.indexOf(proc);
    }

    public List<Process> getList(){
        return processList;
    }
    public int getCompletion(Process process, int contextSwitch){
        return processList.get(processList.indexOf(process)).CalculateCompletion(processList, processList.indexOf(process), contextSwitch);
    }

    public int getStartingTime(Process process, int contextSwitch){
        return processList.get(processList.indexOf(process)).getStartingTime(processList, processList.indexOf(process), contextSwitch);
    }

    public void sort(){
        processList.sort(Comparator.comparing(Process::getIdProc));
    }

    public void addProc()
    {
        //Prototype
        proces = new Process();

        // The first position (0) is for the "original", the clones follows
        Process clonedProcess = (Process) processMaker.getClone(proces);

        processList.add(clonedProcess);
        numOfProc++;

    }
    public void removeProc()
    {
        processList.remove(processList.size() - 1);
    }

    public Iterator createIterator(){
        return new ProcessIterator(processList);
    }
}
