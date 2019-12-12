package com.kevenpotter.student.service;

import com.kevenpotter.student.dao.SystemRoleDao;
import com.kevenpotter.student.domain.entity.SystemRoleEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2019-12-12 15:46:18
 * @description 后台角色服务层类
 */
@Service
public class SystemRoleService {

    /*定义日志记录器，用来记录必要信息*/
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SystemRoleDao systemRoleDao;

    /**
     * @param roleId 角色编号
     * @return 根据[角色编号]返回[后台角色实体]
     * @author KevenPotter
     * @date 2019-12-11 22:50:39
     * @description 根据[角色编号]返回[后台角色实体]
     */
    public SystemRoleEntity getSystemRoleByRoleId(Long roleId) {
        if (null == roleId) return null;
        return systemRoleDao.getSystemRoleByRoleId(roleId);
    }
}
