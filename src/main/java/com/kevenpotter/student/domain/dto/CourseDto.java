package com.kevenpotter.student.domain.dto;

import java.util.StringJoiner;

/**
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2019-11-23 12:50:16
 * @description 课程数据传输类
 */
public class CourseDto {

    /*课程编号*/
    private Long id;
    /*课程名称*/
    private String name;
    /*课时*/
    private Integer hour;
    /*学分*/
    private Integer credit;

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

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", CourseDto.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("hour=" + hour)
                .add("credit=" + credit)
                .toString();
    }
}
