package com.kevenpotter.student.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 专业实体类
 *
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2019-12-05 11:46:16
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class MajorEntity {

    /*主键ID*/
    private Long id;
    /*专业编号*/
    private Integer majorId;
    /*专业名称*/
    private String majorName;
    /*系别编号*/
    private Integer departmentId;
}
