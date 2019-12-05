package com.kevenpotter.student.domain.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2019-11-23 11:12:10
 * @description 考试类目实体类
 */
@Data
public class ExaminationCategoryEntity implements Serializable {

    /*考试场次编号*/
    private Long id;
    /*考试场次名称*/
    private String name;
    /*分数占比*/
    private BigDecimal ratio;
}
