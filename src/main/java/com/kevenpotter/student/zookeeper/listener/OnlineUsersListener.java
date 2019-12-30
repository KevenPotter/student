package com.kevenpotter.student.zookeeper.listener;

import com.kevenpotter.student.utils.PropertyUtils;
import com.kevenpotter.student.utils.StringUtils;
import com.kevenpotter.student.utils.ZKUtils;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

/**
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2019-12-30 09:12:19
 * @description 在线用户监听器
 */
public class OnlineUsersListener {

    /*定义日志记录器，用来记录必要信息*/
    private static Logger logger = LoggerFactory.getLogger(OnlineUsersListener.class);

    /*ZooKeeper客户端*/
    private static ZooKeeper zooKeeper;
    /*ZooKeeper集群地址*/
    private static String zookeeperAddress = PropertyUtils.getPropertyValue("zookeeper.address");

    /**
     * @author KevenPotter
     * @date 2019-12-30 10:31:27
     * @description 进行在线人数的监控
     */
    public static void startMonitorOnlineUsers() {
        OnlineUsersListener onlineUsersListener = new OnlineUsersListener();
        onlineUsersListener.initZookeeper();
        onlineUsersListener.monitorOnlineUsers();
        onlineUsersListener.business();
    }

    /**
     * @author KevenPotter
     * @date 2019-12-30 09:01:58
     * @description 初始化ZooKeeper连接
     */
    public static void initZookeeper() {
        try {
            if (StringUtils.isEmpty(zookeeperAddress)) {
                logger.error("连接初始化失败. ZooKeeper集群地址为空,请检查 [OnlineUsersListener.java] 文件的配置.");
                return;
            }
            zooKeeper = new ZooKeeper(zookeeperAddress, 2000, event -> monitorOnlineUsers());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @author KevenPotter
     * @date 2019-12-30 09:33:37
     * @description 监控在线用户数
     */
    public static void monitorOnlineUsers() {
        String path = "/visions";
        try {
            List<String> visions = zooKeeper.getChildren(path, true);
            for (String vision : visions) {
                byte[] data = zooKeeper.getData(path + "/" + vision, false, new Stat());
            }
            logger.info("当前在线人数: " + visions.size());
        } catch (KeeperException e) {
            logger.warn("Negligible Exception - KeeperException: {} 节点不存在", path);
            ZKUtils.PATH = path;
            ZKUtils.createPersistentNode(zooKeeper, path, null);
            logger.warn("Negligible Exception - KeeperException: 现重新搜索 {} 节点......", path);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * @author KevenPotter
     * @date 2019-12-30 09:45:45
     * @description 让Zookeeper监听线程处于持续等待状态
     */
    public static void business() {
        try {
            Thread.sleep(Long.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
