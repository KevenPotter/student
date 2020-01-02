package com.kevenpotter.student.domain.dto;

import lombok.Data;

/**
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2019-11-24 18:26:24
 * @description 分数数据传输类
 */
@Data
public class ScoreDto {

    /*课程编号*/
    private Long courseId;
    /*学生编号*/
    private Long studentId;
    /*考试场次编号*/
    private Long examinationCategoryId;
    /*课程成绩*/
    private String score;
}
