package com.kevenpotter.student.domain.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 全部系统角色数据传输类
 *
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2021-01-11 14:43:23
 */
@Data
@Accessors(chain = true)
public class SystemAllRoleDto {

    /*主键ID*/
    private Long id;
    /*角色名称*/
    private String roleName;
    /*角色状态(0.不启用 1.启用)*/
    private Integer roleStatus;
}
