package com.kevenpotter.student.backup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author KevenPotter
 * @company 2019:12:27 09:33:26
 * @date
 */
public class Subject {

    private List<Observer> observers = new ArrayList<Observer>();
    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        notifyAllObservers();
    }

    public void attach(Observer observer) {
        observers.add(observer);
    }

    public void notifyAllObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
