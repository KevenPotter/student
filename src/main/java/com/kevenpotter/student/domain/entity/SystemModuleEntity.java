package com.kevenpotter.student.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 系统模块实体类
 *
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2020-12-28 10:49:58
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class SystemModuleEntity implements Serializable {

    /*主键ID*/
    private Long id;
    /*菜单编号*/
    private Long menuId;
    /*模块名称*/
    private String moduleName;
    /*模块状态(0.不启用 1.启用)*/
    private Integer moduleStatus;
    /*模块创建时间*/
    private LocalDateTime moduleCreateTime;
    /*模块更新时间*/
    private LocalDateTime moduleUpdateTime;
}
