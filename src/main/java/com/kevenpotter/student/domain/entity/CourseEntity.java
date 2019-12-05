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

    /*课程编号*/
    private Long id;
    /*课程名称*/
    private String name;
    /*课时*/
    private Integer hour;
    /*学分*/
    private Integer credit;
}
