package com.kevenpotter.student.domain.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

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
    /*系统用户编号*/
    private Long systemUserId;
    /*角色编号*/
    private Long systemRoleId;
    /*用户角色状态(0.不启用 1.启用)*/
    private Integer userRoleStatus;
    /*用户角色创建时间*/
    private LocalDateTime userRoleCreateTime;
    /*用户角色更新时间*/
    private LocalDateTime userRoleUpdateTime;
}
