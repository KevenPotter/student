package com.kevenpotter.student.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 系别实体类
 *
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2019-12-05 11:42:38
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentEntity {

    /*主键ID*/
    private Long id;
    /*系别编号*/
    private Long departmentId;
    /*系别名称*/
    private String departmentName;
}
