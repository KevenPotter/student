package com.kevenpotter.student.domain.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 系统角色数据传输类
 *
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2019-12-11 16:18:10
 */
@Data
@Accessors(chain = true)
public class SystemRoleDto {

    /*主键ID*/
    private Long id;
    /*角色名称*/
    private String roleName;
    /*角色状态(0.不启用 1.启用)*/
    private Integer roleStatus;
    /*角色创建时间*/
    private LocalDateTime roleCreateTime;
    /*角色更新时间*/
    private LocalDateTime roleUpdateTime;
}
