package com.kevenpotter.student.domain.dto;

import lombok.Data;

/**
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2020-01-14 15:47:41
 * @description 教师详情数据传输类
 */
@Data
public class TeacherProfileDto {

    /*主键ID*/
    private Long id;
    /*教师编号*/
    private String teacherId;
    /*教师姓名*/
    private String teacherName;
    /*教师职称*/
    private String professional;
    /*教师年龄*/
    private Integer teacherAge;
    /*教师性别*/
    private String teacherSex;
    /*教师手机号码*/
    private Long teacherMobile;
    /*教师家庭住址*/
    private String teacherAddress;
    /*系别名称*/
    private String departmentName;
    /*专业名称*/
    private String majorName;
}
