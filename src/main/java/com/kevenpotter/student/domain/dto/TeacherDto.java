package com.kevenpotter.student.domain.dto;

import lombok.Data;

/**
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2019-11-22 13:33:44
 * @description 学生数据传输类
 */
@Data
public class TeacherDto {

    /*主键ID*/
    private Long id;
    /*教师编号*/
    private Long teacherId;
    /*教师所属系别*/
    private Integer departmentId;
    /*教师所属专业*/
    private Integer majorId;
    /*教师职称*/
    private String professional;
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
