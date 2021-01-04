package com.kevenpotter.student.domain.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2019-12-11 16:22:03
 * @description 后台用户-角色数据传输类
 */
@Data
@Accessors(chain = true)
public class SystemUserRoleDto {

    /*主键ID*/
    private Long id;
    /*用户编号(学号、教工号)*/
    private Long userId;
    /*角色编号*/
    private Long roleId;
}
