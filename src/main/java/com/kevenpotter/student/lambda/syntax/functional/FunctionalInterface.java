package com.kevenpotter.student.lambda.syntax.functional;

public class FunctionalInterface {

    public static void main(String[] args) {
        /*
         * 系统内置的一些函数式接口
         * 1.Predicate<T>               参数:T         返回值:boolean
         *      IntPredicate            int     ->     boolean
         *      LongPredicate           long    ->     boolean
         *      DoublePredicate         double  ->     boolean
         *
         * 2.Consumer<T>                参数:T         返回值:void
         *      IntConsumer             int     ->     void
         *      LongConsumer            long    ->     void
         *      DoubleConsumer          double  ->     void
         *
         * 3.Function<T, R>             参数:T         返回值:R
         *      IntFunction<R>          int     ->     R
         *      LongFunction<R>         long    ->     R
         *      DoubleFunction<R>       double  ->     R
         *      IntToLongFunction       int     ->     long
         *      IntToDoubleFunction     int     ->     double
         *      LongToIntFunction       long    ->     int
         *      LongToDoubleFunction    long    ->     double
         *      DoubleToIntFunction     double  ->     int
         *      DoubleToLongFunction    double  ->     long
         *
         * 4.Supplier<T>                参数:无        返回值:T
         * 5.UnaryOperator<T>           参数:T         返回值:T
         * 6.BiFunction<T, U, R>        参数:T、U      返回值:R
         * 7.BinaryOperator<T>          参数:T、T      返回值:T
         * 8.BiPredicate<T, U>          参数:T、U      返回值:boolean
         * 9.BiConsumer<T, U>           参数:T、U      返回值:void
         * 其中常用的有Predicate<T>、Consumer<T>、Function<T, R>、Supplier<T>
         */
    }
}
