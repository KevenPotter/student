package com.kevenpotter.student.domain.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2019-11-23 11:10:03
 * @description 课程实体类
 */
@Data
public class CourseEntity implements Serializable {

    /*自增ID*/
    private Long id;
    /*学科编号*/
    private Integer courseId;
    /*课程名称*/
    private String courseName;
    /*课时*/
    private Integer hour;
    /*学分*/
    private Integer credit;
}
