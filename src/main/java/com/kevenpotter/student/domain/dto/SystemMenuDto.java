package com.kevenpotter.student.domain.dto;

import lombok.Data;

/**
 * 系统菜单数据传输类
 *
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2020-12-29 13:38:25
 */
@Data
public class SystemMenuDto {

    /*自增ID*/
    private Long id;
    /*菜单名称*/
    private String menuName;
    /*菜单连接*/
    private String menuLinkUrl;
    /*菜单图标*/
    private String menuIcon;
    /*菜单排序编号*/
    private Integer menuSortNumber;
    /*菜单状态(0.不启用 1.启用)*/
    private Integer menuStatus;
}
