package com.kevenpotter.student.lambda.syntax;

import com.kevenpotter.student.lambda.interfaces.LambdaSingleReturnSingleParameter;

public class Syntax3 {

    public static void main(String[] args) {
        /*
         * 如果我们的程序需要需要很多相同的Lambda代码,如:
         * LambdaSingleReturnSingleParameter lambda1 = a -> a * 2;
         * LambdaSingleReturnSingleParameter lambda2 = a -> a * 2;
         * 这样就会出现一些问题:
         * 1.可能出现大量的重复代码和重复逻辑,当需求一旦变更就会发生大量修改代码的过程.
         * 所以我们可以声明一个私有方法用来封装我们的lambda内部的逻辑代码,如:
         * private static int change(int a) {
         *      return a * 2;
         * }
         * 2.方法引用的问题:
         * 方法引用:
         * 是指可以快速的将一个Lambda表达式的实现指向一个已经实现的方法.做一个对已经实现的方法的一个引用.
         * 基本概念:
         * 方法的隶属者(这里面的隶属者可以理解为是类的静态方法还是对象的普通方法,其中的含义即是是否含有static关键字)
         * 其语法就是:方法的隶属者::方法名
         * 注意的地方:
         * 1.参数数量和类型一定要和接口中所定义的方法一致;
         * 2.返回值的类型一定要和接口中定义的方法一致
         */
        LambdaSingleReturnSingleParameter lambda3 = Syntax3::change;
    }

    private static int change(int a) {
        return a * 2;
    }
}
