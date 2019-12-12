package com.kevenpotter.student.service;

import com.kevenpotter.student.dao.SystemUserDao;
import com.kevenpotter.student.domain.entity.SystemUserEntity;
import com.kevenpotter.student.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2019-12-11 22:47:50
 * @description 后台用户服务层类
 */
@Service
public class SystemUserService {

    /*定义日志记录器，用来记录必要信息*/
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SystemUserDao systemUserDao;

    /**
     * @param userId   用户编号(学号、教工号)
     * @param userName 用户名称
     * @return 根据[用户编号]或[用户名称]返回[后台用户实体类]
     * @author KevenPotter
     * @date 2019-12-11 22:50:39
     * @description 根据[用户编号]或[用户名称]返回[后台用户实体类]
     */
    public SystemUserEntity getSystemUser(Long userId, String userName) {
        if (null == userId && StringUtils.isEmpty(userName)) return null;
        if (StringUtils.isEmpty(userName)) userName = null;
        else userName = userName.trim();
        return systemUserDao.getSystemUser(userId, userName);
    }
}
