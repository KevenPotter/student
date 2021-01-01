package com.kevenpotter.student.config;

import com.kevenpotter.student.realm.CustomerRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Shiro配置器
 *
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2020-12-22 13:52:58
 */
@Configuration
public class ShiroConfig {

    /*受限资源*/
    private static Map<String, String> RESTRICTED_RESOURCES = new HashMap<String, String>() {{
        put("/register.html", "anon");
        put("/register-success.html", "anon");
        put("/swagger-ui.html", "anon");
        put("/**.html", "authc");
        put("/**/**.html", "authc");
    }};

    /*公共资源*/
    private static Map<String, String> PUBLIC_RESOURCE = new HashMap<String, String>() {{
        put("/login.html.", "authc");
    }};

    /**
     * 1.创建ShiroFilter，负责拦截所有请求
     *
     * @param defaultWebSecurityManager 安全管理器
     * @return 返回创建好的ShiroFilter，负责拦截所有请求
     * @date 2020-12-22 16:37:48
     * @author KevenPotter
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        // 配置系统受限资源
        shiroFilterFactoryBean.setFilterChainDefinitionMap(RESTRICTED_RESOURCES);
        // 配置系统公共资源
        // 配置默认认证界面路径
        shiroFilterFactoryBean.setLoginUrl("/login.html");
        return shiroFilterFactoryBean;
    }

    /**
     * 2.创建安全管理器
     *
     * @param realm 领域对象
     * @return 返回创建的带有WEB功能的安全管理器
     * @date 2020-12-22 16:39:22
     * @author KevenPotter
     */
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(Realm realm) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(realm);
        return defaultWebSecurityManager;
    }

    /**
     * 3.创建自定义领域对象realm
     *
     * @return 返回创建的自定义领域对象realm
     * @date 2020-12-22 16:40:23
     * @author KevenPotter
     */
    @Bean
    public Realm getRealm() {
        CustomerRealm customerRealm = new CustomerRealm();
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();   // 修改凭证校验匹配器
        credentialsMatcher.setHashAlgorithmName("MD5");                                 // 设置加密算法位MD5
        credentialsMatcher.setHashIterations(1024);                                     // 设置散列次数
        customerRealm.setCredentialsMatcher(credentialsMatcher);
        return customerRealm;
    }
}
