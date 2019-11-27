package com.kevenpotter.student.domain.dto;

import lombok.Data;

/**
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2019-11-22 13:33:44
 * @description 学生数据传输类
 */
@Data
public class StudentDto {

    /*学生编号*/
    private Long id;
    /*学生所属系别*/
    private Integer departmentId;
    /*学生所属年级*/
    private Integer grade;
    /*学生所属班级*/
    private Integer clazz;
    /*学生姓名*/
    private String name;
    /*学生年龄*/
    private Integer age;
    /*学生性别*/
    private String sex;
    /*学生家庭住址*/
    private String address;
}
