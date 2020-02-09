package com.kevenpotter.student.domain.dto;

import lombok.Data;

/**
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2020-02-08 17:31:05
 * @description 系别专业数量数据详情传输类
 */
@Data
public class DepartmentNestedPiesDataDto {

    /*专业数量*/
    private Integer value;
    /*专业名称*/
    private String name;
}
