package com.kevenpotter.student.backup;

/**
 * @author KevenPotter
 * @company 2019:12:27 09:48:12
 * @date
 */
public class OctalObserver extends Observer {

    public OctalObserver(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println("Octal String: " + Integer.toOctalString(subject.getState()));
    }
}
