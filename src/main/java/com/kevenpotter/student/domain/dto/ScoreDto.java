package com.kevenpotter.student.domain.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2019-11-24 18:26:24
 * @description 分数数据传输类
 */
@Data
@Accessors(chain = true)
public class ScoreDto {

    /*主键ID*/
    private Long id;
    /*学生编号*/
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
