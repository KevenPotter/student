package com.kevenpotter.student.domain.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 系统模块权限数据传输类
 *
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2021-01-15 10:26:13
 */
@Data
@Accessors(chain = true)
public class SystemModulePermissionDto {

    /*模块编号*/
    private Long moduleId;
    /*模块名称*/
    private String moduleName;
    /*模块权限*/
    private String permissions;

}
