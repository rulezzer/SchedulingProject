package com.company;

import java.util.List;

public class ProcessIterator implements Iterator {

    List<Process> processList;
    private int position=0;

    public  ProcessIterator(List<Process> processList)
    {
        this.processList = processList;
    }

    public int getPosition(){  return this.position; }
    public Object next()
    {
        Process proc =  processList.get(position);
        position += 1;
        return proc;
    }

    public boolean hasNext()
    {
        if (position >= processList.size() ||
                processList.get(position) == null) {
            this.position=0;
            return false;
        }
        else
            return true;
    }
}
