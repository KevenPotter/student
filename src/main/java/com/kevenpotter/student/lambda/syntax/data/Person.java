package com.kevenpotter.student.lambda.syntax.data;

/**
 * @author KevenPotter
 * @company 2020:02:11 10:02:03
 * @date
 */
public class Person {

    public String name;
    public int age;

    public Person() {
        System.out.println("Person类的无参构造方法执行了");
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
        System.out.println("Person类的有参构造方法执行了");
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
