package com.kevenpotter.student.domain.dto;

import java.math.BigDecimal;
import java.util.StringJoiner;

/**
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2019-11-23 18:54:39
 * @description 考试类目数据传输类
 */
public class ExaminationCategoryDto {

    /*考试场次编号*/
    private Long id;
    /*考试场次名称*/
    private String name;
    /*分数占比*/
    private BigDecimal ratio;

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

    public BigDecimal getRatio() {
        return ratio;
    }

    public void setRatio(BigDecimal ratio) {
        this.ratio = ratio;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ExaminationCategoryDto.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("ratio=" + ratio)
                .toString();
    }
}
