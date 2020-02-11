package com.company;

import java.util.ArrayList;
import java.util.List;

public class Memento implements MementoInterface {


    private List<Process> state;
    private List<Process> mem_state;


    public List<Process> getState() {
        return state;
    }

    public void setState(List<Process> state) {
        mem_state = state;
    }

    @Override
    public void restoreState() {

        state = mem_state;

    }
}
