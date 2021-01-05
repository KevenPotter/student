package com.kevenpotter.student.domain.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 首页全部系统菜单数据传输类
 *
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2021-01-05 13:47:29
 */
@Data
@Accessors(chain = true)
public class SystemAllMenuForIndexDto {

    /*菜单编号*/
    private Long menuId;
    /*菜单名称*/
    private String menuName;
    /*菜单连接*/
    private String menuLinkUrl;
    /*菜单图标*/
    private String menuIcon;
    /*菜单排序编号*/
    private Integer menuSortNumber;
}
