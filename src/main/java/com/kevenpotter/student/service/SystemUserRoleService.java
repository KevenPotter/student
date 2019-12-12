package com.kevenpotter.student.service;

import com.kevenpotter.student.dao.SystemUserRoleDao;
import com.kevenpotter.student.domain.entity.SystemUserRoleEntity;
import com.kevenpotter.student.utils.ListUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
     * @param userId 用户编号
     * @return 根据[用户编号]返回[后台用户-角色实体]列表
     * @author KevenPotter
     * @date 2019-12-12 16:06:17
     * @description 根据[用户编号]返回[后台用户-角色实体]列表
     */
    public List<SystemUserRoleEntity> getSystemUserRoleByUserId(Long userId) {
        if (null == userId) return null;
        List<SystemUserRoleEntity> systemUserRoleEntityList = systemUserRoleDao.getSystemUserRoleBy(userId);
        if (ListUtils.isEmpty(systemUserRoleEntityList)) return null;
        return systemUserRoleEntityList;
    }
}
