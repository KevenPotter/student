package com.kevenpotter.student.service;

import com.kevenpotter.student.dao.SystemUserDao;
import com.kevenpotter.student.domain.dto.SystemUserDto;
import com.kevenpotter.student.domain.entity.SystemUserEntity;
import com.kevenpotter.student.utils.NumericUtils;
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
     * @param systemUserDto 后台用户数据传输类
     * @return 根据[后台用户数据传输类]返回[后台用户实体类]
     * @author KevenPotter
     * @date 2019-12-11 22:50:39
     * @description 根据[后台用户数据传输类]返回[后台用户实体类]
     */
    public SystemUserEntity getSystemUser(SystemUserDto systemUserDto) {
        if (null == systemUserDto) return null;
        return systemUserDao.getSystemUser(systemUserDto);
    }

    /**
     * @param systemUserDto 后台用户数据传输类
     * @return 返回插入的[后台用户实体]
     * @author KevenPotter
     * @date 2019-12-11 16:20:33
     * @description 插入一条新的[后台用户实体]并返回该[后台用户实体]
     */
    public SystemUserEntity addSystemUser(SystemUserDto systemUserDto) {
        if (null == systemUserDto) return null;
        if (!StringUtils.isEmpty(systemUserDto.getUserNickName())) {
            SystemUserEntity systemUserEntity = this.getSystemUserByNickname(systemUserDto.getUserNickName());
            if (null != systemUserEntity) return null;
        }
        if (!StringUtils.isEmpty(systemUserDto.getUserEmail())) {
            SystemUserEntity systemUserEntity = this.getSystemUserByEmail(systemUserDto.getUserEmail());
            if (null != systemUserEntity) return null;
        }
        if (null != systemUserDto.getUserMobile()) {
            SystemUserEntity systemUserEntity = this.getSystemUserByMobile(systemUserDto.getUserMobile());
            if (null != systemUserEntity) return null;
        }
        systemUserDto.setUserId(NumericUtils.generateRandomNumber(18));
        systemUserDao.addSystemUser(systemUserDto);
        return this.getSystemUserById(systemUserDto.getId());
    }

    /**
     * @param id 主键ID
     * @return 根据[主键ID]返回[后台用户实体类]
     * @author KevenPotter
     * @date 2019-12-19 11:20:20
     * @description 根据[主键ID]返回[后台用户实体类]
     */
    public SystemUserEntity getSystemUserById(Long id) {
        if (null == id) return null;
        return systemUserDao.getSystemUserById(id);
    }

    /**
     * @param userNickname 用户昵称
     * @return 根据[用户昵称]返回[后台用户实体类]
     * @author KevenPotter
     * @date 2019-12-18 15:40:33
     * @description 根据[用户昵称]返回[后台用户实体类]
     */
    public SystemUserEntity getSystemUserByNickname(String userNickname) {
        if (StringUtils.isEmpty(userNickname)) return null;
        return systemUserDao.getSystemUserByNickname(userNickname);
    }

    /**
     * @param userEmail 用户邮箱
     * @return 根据[用户邮箱]返回[后台用户实体类]
     * @author KevenPotter
     * @date 2019-12-18 15:41:43
     * @description 根据[用户邮箱]返回[后台用户实体类]
     */
    public SystemUserEntity getSystemUserByEmail(String userEmail) {
        if (StringUtils.isEmpty(userEmail)) return null;
        return systemUserDao.getSystemUserByEmail(userEmail);
    }

    /**
     * @param userMobile 用户手机
     * @return 根据[用户手机]返回[后台用户实体类]
     * @author KevenPotter
     * @date 2019-12-18 15:43:06
     * @description 根据[用户手机]返回[后台用户实体类]
     */
    public SystemUserEntity getSystemUserByMobile(Long userMobile) {
        if (null == userMobile) return null;
        return systemUserDao.getSystemUserByMobile(userMobile);
    }
}
