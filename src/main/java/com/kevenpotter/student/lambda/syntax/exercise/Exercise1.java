package com.kevenpotter.student.lambda.syntax.exercise;

import com.kevenpotter.student.lambda.syntax.data.Person;

import java.util.ArrayList;

/**
 * @author KevenPotter
 * @company 2020:02:11 10:39:47
 * @date
 */
public class Exercise1 {

    /*
     * 综合案例1:集合的排序
     * 已知,在一个ArrayList中有若干个Person对象.将这些Person对象按照年龄进行降序排序.
     * 拿list集合内带有的sort方法举例
     * 1.首先查看sort方法所需要的参数,通过观察得到"Comparator<? super E> c"
     * 2.确定得到的参数的类型,经过查看明确为:interface Comparator<T>,是一个接口
     * 3.观察得知接口的注解是为@FunctionalInterface,即函数式编程接口,表示该接口可以用于Lambda表达式实现
     * 4.向sort方法内进行传参,过去用的是匿名函数,而现在使用Lambda表达式
     * 5.其中所要实现的方法有两个参数,而所要实现的方法体仅有一个return语句,所以sort函数调用就可精简为:
     * personArrayList.sort((o1, o2) -> o2.age - o1.age);
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
        personArrayList.sort((o1, o2) -> o2.age - o1.age);
        System.out.println(personArrayList);
    }
}
