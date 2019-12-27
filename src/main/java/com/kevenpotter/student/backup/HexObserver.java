package com.kevenpotter.student.backup;

/**
 * @author KevenPotter
 * @company 2019:12:27 09:50:03
 * @date
 */
public class HexObserver extends Observer {

    public HexObserver(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println("Hex String: " + Integer.toHexString(subject.getState()).toUpperCase());
    }
}
