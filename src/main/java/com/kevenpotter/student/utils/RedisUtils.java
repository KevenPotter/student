package com.kevenpotter.student.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Redis工具类
 *
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2020-12-27 21:20:56
 */
@Component
public class RedisUtils {

    /*定义日志记录器，用来记录必要信息*/
    private static Logger logger = LoggerFactory.getLogger(RedisUtils.class);

    @Autowired
    private JedisPool jedisPool;

    /**
     * 添加字符串
     *
     * @param key     键名
     * @param value   值
     * @param timeOut 超时时间
     * @param db      所选数据库
     * @author KevenPotter
     * @date 2020-12-28 09:15:12
     */
    public void addString(String key, String value, int timeOut, int db) {
        if (!checkTheParameters(timeOut, db)) {
            logger.error("添加字符串失败，请检查[超时时间]和[所选数据库]属性。目前输入值——[超时时间]:{} [所选数据库]:{}", timeOut, db);
            return;
        }
        Jedis jedis = jedisPool.getResource();
        if (db >= 0 && db <= 255) jedis.select(db);
        jedis.set(key, value);
        if (timeOut > 0) jedis.expire(key, timeOut);
        jedis.close();
    }

    /**
     * 返回检查参数后的结果
     *
     * @param timeout 超时时间
     * @param db      所选数据库
     * @return 返回检查参数后的结果
     * @author KevenPotter
     * @date 2020-12-27 22:05:11
     */
    private boolean checkTheParameters(int timeout, int db) {
        return (db >= 0 && db <= 255) && timeout > -1;
    }
}
