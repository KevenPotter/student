package com.kevenpotter.student.domain.entity;

import java.io.Serializable;
import java.util.StringJoiner;

/**
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2019-11-23 11:10:03
 * @description 课程实体类
 */
public class CourseEntity implements Serializable {

    /*课程编号*/
    private Long id;
    /*课程名称*/
    private String name;
    /*课时*/
    private Integer hour;
    /*学分*/
    private Integer score;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", CourseEntity.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("hour=" + hour)
                .add("score=" + score)
                .toString();
    }
}
