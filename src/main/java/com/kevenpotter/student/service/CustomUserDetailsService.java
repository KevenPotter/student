package com.kevenpotter.student.service;

import com.kevenpotter.student.domain.dto.SystemUserDto;
import com.kevenpotter.student.domain.entity.SystemRoleEntity;
import com.kevenpotter.student.domain.entity.SystemUserEntity;
import com.kevenpotter.student.domain.entity.SystemUserRoleEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author KevenPotter
 * @company https://github.com/KevenPotter/student
 * @date 2019-12-12 16:47:33
 * @description 用户认证服务层类
 */
@Service(value = "userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    /*定义日志记录器，用来记录必要信息*/
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SystemUserService systemUserService;
    @Autowired
    private SystemRoleService systemRoleService;
    @Autowired
    private SystemUserRoleService systemUserRoleService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        List<GrantedAuthority> authorityList = new ArrayList<GrantedAuthority>();
        SystemUserDto systemUserDto = new SystemUserDto();
        SystemUserEntity systemUserEntity = systemUserService.getSystemUser(systemUserDto);
        if (null == systemUserEntity) throw new UsernameNotFoundException("该用户不存在");
        List<SystemUserRoleEntity> systemUserRoleEntityList = systemUserRoleService.getSystemUserRoleByUserId(systemUserEntity.getUserId());
        for (SystemUserRoleEntity systemUserRoleEntity : systemUserRoleEntityList) {
            SystemRoleEntity systemRoleEntity = systemRoleService.getSystemRoleByRoleId(systemUserRoleEntity.getRoleId());
            authorityList.add(new SimpleGrantedAuthority(systemRoleEntity.getRoleName()));
        }
        return new User(systemUserEntity.getUserName(), systemUserEntity.getUserPassword(), authorityList);
    }
}
