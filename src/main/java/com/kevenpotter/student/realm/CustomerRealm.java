package com.kevenpotter.student.realm;

import com.kevenpotter.student.domain.entity.SystemUserEntity;
import com.kevenpotter.student.service.SystemUserService;
import com.kevenpotter.student.utils.SaltUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomerRealm extends AuthorizingRealm {

    @Autowired
    private SystemUserService systemUserService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 获取身份信息
        String primaryPrincipal = (String) principalCollection.getPrimaryPrincipal();
        System.out.println(primaryPrincipal);
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String principal = (String) authenticationToken.getPrincipal();
        SystemUserEntity systemUserEntity = systemUserService.getSystemUser(principal);
        if (systemUserEntity != null)
            return new SimpleAuthenticationInfo(systemUserEntity.getUserId(), systemUserEntity.getUserPassword(), ByteSource.Util.bytes(SaltUtils.getDecodeSaltValue(systemUserEntity.getSalt())), this.getName());
        return null;
    }
}
