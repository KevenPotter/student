package com.kevenpotter.student.domain.dto;

import java.util.StringJoiner;

/**
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2019-11-24 18:26:24
 * @description 分数数据传输类
 */
public class ScoreDto {

    /*课程编号*/
    private Long courseId;
    /*学生编号*/
    private Long studentId;
    /*考试场次编号*/
    private Long examinationCategoryId;
    /*课程成绩*/
    private String score;

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getExaminationCategoryId() {
        return examinationCategoryId;
    }

    public void setExaminationCategoryId(Long examinationCategoryId) {
        this.examinationCategoryId = examinationCategoryId;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ScoreDto.class.getSimpleName() + "[", "]")
                .add("courseId=" + courseId)
                .add("studentId=" + studentId)
                .add("examinationCategoryId=" + examinationCategoryId)
                .add("score='" + score + "'")
                .toString();
    }
}
