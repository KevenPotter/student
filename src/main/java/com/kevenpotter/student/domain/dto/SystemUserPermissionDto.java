package com.kevenpotter.student.domain.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 系统用户权限数据传输类
 *
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2021-01-15 10:16:15
 */
@Data
@Accessors(chain = true)
public class SystemUserPermissionDto {

    /*系统用户编号*/
    private Long systemUserId;
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
    /*模块权限*/
    List<SystemModulePermissionDto> modulePermissionDtoList;

}
