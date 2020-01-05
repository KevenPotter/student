package com.kevenpotter.student.domain.dto;

import lombok.Data;

/**
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2020-01-04 00:14:43
 * @description 学生详情数据传输类
 */
@Data
public class StudentProfileDto {

    /*主键ID*/
    private Long id;
    /*学生编号*/
    private Long studentId;
    /*学生所属年级*/
    private Integer studentGrade;
    /*学生所属班级*/
    private Integer studentClazz;
    /*学生姓名*/
    private String studentName;
    /*学生年龄*/
    private Integer studentAge;
    /*学生性别*/
    private String studentSex;
    /*学生手机号码*/
    private Long studentMobile;
    /*学生家庭住址*/
    private String studentAddress;
    /*系别名称*/
    private String departmentName;
    /*专业名称*/
    private String majorName;
}
