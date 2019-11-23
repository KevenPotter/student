package com.kevenpotter.student.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.StringJoiner;

/**
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2019-11-23 11:12:10
 * @description 考试类目实体类
 */
public class ExaminationCategoryEntity implements Serializable {

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
        return new StringJoiner(", ", ExaminationCategoryEntity.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("ratio=" + ratio)
                .toString();
    }
}
