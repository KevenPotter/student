package com.kevenpotter.student.domain.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 全部系统菜单数据传输类
 *
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2021-01-05 10:09:26
 */
@Data
@Accessors(chain = true)
public class SystemAllMenuDto {

    /*菜单编号*/
    private Long menuId;
    /*菜单名称*/
    private String menuName;
}
