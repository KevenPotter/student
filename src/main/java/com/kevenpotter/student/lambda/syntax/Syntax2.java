package com.kevenpotter.student.lambda.syntax;

import com.kevenpotter.student.lambda.interfaces.LambdaNoneReturnMultipleParameter;
import com.kevenpotter.student.lambda.interfaces.LambdaNoneReturnSingleParameter;
import com.kevenpotter.student.lambda.interfaces.LambdaSingleReturnMultipleParameter;
import com.kevenpotter.student.lambda.interfaces.LambdaSingleReturnNoneParameter;

/**
 * @author KevenPotter
 * @company 2020:02:10 22:29:02
 * @date
 */
public class Syntax2 {

    public static void main(String[] args) {

        /*
         * 语法精简:
         * 1.参数:
         * 由于在接口的抽象方法中已经定义了参数的数量和类型,所以在Lambda表达式中,参数的类型可以省略
         * 但是,如果需要省略类型,则每一个参数的类型都要省略,千万不要出现省略一个参数,不省略一个参数
         */
        LambdaNoneReturnMultipleParameter lambda1 = (a, b) -> {
            System.out.println("Hello World");
        };

        /*
         * 2.参数小括号():
         * 如果说,参数列表中,参数的数量只有一个.那么此时,小括号()可以省略
         */
        LambdaNoneReturnSingleParameter lambda2 = a -> {
            System.out.println("Hello World");
        };

        /*
         * 3.方法体大括号{}:
         * 如果说方法体中只有一条语句,那么此时,大括号{}可以省略
         */
        LambdaNoneReturnSingleParameter lambda3 = a -> System.out.println("Hello World");

        /*
         * 4.方法体大括号{}的补充:
         * 如果说方法体中唯一的一条语句是一个返回语句,则在省略掉大括号{}的同时,也必须省略掉return
         */
        LambdaSingleReturnNoneParameter lambda4 = () -> 10;
        LambdaSingleReturnMultipleParameter lambda5 = (a, b) -> a + b;
    }
}
