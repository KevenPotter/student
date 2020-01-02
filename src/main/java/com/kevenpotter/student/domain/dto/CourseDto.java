package com.kevenpotter.student.domain.dto;

import lombok.Data;

/**
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2019-11-23 12:50:16
 * @description 课程数据传输类
 */
@Data
public class CourseDto {

    /*课程编号*/
    private Long id;
    /*课程名称*/
    private String name;
    /*课时*/
    private Integer hour;
    /*学分*/
    private Integer credit;
}
