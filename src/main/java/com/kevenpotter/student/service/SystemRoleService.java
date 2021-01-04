package com.kevenpotter.student.service;

import com.github.pagehelper.Page;
import com.kevenpotter.student.dao.SystemRoleDao;
import com.kevenpotter.student.domain.dto.SystemRoleDto;
import com.kevenpotter.student.domain.entity.SystemRoleEntity;
import com.kevenpotter.student.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * 系统角色服务层类
 *
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2019-12-12 15:46:18
 */
@Service
public class SystemRoleService {

    /*定义日志记录器，用来记录必要信息*/
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SystemRoleDao systemRoleDao;

    /**
     * 获取[系统角色实体]分页数据
     *
     * @param roleName   角色名称
     * @param roleStatus 角色状态
     * @return 返回获取[系统角色实体]分页数据
     * @author KevenPotter
     * @date 2021-01-04 09:19:23
     */
    public Page<SystemRoleEntity> getRoles(String roleName, Integer roleStatus) {
        return systemRoleDao.getRoles(roleName, roleStatus);
    }

    /**
     * 根据[角色名称]查询[系统角色实体]
     *
     * @param roleName 角色名称
     * @return 返回根据[角色名称]查询[系统角色实体]
     * @author KevenPotter
     * @date 2021-01-04 09:25:58
     */
    public SystemRoleEntity getRoleByRoleName(String roleName) {
        if (StringUtils.isEmpty(roleName)) return null;
        return systemRoleDao.getRoleByRoleName(roleName);
    }

    /**
     * 插入一条新的[系统角色实体]并返回该[系统角色实体]
     *
     * @param systemRoleDto 系统角色数据传输类
     * @return 返回插入的[系统角色实体]
     * @author KevenPotter
     * @date 2021-01-04 09:24:15
     */
    public SystemRoleEntity addSystemRole(SystemRoleDto systemRoleDto) {
        if (null == systemRoleDto) return null;
        SystemRoleEntity systemRoleEntity = this.getRoleByRoleName(systemRoleDto.getRoleName());
        if (null != systemRoleEntity) return null;
        systemRoleDao.addSystemRole(systemRoleDto);
        return systemRoleDao.getSystemRoleById(systemRoleDto.getId());
    }

    /**
     * 更新[系统角色实体]并返回更新之前的[系统角色实体]
     *
     * @param systemRoleDto 系统角色数据传输类
     * @return 返回更新之前的系统角色实体
     * @author KevenPotter
     * @date 2021-01-04 09:38:55
     */
    public SystemRoleEntity updateSystemRole(SystemRoleDto systemRoleDto) {
        if (null == systemRoleDto) return null;
        SystemRoleEntity systemRoleEntity = systemRoleDao.getSystemRoleById(systemRoleDto.getId());
        if (null == systemRoleEntity) return null;
        systemRoleDao.updateSystemRole(systemRoleDto.setRoleUpdateTime(LocalDateTime.now()));
        return systemRoleDao.getSystemRoleById(systemRoleDto.getId());
    }
}
