package com.kevenpotter.student.dao;

import com.github.pagehelper.Page;
import com.kevenpotter.student.domain.dto.SystemAllModuleDto;
import com.kevenpotter.student.domain.dto.SystemModuleDto;
import com.kevenpotter.student.domain.entity.SystemModuleEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 系统模块持久层类
 *
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2021-01-04 22:51:14
 */
@Repository
@Mapper
public interface SystemModuleDao {

    /**
     * 根据给定的参数进行[系统模块实体]列表的查询
     *
     * @param menuId       菜单编号
     * @param moduleName   模块名称
     * @param moduleStatus 模块状态
     * @return 返回根据给定的参数进行[系统模块实体]列表的查询
     * @author KevenPotter
     * @date 2021-01-04 09:22:01
     */
    @Select("<script> " +
            "SELECT * FROM system_module sm " +
            "<where> " +
            "<if test='menuId != null'> " +
            "AND sm.menu_id = #{menuId} " +
            "</if>" +
            "<if test='moduleName != null'> " +
            "AND sm.module_name LIKE CONCAT('%',#{moduleName},'%') " +
            "</if>" +
            "<if test='moduleStatus != null'> " +
            "AND sm.module_status = #{moduleStatus} " +
            "</if> " +
            "</where>" +
            "</script>")
    Page<SystemModuleEntity> getModules(@Param("menuId") Long menuId, @Param("moduleName") String moduleName, @Param("moduleStatus") Integer moduleStatus);

    /**
     * 根据[菜单编号]查询[全部系统模块数据传输类]
     *
     * @param menuId 菜单编号
     * @return 返回根据[菜单编号]查询[全部系统模块数据传输类]
     * @author KevenPotter
     * @date 2021-01-05 09:10:14
     */
    @Select("SELECT sm.id AS 'menuId', sm.menu_name AS 'menuName', smo.id AS 'moduleId', smo.module_name AS 'moduleName' FROM system_menu sm LEFT JOIN system_module smo ON sm.id = smo.menu_id WHERE sm.id = #{menuId}")
    List<SystemAllModuleDto> getModuleByMenuId(@Param("menuId") Long menuId);

    /**
     * 根据[模块名称]查询[系统模块实体]
     *
     * @param moduleName 模块名称
     * @return 返回根据[模块名称]查询[系统模块实体]
     * @author KevenPotter
     * @date 2021-01-04 23:05:00
     */
    @Select("SELECT * FROM system_module sm WHERE sm.module_name = #{moduleName}")
    SystemModuleEntity getModuleByModuleName(@Param("moduleName") String moduleName);

    /**
     * 根据[模块编号]查询[系统模块实体]
     *
     * @param id 角色编号
     * @return 返回一个[系统模块实体]
     * @author KevenPotter
     * @date 2021-01-04 23:03:39
     */
    @Select("SELECT * FROM system_module sm WHERE sm.id = #{id}")
    SystemModuleEntity getSystemModuleById(@Param("id") Long id);

    /**
     * 插入一条新的[系统模块实体]
     *
     * @param systemModuleDto 系统模块数据传输类
     * @author KevenPotter
     * @date 2021-01-04 23:01:58
     */
    @Insert("INSERT INTO `student`.`system_module` (`menu_id`, `module_name`, `module_status`, `module_create_time`, `module_update_time`) VALUES (#{systemModuleDto.menuId}, #{systemModuleDto.moduleName}, #{systemModuleDto.moduleStatus}, NOW(), NOW());")
    @Options(useGeneratedKeys = true, keyProperty = "systemModuleDto.id", keyColumn = "id")
    void addSystemModule(@Param("systemModuleDto") SystemModuleDto systemModuleDto);

    /**
     * 更新[系统模块实体]
     *
     * @param systemModuleDto 系统模块数据传输类
     * @author KevenPotter
     * @date 2021-01-04 22:57:09
     */
    void updateSystemModule(@Param("systemModuleDto") SystemModuleDto systemModuleDto);
}

