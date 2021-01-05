package com.kevenpotter.student.domain.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 系统模块数据传输类
 *
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2021-01-04 22:53:16
 */
@Data
@Accessors(chain = true)
public class SystemModuleDto {

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
