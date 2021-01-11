package com.kevenpotter.student.dao;

import com.github.pagehelper.Page;
import com.kevenpotter.student.domain.dto.SystemUserDto;
import com.kevenpotter.student.domain.dto.SystemUserWithoutImportantInformationDto;
import com.kevenpotter.student.domain.entity.SystemUserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2019-12-11 21:15:26
 * @description 后台用户持久层类
 */
@Repository
@Mapper
public interface SystemUserDao {

    /**
     * 根据[用户编号]、[用户昵称]和[用户状态]返回[后台用户实体类]
     *
     * @param userId       用户编号
     * @param userNickname 用户昵称
     * @param userStatus   用户状态
     * @return 返回根据[用户编号]、[用户昵称]和[用户状态]返回[后台用户实体类]
     * @author KevenPotter
     * @date 2021-01-11 10:11:52
     */
    @Select("<script> " +
            "SELECT su.id,su.user_id,su.user_mobile,su.user_email,su.user_nickname,su.user_status FROM system_user su " +
            "<where> " +
            "<if test='userId != null'> " +
            "AND su.user_id LIKE CONCAT('%',#{userId},'%') " +
            "</if>" +
            "<if test='userNickname != null'> " +
            "AND su.user_nickname LIKE CONCAT('%',#{userNickname},'%') " +
            "</if>" +
            "<if test='userStatus != null'> " +
            "AND su.user_status = #{userStatus}  " +
            "</if> " +
            "</where>" +
            "</script>")
    Page<SystemUserWithoutImportantInformationDto> getSystemUsers(@Param("userId") Long userId, @Param("userNickname") String userNickname, @Param("userStatus") Integer userStatus);

    /**
     * @param systemUserDto 后台用户数据传输类
     * @author KevenPotter
     * @date 2019-12-19 10:19:50
     * @description 插入一条新的[后台用户实体]
     */
    void addSystemUser(@Param("systemUserDto") SystemUserDto systemUserDto);

    /**
     * @param id 主键ID
     * @return 根据[主键ID]返回[后台用户实体类]
     * @author KevenPotter
     * @date 2019-12-19 11:17:33
     * @description 根据[主键ID]返回[后台用户实体类]
     */
    SystemUserEntity getSystemUserById(@Param("id") Long id);

    /**
     * 根据[用户编号]返回[后台用户实体类]
     *
     * @param userId 用户编号
     * @return 根据[用户编号]返回[后台用户实体类]
     * @author KevenPotter
     * @date 2020-12-24 10:31:45
     */
    SystemUserEntity getSystemUserByUserId(@Param("userId") Long userId);

    /**
     * @param userNickname 用户昵称
     * @return 根据[用户昵称]返回[后台用户实体类]
     * @author KevenPotter
     * @date 2019-12-18 15:00:48
     * @description 根据[用户昵称]返回[后台用户实体类]
     */
    SystemUserEntity getSystemUserByNickname(@Param("userNickname") String userNickname);

    /**
     * @param userEmail 用户邮箱
     * @return 根据[用户邮箱]返回[后台用户实体类]
     * @author KevenPotter
     * @date 2019-12-18 15:03:32
     * @description 根据[用户邮箱]返回[后台用户实体类]
     */
    SystemUserEntity getSystemUserByEmail(@Param("userEmail") String userEmail);

    /**
     * @param userMobile 用户手机
     * @return 根据[用户手机]返回[后台用户实体类]
     * @author KevenPotter
     * @date 2019-12-18 15:24:56
     * @description 根据[用户手机]返回[后台用户实体类]
     */
    SystemUserEntity getSystemUserByMobile(@Param("userMobile") Long userMobile);

    /**
     * @return 返回账户记录总条数
     * @author KevenPotter
     * @date 2019-12-20 16:54:34
     * @description 返回账户记录总条数
     */
    @Select("SELECT COUNT(*) FROM system_user;")
    Long getCount();

    /**
     * 更新[系统用户实体]
     *
     * @param systemUserDto 系统用户数据传输类
     * @author KevenPotter
     * @date 2021-01-11 12:21:52
     */
    void updateSystemUser(@Param("systemUserDto") SystemUserDto systemUserDto);
}

