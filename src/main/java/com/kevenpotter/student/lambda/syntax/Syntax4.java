package com.kevenpotter.student.lambda.syntax;

import com.kevenpotter.student.lambda.syntax.data.Person;

public class Syntax4 {

    public static void main(String[] args) {
        /*
         * 需求:我们需要定义一个接口来获取Person类的信息
         * 构造方法的引用
         * 基本语法:需要引用的类::new关键字
         * 其实仔细看这个语法,也能判断出这就是一个基本的方法引用语法,其中的方法隶属者为Person这个毋庸置疑,而所调用的构造函数的
         * 方法名其实使用new关键字取代了,意味着创建一个对象,而且是使用的是无参构造器创建.
         * 那么如何调用有参构造器呢?这个其实就和我们接口有关系了,接口中定义的是无参数的,那么调用的就是无参构造器.如果接口中定义
         * 的是有参数的,那么调用的就是有参构造器.
         * 基本语法:需要引用的类::new关键字
         * 其实我们不难发现,这里的语法是一样的,因为我们所用的语法都是所谓的语法引用语法,双冒号(::)后面的就是一个方法名,即是需要
         * 携带参数,也不会在方法名后面出现.
         */
        PersonCreator1 creator1 = () -> new Person();
        PersonCreator1 creator2 = Person::new;
        Person person1 = creator2.getPerson();
        PersonCreator2 creator3 = Person::new;
        Person person2 = creator3.getPerson("小明", 10);
    }
}

interface PersonCreator1 {
    Person getPerson();
}

interface PersonCreator2 {
    Person getPerson(String name, int age);
}
