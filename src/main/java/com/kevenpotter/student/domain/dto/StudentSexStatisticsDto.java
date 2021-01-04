package com.kevenpotter.student.domain.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2020-01-02 14:12:07
 * @description 男女人数数据传输类
 */
@Data
@Accessors(chain = true)
public class StudentSexStatisticsDto {

    /*系别名称*/
    private String departmentName;
    /*男性人数*/
    private Integer numberOfMales;
    /*女性人数*/
    private Integer numberOfFemales;
}
