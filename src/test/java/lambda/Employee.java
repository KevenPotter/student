package lambda;

import lombok.Data;

/**
 * @author KevenPotter
 * @company 2020:02:10 15:53:36
 * @date
 */
@Data
public class Employee {

    private String name;
    private int age;
    private double salary;

    public Employee(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }
}
