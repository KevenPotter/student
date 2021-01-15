package com.kevenpotter.student.dao;

import com.github.pagehelper.Page;
import com.kevenpotter.student.domain.dto.SystemAllMenuDto;
import com.kevenpotter.student.domain.dto.SystemAllMenuForIndexDto;
import com.kevenpotter.student.domain.dto.SystemMenuDto;
import com.kevenpotter.student.domain.entity.SystemMenuEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 菜单持久层类
 *
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2020-12-28 15:37:41
 */
@Repository
@Mapper
public interface SystemMenuDao {

    /**
     * 根据给定的参数进行[系统菜单实体]列表的查询
     *
     * @param menuName   菜单名称
     * @param menuStatus 菜单状态
     * @return 返回根据给定的参数进行[系统菜单实体]列表的查询
     * @author KevenPotter
     * @date 2020-12-28 15:46:18
     */
    @Select("<script> " +
            "SELECT * FROM system_menu sm " +
            "<where> " +
            "<if test='menuName != null'> " +
            "AND sm.menu_name LIKE CONCAT('%',#{menuName},'%') " +
            "</if>" +
            "<if test='menuStatus != null'> " +
            "AND sm.menu_status = #{menuStatus}  " +
            "</if> " +
            "</where>" +
            "</script>")
    Page<SystemMenuEntity> getMenus(@Param("menuName") String menuName, @Param("menuStatus") Integer menuStatus);

    /**
     * 获取所有[全部系统菜单数据传输类]
     *
     * @return 返回所有[全部系统菜单数据传输类]
     * @author KevenPotter
     * @date 2021-01-05 09:49:40
     */
    @Select("SELECT sm.id AS 'menuId', sm.menu_name AS 'menuName', sm.menu_sort_number AS 'menuSortNumber' FROM system_menu sm")
    List<SystemAllMenuDto> getAllMenus();

    /**
     * 获取所有[首页全部系统菜单数据传输类]
     *
     * @return 返回所有[首页全部系统菜单数据传输类]
     * @author KevenPotter
     * @date 2021-01-05 13:44:10
     */
    @Select("SELECT sm.id AS 'menuId', sm.menu_name AS 'menuName', sm.menu_link_url AS 'menuLinkUrl', sm.menu_icon AS 'menuIcon', sm.menu_sort_number AS 'menuSortNumber' FROM system_menu sm WHERE sm.menu_status = 1")
    List<SystemAllMenuForIndexDto> getAllMenusForIndex();

    /**
     * 依据[菜单编号]获取[首页全部系统菜单数据传输类]
     *
     * @return 返回依据[菜单编号]获取[首页全部系统菜单数据传输类]
     * @author KevenPotter
     * @date 2021-01-15 11:03:51
     */
    @Select("SELECT sm.id AS 'menuId', sm.menu_name AS 'menuName', sm.menu_link_url AS 'menuLinkUrl', sm.menu_icon AS 'menuIcon', sm.menu_sort_number AS 'menuSortNumber' FROM system_menu sm WHERE sm.id = #{menuId} AND sm.menu_status = 1")
    SystemAllMenuForIndexDto getMenusByMenuId(@Param("menuId") Long menuId);

    /**
     * 根据[菜单名称]查询[系统菜单实体]
     *
     * @param menuName 菜单名称
     * @return 返回根据[菜单名称]查询[系统菜单实体]
     * @author KevenPotter
     * @date 2020-12-30 10:35:21
     */
    @Select("SELECT * FROM system_menu sm WHERE sm.menu_name = #{menuName}")
    SystemMenuEntity getMenuByMenuName(@Param("menuName") String menuName);

    /**
     * 根据[菜单连接]查询[系统菜单实体]
     *
     * @param menuLinkUrl 菜单连接
     * @return 返回根据[菜单连接]查询[系统菜单实体]
     * @author KevenPotter
     * @date 2020-12-30 10:37:18
     */
    @Select("SELECT * FROM system_menu sm WHERE sm.menu_link_url = #{menuLinkUrl}")
    SystemMenuEntity getMenuByMenuLinkUrl(@Param("menuLinkUrl") String menuLinkUrl);

    /**
     * 根据[菜单图标]查询[系统菜单实体]
     *
     * @param menuIcon 菜单图标
     * @return 返回根据[菜单图标]查询[系统菜单实体]
     * @author KevenPotter
     * @date 2020-12-30 10:45:45
     */
    @Select("SELECT * FROM system_menu sm WHERE sm.menu_icon = #{menuIcon}")
    SystemMenuEntity getMenuByMenuIcon(@Param("menuIcon") String menuIcon);

    /**
     * 根据[学生编号]查询[系统菜单实体]
     *
     * @param id 菜单编号
     * @return 返回一个[系统菜单实体]
     * @author KevenPotter
     * @date 2020-12-29 13:49:20
     */
    @Select("SELECT * FROM system_menu sm WHERE sm.id = #{id}")
    SystemMenuEntity getSystemMenuById(@Param("id") Long id);

    /**
     * 插入一条新的[系统菜单实体]
     *
     * @param systemMenuDto 系统菜单数据传输类
     * @author KevenPotter
     * @date 2020-12-30 10:17:22
     */
    @Insert("INSERT INTO `student`.`system_menu` (`menu_name`, `menu_link_url`, `menu_icon`, `menu_sort_number`, `menu_status`, `menu_create_time`, `menu_update_time`) VALUES (#{systemMenuDto.menuName}, #{systemMenuDto.menuLinkUrl}, #{systemMenuDto.menuIcon}, #{systemMenuDto.menuSortNumber}, #{systemMenuDto.menuStatus}, NOW(), NOW());")
    @Options(useGeneratedKeys = true, keyProperty = "systemMenuDto.id", keyColumn = "id")
    void addMenu(@Param("systemMenuDto") SystemMenuDto systemMenuDto);

    /**
     * 更新[系统菜单实体]
     *
     * @param systemMenuDto 系统菜单数据传输类
     * @author KevenPotter
     * @date 2020-12-29 14:02:36
     */
    void updateSystemMenu(@Param("systemMenuDto") SystemMenuDto systemMenuDto);

}

