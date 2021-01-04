package com.kevenpotter.student.domain.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2019-12-05 11:47:10
 * @description 系别数据传输类
 */
@Data
@Accessors(chain = true)
public class DepartmentDto {

    /*主键ID*/
    private Long id;
    /*系别编号*/
    private Long departmentId;
    /*系别名称*/
    private String departmentName;
}
