package com.kevenpotter.student.realm;

import com.kevenpotter.student.dao.SystemUserDao;
import com.kevenpotter.student.domain.entity.SystemUserEntity;
import com.kevenpotter.student.utils.AccountVerification;
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
    private SystemUserDao systemUserDao;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String principal = (String) authenticationToken.getPrincipal();
        SystemUserEntity systemUserEntity = null;
        if (AccountVerification.isStudentNo(principal)) systemUserEntity = systemUserDao.getSystemUserByUserId(Long.valueOf(principal));
        if (AccountVerification.isEmail(principal)) systemUserEntity = systemUserDao.getSystemUserByEmail(principal);
        if (AccountVerification.isMobile(principal)) systemUserEntity = systemUserDao.getSystemUserByMobile(Long.valueOf(principal));
        if (AccountVerification.isNickname(principal)) systemUserEntity = systemUserDao.getSystemUserByNickname(principal);
        if (systemUserEntity != null)
            return new SimpleAuthenticationInfo(systemUserEntity.getUserId(), systemUserEntity.getUserPassword(), ByteSource.Util.bytes(SaltUtils.getDecodeSaltValue(systemUserEntity.getSalt())), this.getName());
        return null;
    }
}
