package com.kevenpotter.student.service;

import com.kevenpotter.student.dao.SystemUserRoleDao;
import com.kevenpotter.student.domain.dto.SystemUserRoleDto;
import com.kevenpotter.student.domain.entity.SystemUserRoleEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        if (null != systemUserRoleEntity) return null;
        systemUserRoleDao.addUserRole(systemUserRoleDto);
        return systemUserRoleDao.getSystemUserRoleById(systemUserRoleDto.getId());
    }
}
