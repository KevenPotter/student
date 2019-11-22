package com.kevenpotter.student.domain.dto;

import java.util.StringJoiner;

/**
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2019-11-22 13:33:44
 * @description 学生数据传输类
 */
public class StudentDto {

    /*学生编号*/
    private Long id;
    /*学生性别*/
    private String sex;
    /*学生姓名*/
    private String name;
    /*学生年龄*/
    private Integer age;
    /*学生家庭住址*/
    private String address;
    /*学生所属系别*/
    private String departmentId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", StudentDto.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("sex='" + sex + "'")
                .add("name='" + name + "'")
                .add("age=" + age)
                .add("address='" + address + "'")
                .add("departmentId='" + departmentId + "'")
                .toString();
    }
}
