package com.kevenpotter.student.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 系部专业学期课程实体类
 *
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2020-02-05 23:12:47
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
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
