package com.kevenpotter.student.domain.dto;

import lombok.Data;

/**
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2019-12-05 11:49:09
 * @description 专业数据传输类
 */
@Data
public class MajorDto {

    /*主键ID*/
    private Long id;
    /*专业编号*/
    private Long majorId;
    /*专业名称*/
    private String majorName;
    /*系别编号*/
    private Long departmentId;
}
