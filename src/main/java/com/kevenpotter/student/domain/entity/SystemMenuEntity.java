package com.kevenpotter.student.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 系统菜单实体类
 *
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2020-12-24 17:05:12
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class SystemMenuEntity implements Serializable {

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
