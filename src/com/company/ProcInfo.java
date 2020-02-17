package com.company;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ProcInfo {

    private SimpleIntegerProperty ID;
    private SimpleStringProperty arrivalTime;
    private SimpleStringProperty burst;
    private SimpleIntegerProperty contextSwitch;

    public ProcInfo(Integer ID, String arrivalTime, String burst) {
        this.ID = new SimpleIntegerProperty(ID);
        this.arrivalTime = new SimpleStringProperty(arrivalTime);
        this.burst = new SimpleStringProperty(burst);
//        this.contextSwitch = new SimpleIntegerProperty(contextSwitch);
    }

    public Integer getID(){
        return ID.get();
    }

    public String getArrivalTime(){
        return arrivalTime.get();
    }

    public String getBurst(){
        return burst.get();
    }

    public Integer getContextSwitch(){
        return contextSwitch.get();
    }


}
