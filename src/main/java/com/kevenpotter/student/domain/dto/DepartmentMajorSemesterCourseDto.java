package com.kevenpotter.student.domain.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2020-02-06 20:59:15
 * @description 系部专业学期课程数据传输类
 */
@Data
@Accessors(chain = true)
public class DepartmentMajorSemesterCourseDto {

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
