package com.kevenpotter.student.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 课程实体类
 *
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2019-11-23 11:10:03
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
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
