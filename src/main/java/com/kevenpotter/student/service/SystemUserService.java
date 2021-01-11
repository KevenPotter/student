package com.kevenpotter.student.service;

import com.github.pagehelper.Page;
import com.kevenpotter.student.dao.SystemUserDao;
import com.kevenpotter.student.domain.dto.SystemUserDto;
import com.kevenpotter.student.domain.dto.SystemUserWithoutImportantInformationDto;
import com.kevenpotter.student.domain.entity.SystemUserEntity;
import com.kevenpotter.student.utils.AccountVerification;
import com.kevenpotter.student.utils.NumericUtils;
import com.kevenpotter.student.utils.SaltUtils;
import com.kevenpotter.student.utils.StringUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
     * 根据[用户编号]、[用户昵称]和[用户状态]返回[后台用户实体类]
     *
     * @param userId       用户编号
     * @param userNickname 用户昵称
     * @return 返回根据[用户编号]、[用户昵称]和[用户状态]返回[后台用户实体类]
     * @author KevenPotter
     * @date 2021-01-11 10:10:10
     */
    public Page<SystemUserWithoutImportantInformationDto> getSystemUsers(Long userId, String userNickname, Integer userStatus) {
        return systemUserDao.getSystemUsers(userId, userNickname, userStatus);
    }

    /**
     * 插入一条新的[后台用户实体]并返回该[后台用户实体]
     *
     * @param systemUserDto 后台用户数据传输类
     * @return 返回插入的[后台用户实体]
     * @author KevenPotter
     * @date 2019-12-11 16:20:33
     */
    public SystemUserEntity addSystemUser(SystemUserDto systemUserDto) {
        if (null == systemUserDto) return null;
        if (!StringUtils.isEmpty(systemUserDto.getUserNickName())) {                                                    // 判断[用户昵称]是否不为空
            SystemUserEntity systemUserEntity = this.getSystemUserByNickname(systemUserDto.getUserNickName());          // 不为空的话就通过该[用户昵称]去数据库内进行查找
            if (null != systemUserEntity) return null;                                                                  // 如果查到的结果也不为空，说明有人已经用了此昵称，那么返回空不执行即可
        }
        if (!StringUtils.isEmpty(systemUserDto.getUserEmail())) {                                                       // 判断[用户邮箱]是否不为空
            SystemUserEntity systemUserEntity = this.getSystemUserByEmail(systemUserDto.getUserEmail());                // 不为空的话就通过该[用户邮箱]去数据库内进行查找
            if (null != systemUserEntity) return null;                                                                  // 如果查到的结果也不为空，说明有人已经用了此邮箱，那么返回空不执行即可
        }
        if (null != systemUserDto.getUserMobile()) {                                                                    // 判断[用户手机]是否不为空
            SystemUserEntity systemUserEntity = this.getSystemUserByMobile(systemUserDto.getUserMobile());              // 不为空的话就通过该[用户手机]去数据库内进行查找
            if (null != systemUserEntity) return null;                                                                  // 如果查到的结果也不为空，说明有人已经用了此手机，那么返回空不执行即可
        }
        String salt = SaltUtils.getRandomSalt(8);                                                                    // 获取8位随机盐值
        systemUserDto.setUserPassword(new Md5Hash(systemUserDto.getUserPassword(), salt, 1024).toHex());    // 对[用户密码进行设置]
        systemUserDto.setUserId(NumericUtils.generateRandomNumber(18));                                              // 设置默认18位随机[用户编号]
        systemUserDto.setSalt(SaltUtils.getEncodeSaltValue(salt));                                                      // 进行系统用户的添加
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

    /**
     * @return 返回账户记录总条数
     * @author KevenPotter
     * @date 2019-12-20 16:55:47
     * @description 返回账户记录总条数
     */
    public Long getTheTotalNumberOfAccounts() {
        return systemUserDao.getCount();
    }

    /**
     * 根据[用户名称]获取[系统用户实体类]
     *
     * @param userName [用户名称]
     * @return 返回根据[用户名称]获取[系统用户实体类]
     * @author KevenPotter
     * @date 2020-12-28 15:09:56
     */
    public SystemUserEntity getSystemUser(String userName) {
        SystemUserEntity systemUserEntity = null;
        if (AccountVerification.isStudentNo(userName)) systemUserEntity = systemUserDao.getSystemUserByUserId(Long.valueOf(userName));
        if (AccountVerification.isEmail(userName)) systemUserEntity = systemUserDao.getSystemUserByEmail(userName);
        if (AccountVerification.isMobile(userName)) systemUserEntity = systemUserDao.getSystemUserByMobile(Long.valueOf(userName));
        if (AccountVerification.isNickname(userName)) systemUserEntity = systemUserDao.getSystemUserByNickname(userName);
        return systemUserEntity;
    }

    /**
     * 更新[系统用户实体]并返回更新之前的[系统用户实体]
     *
     * @param systemUserDto 系统用户数据传输类
     * @return 返回更新之前的[系统用户实体]
     * @author KevenPotter
     * @date 2021-01-11 12:19:58
     */
    public SystemUserEntity updateSystemMenu(SystemUserDto systemUserDto) {
        if (null == systemUserDto) return null;
        SystemUserEntity systemUserEntity = systemUserDao.getSystemUserById(systemUserDto.getId());
        if (null == systemUserEntity) return null;
        systemUserDao.updateSystemUser(systemUserDto.setUserUpdateTime(LocalDateTime.now()));
        return systemUserDao.getSystemUserById(systemUserDto.getId());
    }
}
