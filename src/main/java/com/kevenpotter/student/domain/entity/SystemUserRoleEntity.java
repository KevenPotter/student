package com.kevenpotter.student.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 系统用户-角色实体类
 *
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2019-12-11 16:21:02
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class SystemUserRoleEntity implements Serializable {

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
