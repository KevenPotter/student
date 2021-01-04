package com.kevenpotter.student.domain.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2019-11-23 18:54:39
 * @description 考试类目数据传输类
 */
@Data
@Accessors(chain = true)
public class ExaminationCategoryDto {

    /*考试场次编号*/
    private Long id;
    /*考试场次名称*/
    private String name;
    /*分数占比*/
    private BigDecimal ratio;
}
