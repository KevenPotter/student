package com.kevenpotter.student.domain.dto;

import lombok.Data;

import java.util.ArrayList;

/**
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2020-02-08 16:51:18
 * @description 系别专业数量数据传输类
 */
@Data
public class DepartmentNestedPiesDto {

    /*专业名称*/
    private ArrayList<String> majorNameList;
    /*专业相关数据*/
    private ArrayList<DepartmentNestedPiesDataDto> departmentNestedPiesDataDtoList;
}
