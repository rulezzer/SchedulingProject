package com.company;

import java.util.List;

public interface SchedulingStrategy {
    public void schedule(ProcessCollections processCollections, int contextSwitch);

}
