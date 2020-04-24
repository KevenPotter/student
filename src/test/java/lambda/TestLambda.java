package lambda;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @author KevenPotter
 * @company 2020:02:10 15:44:00
 * @date
 */
public class TestLambda {

    /**
     * 原来的匿名内部类
     */
    @Test
    public void test1() {
        Comparator<Integer> com = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };
        TreeSet<Integer> treeSet = new TreeSet<Integer>(com);
    }

    /**
     * Lambda表达式
     */
    public void test2() {
        Comparator<Integer> com = (a, b) -> Integer.compare(a, b);
        TreeSet<Integer> treeSet = new TreeSet<Integer>(com);
    }

    /**
     * 需求:获取当前公司中员工年龄大于35的员工信息
     */
    @Test
    public void test3() {
        List<Employee> employees = Arrays.asList(
                new Employee("张三", 18, 9999.99),
                new Employee("李四", 38, 5555.55),
                new Employee("王五", 50, 6666.66),
                new Employee("赵六", 16, 3333.33),
                new Employee("田七", 8, 7777.77)
        );
        List<Employee> list = filterEmployees(employees);
        for (Employee employee : list) {
            System.out.println(employee);
        }
    }

    public List<Employee> filterEmployees(List<Employee> list) {
        List<Employee> emps = new ArrayList<>();
        for (Employee emp : list) {
            if (emp.getAge() >= 35) {
                emps.add(emp);
            }
        }
        return emps;
    }
}
