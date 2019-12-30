package com.kevenpotter.student.utils;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.apache.zookeeper.ZooDefs.Ids.OPEN_ACL_UNSAFE;

/**
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2019-12-24 11:18:15
 * @description ZooKeeper工具类
 */
public class ZKUtils {

    /*定义日志记录器，用来记录必要信息*/
    private static Logger logger = LoggerFactory.getLogger(ZKUtils.class);

    /*路径分隔符*/
    public static final String SPLITTER = "/";

    /*节点路径*/
    public static String PATH = "/";
    /*子节点路径*/
    public static String CHILDREN_PATH = "/";

    /**
     * @param zooKeeper ZooKeeper对象
     * @param path      节点路径
     * @param value     节点数据
     * @return 返回创建的节点路径
     * @author KevenPotter
     * @date 2019-12-24 11:35:47
     * @description 创建持久性无序节点并返回创建的节点路径
     */
    public static String createPersistentNode(ZooKeeper zooKeeper, String path, String value) {
        ensureParentNode(zooKeeper, path);
        return createNode(zooKeeper, path, value, CreateMode.PERSISTENT);
    }

    /**
     * @param zooKeeper ZooKeeper对象
     * @param path      节点路径
     * @param value     节点数据
     * @return 返回创建的节点路径
     * @author KevenPotter
     * @date 2019-12-24 11:40:26
     * @description 创建持久性有序节点并返回创建的节点路径
     */
    public static String createPersistentSequentialNode(ZooKeeper zooKeeper, String path, String value) {
        ensureParentNode(zooKeeper, path);
        return createNode(zooKeeper, path, value, CreateMode.PERSISTENT_SEQUENTIAL);
    }

    /**
     * @param zooKeeper ZooKeeper对象
     * @param path      节点路径
     * @param value     节点数据
     * @return 返回创建的节点路径
     * @author KevenPotter
     * @date 2019-12-24 13:09:13
     * @description 创建临时性无序节点并返回创建的节点路径
     */
    public static String createEphemeralNode(ZooKeeper zooKeeper, String path, String value) {
        ensureParentNode(zooKeeper, path);
        return createNode(zooKeeper, path, value, CreateMode.EPHEMERAL);
    }

    /**
     * @param zooKeeper ZooKeeper对象
     * @param path      节点路径
     * @param value     节点数据
     * @return 返回创建的节点路径
     * @author KevenPotter
     * @date 2019-12-24 13:12:38
     * @description 创建临时性有序节点并返回创建的节点路径
     */
    public static String createEphemeralSequentialNode(ZooKeeper zooKeeper, String path, String value) {
        ensureParentNode(zooKeeper, path);
        return createNode(zooKeeper, path, value, CreateMode.EPHEMERAL_SEQUENTIAL);
    }

    /**
     * @param zooKeeper  ZooKeeper对象
     * @param path       节点路径
     * @param value      节点数据
     * @param createMode 节点模式(持久无序节点、持久有序节点、临时无序节点、临时有序节点)
     * @return 返回创建的节点路径
     * @author KevenPotter
     * @date 2019-12-24 16:06:19
     * @description 创建节点并返回创建的节点路径
     */
    private static String createNode(ZooKeeper zooKeeper, String path, String value, CreateMode createMode) {
        if (StringUtils.isEmpty(value)) value = "null";
        try {
            if (ensureNode(zooKeeper, path)) return null;
            return zooKeeper.create(path, value.getBytes(), OPEN_ACL_UNSAFE, createMode);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param zooKeeper ZooKeeper对象
     * @param path      节点路径
     * @author KevenPotter
     * @date 2019-12-24 14:38:46
     * @description 检查父节点是否存在, 如果不存在则创建, 否则创建当前父节点的子节点
     */
    public static void ensureParentNode(ZooKeeper zooKeeper, String path) {
        if (ensureNode(zooKeeper, path)) {
            if (CHILDREN_PATH.equals(PATH)) {
                return;
            } else {
                createNode(zooKeeper, CHILDREN_PATH, null, CreateMode.PERSISTENT);
            }
            return;
        } else {
            CHILDREN_PATH = path;
            path = path.substring(0, path.lastIndexOf(SPLITTER));
            if (!path.contains(SPLITTER)) {
                path = SPLITTER;
            }
            createPersistentNode(zooKeeper, path, null);
        }
    }

    /**
     * @param zooKeeper ZooKeeper对象
     * @param path      节点路径
     * @author KevenPotter
     * @date 2019-12-24 14:24:14
     * @description 检查节点是否存在
     */
    public static boolean ensureNode(ZooKeeper zooKeeper, String path) {
        try {
            Stat stat = zooKeeper.exists(path, false);
            if (null == stat) {
                logger.debug("{} 节点不存在", path);
                return false;
            } else {
                logger.debug("{} 节点已存在", path);
                return true;
            }
        } catch (KeeperException e) {
            logger.warn("Negligible Exception - KeeperException: {} 节点不存在", path);
            return false;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }
}
