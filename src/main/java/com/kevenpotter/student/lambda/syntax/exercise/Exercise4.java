package com.kevenpotter.student.lambda.syntax.exercise;

import com.kevenpotter.student.lambda.syntax.data.Person;

import java.util.ArrayList;

public class Exercise4 {

    /*
     * 综合案例4:删除集合中满足条件的元素
     * 已知,在一个ArrayList中有若干个Person对象.删除集合中年龄>10岁的元素.
     */
    public static void main(String[] args) {
        ArrayList<Person> personArrayList = new ArrayList<Person>();
        personArrayList.add(new Person("john", 10));
        personArrayList.add(new Person("keven", 11));
        personArrayList.add(new Person("blue", 12));
        personArrayList.add(new Person("lily", 9));
        personArrayList.add(new Person("lucy", 9));
        personArrayList.add(new Person("polly", 3));
        personArrayList.add(new Person("wang", 40));
        personArrayList.removeIf(ele -> ele.age > 10);
        System.out.println(personArrayList);
    }
}
