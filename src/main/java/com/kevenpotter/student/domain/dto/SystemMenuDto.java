package com.kevenpotter.student.domain.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 系统菜单数据传输类
 *
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2021-01-01 20:02:36
 */
@Data
@Accessors(chain = true)
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
    /*菜单创建时间*/
    private LocalDateTime menuCreateTime;
    /*菜单更新时间*/
    private LocalDateTime menuUpdateTime;
}
