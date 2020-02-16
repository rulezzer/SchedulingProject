package com.company;

import java.util.ArrayList;
import java.util.List;

public class Observable {

    private int maxCS;
    private List<Observer> observers= new ArrayList<>();

    public void addObserver (Observer observer){
        observer.update(this.maxCS);
        this.observers.add(observer);

    }

    public void removeObserver(Observer observer){
        this.observers.remove(observer);
    }

    public Observable(){
        this.maxCS=0;
    }

    public void setMaxCS(int newMax){
        this.maxCS=newMax;
        for(Observer observer: this.observers){
            observer.update(this.maxCS);
        }
    }
}
