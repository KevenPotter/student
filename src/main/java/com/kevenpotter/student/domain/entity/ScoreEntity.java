package com.kevenpotter.student.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    /*主键ID*/
    private Long id;
    /*学生编号*/
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long studentId;
    /*课程编号*/
    private Integer courseId;
    /*考试场次编号*/
    private Integer examinationCategoryId;
    /*课程成绩*/
    private String score;
    /*学期*/
    private Integer semester;
}
