package com.kevenpotter.student.domain.dto;

import lombok.Data;

/**
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2020-01-06 13:28:10
 * @description 分数详情数据传输类
 */
@Data
public class ScoreProfileDto {

    /*学生编号*/
    private Long studentId;
    /*课程编号*/
    private Integer courseId;
    /*课程名称*/
    private String courseName;
    /*课时*/
    private Integer courseHour;
    /*学分*/
    private Integer courseCredit;
    /*课程成绩*/
    private Integer courseScore;
}
