package com.kevenpotter.student.lambda.syntax.exercise;

public class Exercise5 {

    /*
     * 综合案例5:线程的实例化
     * 开辟一条线程,做一个数字的输出
     */
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println(i);
            }
        });
        thread.start();
    }
}
