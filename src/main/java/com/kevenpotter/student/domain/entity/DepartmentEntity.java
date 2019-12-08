package com.kevenpotter.student.domain.entity;

import lombok.Data;

/**
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2019-12-05 11:42:38
 * @description 系别实体类
 */
@Data
public class DepartmentEntity {

    /*主键ID*/
    private Long id;
    /*系别编号*/
    private Long departmentId;
    /*系别名称*/
    private String departmentName;
}
