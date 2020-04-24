package com.kevenpotter.student.lambda.syntax;

import com.kevenpotter.student.lambda.interfaces.*;

/**
 * @author KevenPotter
 * @company 2020:02:10 19:45:44
 * @date
 */
public class syntax1 {

    public static void main(String[] args) {
        /*
         * 1.Lambda表达式的基础语法
         * 首先Lambda表达式是一个匿名函数,对于匿名函数我们需要关注的就是
         * 返回值类型、方法名、参数列表、方法体
         * 因为,其中Lambda表达式是匿名函数,所以其中的的方法名可以忽略不计
         * 而且,在Lambda表达式中,返回值类型也不需要显式的写出来,所以返回值类型也可以忽略不计
         * 至此,对于Lambda表达式,我们所关注的,就是我们现在剩下的
         * 参数列表、方法体
         *
         * 基础语法:
         * (): 小括号,用来描述参数列表
         * {}: 大括号,用来描述方法体
         * ->: Lambda运算符goes to,用来分割()和{},也就是说分割参数列表和方法体
         */

        // 无返回值无参数的接口实现
        LambdaNoneReturnNoneParameter lambda1 = () -> {
            System.out.println("Hello World");
        };
        lambda1.test();

        // 无返回值有参数的接口实现
        LambdaNoneReturnSingleParameter lambda2 = (int a) -> {
            System.out.println(a);
        };

        lambda2.test(10);
        // 无返回值多参数的接口实现
        LambdaNoneReturnMultipleParameter lambda3 = (int a, int b) -> {
            System.out.println(a + b);
        };
        lambda3.test(10, 20);

        // 有返回值无参数的接口实现
        LambdaSingleReturnNoneParameter lambda4 = () -> {
            System.out.println("=lambda4 Running=");
            return 100;
        };
        int ret = lambda4.test();
        System.out.println(ret);

        // 有返回值有参数的接口实现
        LambdaSingleReturnSingleParameter lambda5 = (int a) -> {
            System.out.println("=lambda5 Running=");
            return a * 2;
        };
        int ret2 = lambda5.test(10);
        System.out.println(ret2);

        // 无返回值多参数的接口实现
        LambdaSingleReturnMultipleParameter lambda6 = (int a, int b) -> {
            System.out.println("=lambda6 Running=");
            return a + b;
        };
        int ret3 = lambda6.test(20, 30);
        System.out.println(ret3);
    }
}
