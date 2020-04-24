package com.kevenpotter.student.lambda.syntax.exercise;

import com.kevenpotter.student.lambda.syntax.data.Person;

import java.util.TreeSet;

/**
 * @author KevenPotter
 * @company 2020:02:11 10:39:47
 * @date
 */
public class Exercise2 {

    /*
     * 综合案例2:TreeSet的排序
     * 在这里TreeSet传入的比较器进行了一个简单的实现,因为对于TreeSet这个结构,如果单纯的使用o2-o1这种语句,一旦计算出0,那么
     * TreeSet就会出现去重操作,这样就导致了部分数据丢失的情况,所以我们需要在方法体内进行一个简单的实现,让其不会出现计算为0的情
     * 况.
     */
    public static void main(String[] args) {
        TreeSet<Person> personTreeSet = new TreeSet<Person>((o1, o2) -> {
            if (o2.age <= o1.age) return -1;
            else return 1;
        });
        personTreeSet.add(new Person("john", 10));
        personTreeSet.add(new Person("keven", 11));
        personTreeSet.add(new Person("blue", 12));
        personTreeSet.add(new Person("lily", 9));
        personTreeSet.add(new Person("lucy", 9));
        personTreeSet.add(new Person("polly", 3));
        personTreeSet.add(new Person("wang", 40));
        System.out.println(personTreeSet);
    }
}
