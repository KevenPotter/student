package com.kevenpotter.student.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 系统角色实体类
 *
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2019-12-11 16:16:54
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class SystemRoleEntity implements Serializable {

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
