package com.kevenpotter.student.config;

import com.kevenpotter.student.realm.CustomerRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * Redis配置器
 *
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2020-12-27 20:46:59
 */
@Configuration
public class RedisConfig {

    @Bean(name = "redisPool")
    public JedisPool jedisPool(
            @Qualifier("redis.pool.config") JedisPoolConfig jedisPoolConfig,
            @Value("${redis.host}") String host,
            @Value("${redis.port}") int port,
            @Value("${redis.timeout}") int timeout,
            @Value("${redis.password}") String password
    ) {
        return new JedisPool(jedisPoolConfig, host, port, timeout, password);
    }

    @Bean(name = "redis.pool.config")
    public JedisPoolConfig jedisPoolConfig(
            @Value("${redis.pool.min-idle}") int minIdle,
            @Value("${redis.pool.max-idle}") int maxIdle,
            @Value("${redis.pool.max-wait}") int maxWaitMills,
            @Value("${redis.block-when-exhausted}") boolean blockWhenExhausted,
            @Value("${redis.pool.max-wait}") int maxTotal
    ) {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMinIdle(minIdle);
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMills);
        jedisPoolConfig.setMaxTotal(maxTotal);
        jedisPoolConfig.setBlockWhenExhausted(blockWhenExhausted);// 连接耗尽时是否阻塞，false报异常，true阻塞直到超时，默认true
        jedisPoolConfig.setJmxEnabled(true);// 是否启用pool的JMX管理功能，默认true
        return jedisPoolConfig;
    }

}
