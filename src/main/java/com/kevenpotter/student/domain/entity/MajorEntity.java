package com.kevenpotter.student.domain.entity;

import lombok.Data;

/**
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2019-12-05 11:46:16
 * @description 专业实体类
 */
@Data
public class MajorEntity {

    /*主键ID*/
    private Long id;
    /*专业编号*/
    private Long majorId;
    /*专业名称*/
    private Integer majorName;
    /*系别编号*/
    private Long departmentId;
}
