package com.kevenpotter.student.service;

import com.kevenpotter.student.dao.SystemRoleMenuModulePermissionDao;
import com.kevenpotter.student.domain.dto.SystemRoleMenuModulePermissionDto;
import com.kevenpotter.student.domain.entity.SystemRoleMenuModulePermissionEntity;
import com.kevenpotter.student.utils.ListUtils;
import com.kevenpotter.student.utils.NumericUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 系统角色-菜单-模块-权限服务层类
 *
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2021-01-06 10:32:44
 */
@Service
public class SystemRoleMenuModulePermissionService {

    /*定义日志记录器，用来记录必要信息*/
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SystemRoleMenuModulePermissionDao systemRoleMenuModulePermissionDao;

    /**
     * 根据[角色编号]查询[全部系统角色-菜单-模块-权限数据传输类]
     *
     * @param roleId 角色编号
     * @return 返回根据[角色编号]查询[全部系统角色-菜单-模块-权限数据传输类]
     * @author KevenPotter
     * @date 2021-01-06 10:34:00
     */
    public List<SystemRoleMenuModulePermissionDto> getSystemRoleMenuModulePermissionByRoleId(Long roleId) {
        if (null == roleId) return null;
        return systemRoleMenuModulePermissionDao.getSystemRoleMenuModulePermissionByRoleId(roleId);
    }

    /**
     * 根据[角色编号]、[菜单编号]查询[系统角色-菜单-模块-权限实体]集合
     *
     * @param roleId 角色编号
     * @param menuId 菜单编号
     * @return 返回根据[角色编号]、[菜单编号]查询[系统角色-菜单-模块-权限实体]集合
     * @author KevenPotter
     * @date 2021-01-07 13:44:21
     */
    public List<SystemRoleMenuModulePermissionEntity> getRoleMenuModulePermissionByRoleIdAndMenuId(Long roleId, Long menuId) {
        if (NumericUtils.longIsEmpty(roleId, menuId)) return null;
        SystemRoleMenuModulePermissionDto systemRoleMenuModulePermissionDto = new SystemRoleMenuModulePermissionDto();
        systemRoleMenuModulePermissionDto.setRoleId(roleId).setMenuId(menuId);
        List<SystemRoleMenuModulePermissionEntity> rolesForList = systemRoleMenuModulePermissionDao.getRolesForList(systemRoleMenuModulePermissionDto);
        if (ListUtils.isEmpty(rolesForList)) return null;
        return rolesForList;
    }

    /**
     * 根据[角色编号]、[菜单编号]、[模块编号]查询[系统角色-菜单-模块-权限实体]
     *
     * @param roleId   角色编号
     * @param menuId   菜单编号
     * @param moduleId 模块编号
     * @return 返回根据[角色编号]、[菜单编号]、[模块编号]查询[系统角色-菜单-模块-权限实体]
     * @author KevenPotter
     * @date 2021-01-06 14:26:34
     */
    public SystemRoleMenuModulePermissionEntity getRoleMenuModulePermissionByRoleIdAndMenuIdAndModuleId(Long roleId, Long menuId, Long moduleId) {
        if (NumericUtils.longIsEmpty(roleId, menuId, moduleId)) return null;
        SystemRoleMenuModulePermissionDto systemRoleMenuModulePermissionDto = new SystemRoleMenuModulePermissionDto();
        systemRoleMenuModulePermissionDto.setRoleId(roleId).setMenuId(menuId).setModuleId(moduleId);
        List<SystemRoleMenuModulePermissionEntity> rolesForList = systemRoleMenuModulePermissionDao.getRolesForList(systemRoleMenuModulePermissionDto);
        if (ListUtils.isEmpty(rolesForList)) return null;
        return rolesForList.get(0);
    }

    /**
     * 插入一条新的[系统角色-菜单-模块-权限实体]并返回该[系统角色-菜单-模块-权限实体]
     *
     * @param systemRoleMenuModulePermissionDto 系统角色-菜单-模块-权限数据传输类
     * @return 返回插入的[系统角色-菜单-模块-权限实体]
     * @author KevenPotter
     * @date 2021-01-06 13:48:48
     */
    public SystemRoleMenuModulePermissionEntity addSystemRoleMenuModulePermission(SystemRoleMenuModulePermissionDto systemRoleMenuModulePermissionDto) {
        if (null == systemRoleMenuModulePermissionDto) return null;
        SystemRoleMenuModulePermissionEntity systemRoleMenuModulePermissionEntity = this.getRoleMenuModulePermissionByRoleIdAndMenuIdAndModuleId(systemRoleMenuModulePermissionDto.getRoleId(), systemRoleMenuModulePermissionDto.getMenuId(), systemRoleMenuModulePermissionDto.getModuleId());
        if (null != systemRoleMenuModulePermissionEntity) return null;
        systemRoleMenuModulePermissionDao.addSystemRoleMenuModulePermission(systemRoleMenuModulePermissionDto);
        return systemRoleMenuModulePermissionDao.getSystemRoleMenuModulePermissionById(systemRoleMenuModulePermissionDto.getId());
    }

    /**
     * 插入[系统角色-菜单-模块-权限实体集]
     *
     * @param systemRoleMenuModulePermissions 系统角色-菜单-模块-权限数据传输类集合
     * @author KevenPotter
     * @date 2021-01-06 13:48:48
     */
    public void addSystemRoleMenuModulePermission(List<SystemRoleMenuModulePermissionDto> systemRoleMenuModulePermissions) {
        if (ListUtils.isEmpty(systemRoleMenuModulePermissions)) return;
        Long roleId = systemRoleMenuModulePermissions.get(0).getRoleId();
        Map<Long, List<SystemRoleMenuModulePermissionDto>> longListMap = systemRoleMenuModulePermissions.stream().collect(Collectors.groupingBy(SystemRoleMenuModulePermissionDto::getMenuId));
        longListMap.forEach((k, v) -> {
            List<SystemRoleMenuModulePermissionEntity> systemRoleMenuModulePermissionEntityList = getRoleMenuModulePermissionByRoleIdAndMenuId(roleId, k);
            List<Long> entityModuleIds = systemRoleMenuModulePermissionEntityList.stream().map(SystemRoleMenuModulePermissionEntity::getModuleId).collect(Collectors.toList());
            List<Long> dtoModuleIds = v.stream().map(SystemRoleMenuModulePermissionDto::getModuleId).collect(Collectors.toList());
            List<Long> entityReduceDto = entityModuleIds.stream().filter(item -> !dtoModuleIds.contains(item)).collect(Collectors.toList());
            for (Long moduleId : entityReduceDto) {
                List<SystemRoleMenuModulePermissionDto> removePermissionList = v.stream().filter(s -> s.getModuleId().equals(moduleId)).collect(Collectors.toList());
                System.out.println(removePermissionList.toString());
                if (ListUtils.isEmpty(removePermissionList)) break;
                removePermissionList.forEach(s -> s.setStatus(0));
                // todo 修改状态
            }
            List<Long> entityEqualsDto = entityModuleIds.stream().filter(dtoModuleIds::contains).collect(Collectors.toList());
            for (Long moduleId : entityEqualsDto) {
                // ***********************************************************************************************
                List<SystemRoleMenuModulePermissionDto> updateLists = v.stream().filter(s -> s.getModuleId().equals(moduleId)).collect(Collectors.toList());
                System.out.println(updateLists.toString());
                if (ListUtils.isEmpty(updateLists)) break;
                List<SystemRoleMenuModulePermissionDto> updatePermissionList = new ArrayList<>();
                for (SystemRoleMenuModulePermissionDto systemRoleMenuModulePermissionDto : updateLists) {
                    SystemRoleMenuModulePermissionEntity systemRoleMenuModulePermissionEntity = this.getRoleMenuModulePermissionByRoleIdAndMenuIdAndModuleId(systemRoleMenuModulePermissionDto.getRoleId(), systemRoleMenuModulePermissionDto.getMenuId(), systemRoleMenuModulePermissionDto.getModuleId());
                    if (null != systemRoleMenuModulePermissionEntity) {
                        List<String> currentPermissions = Arrays.asList(systemRoleMenuModulePermissionEntity.getPermissions().split(","));
                        List<String> updatePermissions = Arrays.asList(systemRoleMenuModulePermissionDto.getPermissions().split(","));
                        if (!ListUtils.isEqual(currentPermissions, updatePermissions)) {
                            updatePermissionList.add(systemRoleMenuModulePermissionDto);
                        }
                    }
                }
                // ***********************************************************************************************
            }
            List<Long> dtoReduceEntity = dtoModuleIds.stream().filter(item -> !entityModuleIds.contains(item)).collect(Collectors.toList());
            for (Long moduleId : dtoReduceEntity) {
                // todo 增加即可
                List<SystemRoleMenuModulePermissionDto> insertPermissionList = v.stream().filter(s -> s.getModuleId().equals(moduleId)).collect(Collectors.toList());
                System.out.println(insertPermissionList.toString());
                if (ListUtils.isEmpty(insertPermissionList)) break;
            }
        });
    }

}
