package com.kevenpotter.student.domain.entity;

import java.io.Serializable;
import java.util.StringJoiner;

/**
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2019-11-23 11:13:59
 * @description 分数实体类
 */
public class ScoreEntity implements Serializable {

    /*课程编号*/
    private Long courseId;
    /*学生编号*/
    private Long studentId;
    /*练习场次编号*/
    private Long practiceId;
    /*课程成绩*/
    private String score;

    @Override
    public String toString() {
        return new StringJoiner(", ", ScoreEntity.class.getSimpleName() + "[", "]")
                .add("courseId=" + courseId)
                .add("studentId=" + studentId)
                .add("practiceId=" + practiceId)
                .add("score='" + score + "'")
                .toString();
    }
}
