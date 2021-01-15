package com.kevenpotter.student.service;

import com.kevenpotter.student.dao.SystemUserRoleDao;
import com.kevenpotter.student.domain.dto.*;
import com.kevenpotter.student.domain.entity.SystemUserRoleEntity;
import com.kevenpotter.student.utils.ListUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2019-12-12 16:04:16
 * @description 后台用户-角色服务层类
 */
@Service
public class SystemUserRoleService {

    /*定义日志记录器，用来记录必要信息*/
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SystemMenuService systemMenuService;
    @Autowired
    private SystemModuleService systemModuleService;
    @Autowired
    private SystemRoleMenuModulePermissionService systemRoleMenuModulePermissionService;
    @Autowired
    private SystemUserRoleDao systemUserRoleDao;

    /**
     * 根据[用户编号]返回一个[系统用户-角色实体]
     *
     * @param userId 用户编号
     * @return 根据[用户编号]返回一个[系统用户-角色实体]
     * @author KevenPotter
     * @date 2021-01-11 15:33:59
     */
    public SystemUserRoleEntity getSystemUserRoleByUserId(String userId) {
        if (null == userId) return null;
        return systemUserRoleDao.getSystemUserRoleByUserId(userId);
    }

    /**
     * 插入一条新的[系统用户角色实体]并返回该[系统用户角色实体]
     *
     * @param systemUserRoleDto 系统用户角色数据传输类
     * @return 返回插入的[系统用户角色实体]
     * @author KevenPotter
     * @date 2021-01-11 16:17:40
     */
    public SystemUserRoleEntity addSystemUserRole(SystemUserRoleDto systemUserRoleDto) {
        if (null == systemUserRoleDto) return null;
        SystemUserRoleEntity systemUserRoleEntity = this.getSystemUserRoleByUserId(systemUserRoleDto.getSystemUserId());
        if (null != systemUserRoleEntity) {
            systemUserRoleDao.updateSystemUserRole(systemUserRoleDto);
            return systemUserRoleDao.getSystemUserRoleByUserId(systemUserRoleDto.getSystemUserId());
        }
        systemUserRoleDao.addUserRole(systemUserRoleDto);
        return systemUserRoleDao.getSystemUserRoleById(systemUserRoleDto.getId());
    }

    /**
     * 根据[系统用户编号]获取该用户的[系统用户权限数据传输类]
     *
     * @param systemUserId 系统用户编号
     * @return 根据[系统用户编号]获取该用户的[系统用户权限数据传输类]
     * @author KevenPotter
     * @date 2021-01-15 10:43:16
     */
    public List<SystemUserPermissionDto> getSystemUserPermissionByUserId(String systemUserId) {
        SystemUserRoleEntity systemUserRoleEntity = systemUserRoleDao.getSystemUserRoleByUserId(systemUserId);
        if (0 == systemUserRoleEntity.getUserRoleStatus()) return null;
        List<SystemRoleMenuModulePermissionDto> systemRoleMenuModulePermissionDtoList = systemRoleMenuModulePermissionService.getSystemRoleMenuModulePermissionByRoleId(systemUserRoleEntity.getSystemRoleId());
        if (ListUtils.isEmpty(systemRoleMenuModulePermissionDtoList)) return null;
        List<SystemUserPermissionDto> systemUserPermissionDtoList = new ArrayList<>();
        List<Long> menuList = ListUtils.distinct(systemRoleMenuModulePermissionDtoList.stream().map(SystemRoleMenuModulePermissionDto::getMenuId).collect(Collectors.toList()));
        for (Long menuId : menuList) {
            List<SystemRoleMenuModulePermissionDto> systemRoleMenuModulePermissionDtos = systemRoleMenuModulePermissionDtoList.stream().filter(u -> (u.getStatus().equals(1) && u.getMenuId().equals(menuId))).collect(Collectors.toList());
            SystemAllMenuForIndexDto systemAllMenuForIndexDto = systemMenuService.getMenusByMenuId(menuId);
            SystemUserPermissionDto systemUserPermissionDto = new SystemUserPermissionDto();
            systemUserPermissionDto.setMenuId(menuId).setMenuName(systemAllMenuForIndexDto.getMenuName()).setMenuLinkUrl(systemAllMenuForIndexDto.getMenuLinkUrl()).setMenuIcon(systemAllMenuForIndexDto.getMenuIcon()).setMenuSortNumber(systemAllMenuForIndexDto.getMenuSortNumber());
            List<SystemModulePermissionDto> systemModulePermissionDtoList = new ArrayList<>();
            for (SystemRoleMenuModulePermissionDto systemRoleMenuModulePermissionDto : systemRoleMenuModulePermissionDtos) {
                SystemModulePermissionDto systemModulePermissionDto = new SystemModulePermissionDto();
                systemModulePermissionDto.setModuleId(systemRoleMenuModulePermissionDto.getModuleId()).setModuleName(systemModuleService.getModuleByModuleId(systemRoleMenuModulePermissionDto.getModuleId()).getModuleName()).setPermissions(systemRoleMenuModulePermissionDto.getPermissions());
                systemModulePermissionDtoList.add(systemModulePermissionDto);
            }
            systemUserPermissionDto.setModulePermissionDtoList(systemModulePermissionDtoList);
            systemUserPermissionDtoList.add(systemUserPermissionDto);
        }
        return systemUserPermissionDtoList;
    }
}
