package com.kevenpotter.student.backup;

/**
 * @author KevenPotter
 * @company 2019:12:27 09:37:39
 * @date
 */
public class BinaryObserver extends Observer {

    public BinaryObserver(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println("Binary String: " + Integer.toBinaryString(subject.getState()));
    }
}
