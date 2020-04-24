package com.kevenpotter.student.lambda.syntax.closure;

import java.util.function.Supplier;

/**
 * @author KevenPotter
 * @company 2020:02:11 15:49:09
 * @date
 */
public class ClosureDemo {

    /*
     * 闭包问题:
     * 举例:我们在getNumber()方法中使用了Lambda表达式,用来返回在getNumber()中定义的局部变量,因为我们所说的Lambda本身就是一个
     * 匿名方法,而这个匿名方法引用了一个getNumber()方法中定义的局部变量num,那么我们就将其称之为闭包.也就是说将num包起来.
     * int num = 10;
     * return () -> {
     *      return num;
     * };
     * 思考:根据main函数所打印出来的结果,我们获取了一个为10的数字,从表面现象上看,这个结果是毋庸置疑的,因为从头到尾就这一个变量
     * 出现了,那么为什么会这样.我们一般说,方法中的局部变量在方法执行结束后就应该被销毁掉,可是为什么这个num没有被销货掉呢?
     * 答案:闭包,其实是会提升所包围变量的生命周期.内部的num在getNumber()方法结束后并不会销毁.
     */
    public static void main(String[] args) {
        int n = getNumber().get();
        System.out.println(n);
    }

    private static Supplier<Integer> getNumber() {
        int num = 10;
        return () -> {
            return num;
        };
    }
}
