package com.kevenpotter.student.backup;

/**
 * @author KevenPotter
 * @company 2019:12:27 09:36:59
 * @date
 */
public abstract class Observer {
    protected Subject subject;
    public abstract void update();
}
