package com.kevenpotter.student.service;

import com.kevenpotter.student.dao.SystemRoleMenuModulePermissionDao;
import com.kevenpotter.student.domain.dto.SystemRoleMenuModulePermissionDto;
import com.kevenpotter.student.domain.entity.SystemRoleMenuModulePermissionEntity;
import com.kevenpotter.student.utils.ListUtils;
import com.kevenpotter.student.utils.NumericUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
    private ModelMapper modelMapper = new ModelMapper();

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
     * 根据[角色编号]查询[系统角色-菜单-模块-权限实体]集合
     *
     * @param roleId 角色编号
     * @return 返回根据[角色编号]查询[系统角色-菜单-模块-权限实体]集合
     * @author KevenPotter
     * @date 2021-01-08 09:48:49
     */
    public List<SystemRoleMenuModulePermissionEntity> getRoleMenuModulePermissionByRoleId(Long roleId) {
        if (NumericUtils.longIsEmpty(roleId)) return null;
        SystemRoleMenuModulePermissionDto systemRoleMenuModulePermissionDto = new SystemRoleMenuModulePermissionDto();
        systemRoleMenuModulePermissionDto.setRoleId(roleId);
        List<SystemRoleMenuModulePermissionEntity> rolesForList = systemRoleMenuModulePermissionDao.getRolesForList(systemRoleMenuModulePermissionDto);
        if (ListUtils.isEmpty(rolesForList)) return null;
        return rolesForList;
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
     * 插入[系统角色-菜单-模块-权限实体集]
     *
     * @param systemRoleMenuModulePermissions 系统角色-菜单-模块-权限数据传输类集合
     * @author KevenPotter
     * @date 2021-01-06 13:48:48
     */
    public void addSystemRoleMenuModulePermission(List<SystemRoleMenuModulePermissionDto> systemRoleMenuModulePermissions) {
        if (ListUtils.isEmpty(systemRoleMenuModulePermissions)) return;
        List<SystemRoleMenuModulePermissionDto> removePermissionList = new ArrayList<>();
        List<SystemRoleMenuModulePermissionDto> updatePermissionList = new ArrayList<>();
        List<SystemRoleMenuModulePermissionDto> insertPermissionList = new ArrayList<>();
        Long roleId = systemRoleMenuModulePermissions.get(0).getRoleId();
        List<SystemRoleMenuModulePermissionEntity> permissionsForLevel1 = this.getRoleMenuModulePermissionByRoleId(roleId);
        if (ListUtils.isEmpty(permissionsForLevel1)) {
            insertPermissionList.addAll(systemRoleMenuModulePermissions);
        } else {
            List<Long> entityMenuList = permissionsForLevel1.stream().map(SystemRoleMenuModulePermissionEntity::getMenuId).collect(Collectors.toList());
            List<Long> dtoMenuList = systemRoleMenuModulePermissions.stream().map(SystemRoleMenuModulePermissionDto::getMenuId).collect(Collectors.toList());
            List<Long> removePermissionMenuIdsByMenuId = ListUtils.distinct(ListUtils.differenceSet(entityMenuList, dtoMenuList));
            if (!ListUtils.isEmpty(removePermissionMenuIdsByMenuId)) {
                // todo
                for (Long menuId : removePermissionMenuIdsByMenuId) {
                    List<SystemRoleMenuModulePermissionEntity> removeFromMenu = permissionsForLevel1.stream().filter(s -> s.getMenuId().equals(menuId)).collect(Collectors.toList());
                    List<SystemRoleMenuModulePermissionDto> dtoRemoveFromMenu = modelMapper.map(removeFromMenu, new TypeToken<List<SystemRoleMenuModulePermissionDto>>() {
                    }.getType());
                    dtoRemoveFromMenu.forEach(e -> e.setStatus(0));
                    removePermissionList.addAll(dtoRemoveFromMenu);
                }
            }
            List<Long> updatePermissionMenuIdsByMenuId = ListUtils.distinct(ListUtils.intersectionSet(entityMenuList, dtoMenuList));
            if (!ListUtils.isEmpty(updatePermissionMenuIdsByMenuId)) {
                for (Long menuId : updatePermissionMenuIdsByMenuId) {
                    List<SystemRoleMenuModulePermissionEntity> permissionsForLevel2 = this.getRoleMenuModulePermissionByRoleIdAndMenuId(roleId, menuId);
                    List<SystemRoleMenuModulePermissionDto> dtoPermissionsForLevel2 = systemRoleMenuModulePermissions.stream().filter(s -> s.getMenuId().equals(menuId)).collect(Collectors.toList());
                    List<Long> entityModuleList = permissionsForLevel2.stream().map(SystemRoleMenuModulePermissionEntity::getModuleId).collect(Collectors.toList());
                    List<Long> dtoModuleList = dtoPermissionsForLevel2.stream().map(SystemRoleMenuModulePermissionDto::getModuleId).collect(Collectors.toList());
                    List<Long> removePermissionModuleIdsByModuleId = ListUtils.distinct(ListUtils.differenceSet(entityModuleList, dtoModuleList));
                    if (!ListUtils.isEmpty(removePermissionModuleIdsByModuleId)) {
                        for (Long moduleId : removePermissionModuleIdsByModuleId) {
                            List<SystemRoleMenuModulePermissionEntity> removeFromModule = permissionsForLevel2.stream().filter(s -> s.getModuleId().equals(moduleId)).collect(Collectors.toList());
                            List<SystemRoleMenuModulePermissionDto> dtoRemoveFromModule = modelMapper.map(removeFromModule, new TypeToken<List<SystemRoleMenuModulePermissionDto>>() {
                            }.getType());
                            dtoRemoveFromModule.forEach(e -> e.setStatus(0));
                            removePermissionList.addAll(dtoRemoveFromModule);
                        }
                    }
                    List<Long> updatePermissionModuleIdsByModuleId = ListUtils.intersectionSet(entityModuleList, dtoModuleList);
                    if (!ListUtils.isEmpty(updatePermissionModuleIdsByModuleId)) {
                        for (Long moduleId : updatePermissionModuleIdsByModuleId) {
                            SystemRoleMenuModulePermissionEntity permissionsForLevel3 = this.getRoleMenuModulePermissionByRoleIdAndMenuIdAndModuleId(roleId, menuId, moduleId);
                            SystemRoleMenuModulePermissionDto dtoPermissionsForLevel3 = systemRoleMenuModulePermissions.stream().filter(s -> s.getMenuId().equals(menuId) && s.getModuleId().equals(moduleId)).collect(Collectors.toList()).get(0);
                            List<String> corePermissions = Arrays.asList(permissionsForLevel3.getPermissions().split(","));
                            List<String> dtoCorePermissions = Arrays.asList(dtoPermissionsForLevel3.getPermissions().split(","));
                            if (!ListUtils.isEqual(corePermissions, dtoCorePermissions)) {
                                updatePermissionList.add(dtoPermissionsForLevel3.setStatus(1));
                            }
                        }
                    }
                    List<Long> insertPermissionModuleIdsByModuleId = ListUtils.distinct(ListUtils.differenceSet(dtoModuleList, entityModuleList));
                    if (!ListUtils.isEmpty(insertPermissionModuleIdsByModuleId)) {
                        for (Long moduleId : insertPermissionModuleIdsByModuleId) {
                            List<SystemRoleMenuModulePermissionDto> insertFromModule = dtoPermissionsForLevel2.stream().filter(s -> s.getModuleId().equals(moduleId)).collect(Collectors.toList());
                            insertFromModule.forEach(e -> e.setStatus(1));
                            insertPermissionList.addAll(insertFromModule);
                        }
                    }
                }
            }
            List<Long> insertPermissionMenuIdsByMenuId = ListUtils.distinct(ListUtils.differenceSet(dtoMenuList, entityMenuList));
            if (!ListUtils.isEmpty(insertPermissionMenuIdsByMenuId)) {
                for (Long menuId : insertPermissionMenuIdsByMenuId) {
                    List<SystemRoleMenuModulePermissionDto> insertFromMenu = systemRoleMenuModulePermissions.stream().filter(s -> s.getMenuId().equals(menuId)).collect(Collectors.toList());
                    insertFromMenu.forEach(e -> e.setStatus(1));
                    insertPermissionList.addAll(insertFromMenu);
                }
            }
        }
        if (!ListUtils.isEmpty(removePermissionList)) {
            for (SystemRoleMenuModulePermissionDto systemRoleMenuModulePermissionDto : removePermissionList) {
                systemRoleMenuModulePermissionDao.deleteSystemRoleMenuModulePermission(systemRoleMenuModulePermissionDto.setUpdateTime(LocalDateTime.now()));
            }
        }
        if (!ListUtils.isEmpty(updatePermissionList)) {
            for (SystemRoleMenuModulePermissionDto systemRoleMenuModulePermissionDto : updatePermissionList) {
                systemRoleMenuModulePermissionDao.updateSystemRoleMenuModulePermission(systemRoleMenuModulePermissionDto.setUpdateTime(LocalDateTime.now()));
            }
        }
        if (!ListUtils.isEmpty(insertPermissionList)) {
            for (SystemRoleMenuModulePermissionDto systemRoleMenuModulePermissionDto : insertPermissionList) {
                systemRoleMenuModulePermissionDao.addSystemRoleMenuModulePermission(systemRoleMenuModulePermissionDto.setUpdateTime(LocalDateTime.now()));
            }
        }
        System.out.println("remove:" + removePermissionList.toString());
        System.out.println("update:" + updatePermissionList.toString());
        System.out.println("insert:" + insertPermissionList.toString());
    }

}
