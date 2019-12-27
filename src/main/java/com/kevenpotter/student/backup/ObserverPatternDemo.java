package com.kevenpotter.student.backup;

/**
 * @author KevenPotter
 * @company 2019:12:27 09:57:06
 * @date
 */
public class ObserverPatternDemo {

    public static void main(String[] args) {
        Subject subject = new Subject();
        new HexObserver(subject);
        new OctalObserver(subject);
        new BinaryObserver(subject);
        System.out.println("First state change:15");
        subject.setState(15);
        System.out.println("Second state change:10");
        subject.setState(10);
    }
}
