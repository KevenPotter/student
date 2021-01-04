package com.kevenpotter.student.domain.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2019-11-23 12:50:16
 * @description 课程数据传输类
 */
@Data
@Accessors(chain = true)
public class CourseDto {

    /*主键ID*/
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
