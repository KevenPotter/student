package com.kevenpotter.student.lambda;

/**
 * @author KevenPotter
 * @company 2020:02:10 16:56:48
 * @date
 */
public class Program {

    public static void main(String[] args) {
        // 1.使用接口实现类的方式
        Comparator comparator1 = new MyComparator();
        // 2.使用匿名内部类的方式
        Comparator comparator2 = new Comparator() {
            @Override
            public int compare(int a, int b) {
                return a - b;
            }
        };
        // 3.使用Lambda表达式的方式
        Comparator comparator3 = (a, b) -> a - b;
    }
}

class MyComparator implements Comparator {

    @Override
    public int compare(int a, int b) {
        return a - b;
    }
}

interface Comparator {
    int compare(int a, int b);
}