package com.kevenpotter.student.domain.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2019-11-23 11:13:59
 * @description 分数实体类
 */
@Data
public class ScoreEntity implements Serializable {

    /*课程编号*/
    private Long courseId;
    /*学生编号*/
    private Long studentId;
    /*考试场次编号*/
    private Long examinationCategoryId;
    /*课程成绩*/
    private String score;
}
