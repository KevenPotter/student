package com.kevenpotter.student.domain.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 全部系统模块数据传输类
 *
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2021-01-05 13:33:20
 */
@Data
@Accessors(chain = true)
public class SystemAllModuleDto {

    /*模块编号*/
    private Long moduleId;
    /*菜单编号*/
    private Long menuId;
    /*模块名称*/
    private String moduleName;
}
