package com.kevenpotter.student.domain.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2019-11-22 13:33:44
 * @description 学生数据传输类
 */
@Data
@Accessors(chain = true)
public class StudentDto {

    /*主键ID*/
    private Long id;
    /*学生编号*/
    private String studentId;
    /*学生所属系别*/
    private Integer departmentId;
    /*学生所属专业*/
    private Integer majorId;
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
    /*学生手机号码*/
    private Long mobile;
    /*学生家庭住址*/
    private String address;
}
