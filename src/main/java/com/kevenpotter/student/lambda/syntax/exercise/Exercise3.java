package com.kevenpotter.student.lambda.syntax.exercise;

import com.kevenpotter.student.lambda.syntax.data.Person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeSet;

/**
 * @author KevenPotter
 * @company 2020:02:11 10:39:47
 * @date
 */
public class Exercise3 {

    /*
     * 综合案例3:集合的遍历
     */
    public static void main(String[] args) {
        ArrayList<Integer> integerArrayList = new ArrayList<Integer>();
        Collections.addAll(integerArrayList, 1, 2, 3, 4, 5, 6, 7, 8, 9, 0);
        // 将集合中的每一个元素都带入到accept方法中
        integerArrayList.forEach(System.out::println);
        // 输出集合中所有的偶数
        integerArrayList.forEach(ele -> {
            if (ele % 2 == 0) {
                System.out.println(ele);
            }
        });
    }
}
