package com.kevenpotter.student.lambda.syntax.closure;

import java.util.function.Consumer;

/**
 * @author KevenPotter
 * @company 2020:02:11 15:49:09
 * @date
 */
public class ClosureDemo2 {

    /*
     * 闭包注意问题:
     * 在Lambda表达式中如果我们想要引用某一个局部变量,那么这个局部变量必须是一个常量
     */
    public static void main(String[] args) {
        int a = 10;
        Consumer<Integer> c = ele -> {
            System.out.println(a + 1);
        };
        c.accept(1);
    }
}
