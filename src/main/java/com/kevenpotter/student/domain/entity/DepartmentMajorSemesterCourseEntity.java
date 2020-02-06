package com.kevenpotter.student.domain.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2020-02-05 23:12:47
 * @description 系部专业学期课程实体类
 */
@Data
public class DepartmentMajorSemesterCourseEntity implements Serializable {

    /*自增ID*/
    private Long id;
    /*系别编号*/
    private Integer departmentId;
    /*专业编号*/
    private Integer majorId;
    /*学期*/
    private Integer semester;
    /*学科编号*/
    private Integer courseId;
}
