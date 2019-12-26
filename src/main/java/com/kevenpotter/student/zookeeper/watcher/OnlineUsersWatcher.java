package com.kevenpotter.student.zookeeper.watcher;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

/**
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2019-12-26 16:12:14
 * @description ZooKeeper在线人数观察者类
 */
public class OnlineUsersWatcher implements Watcher {

    /*定义日志记录器，用来记录必要信息*/
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private CountDownLatch connectedSemaphore;
    private Stat stat;

    public OnlineUsersWatcher(CountDownLatch connectedSemaphore, Stat stat) {
        this.connectedSemaphore = connectedSemaphore;
        this.stat = stat;
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        if (Event.KeeperState.SyncConnected == watchedEvent.getState()) {
            logger.info("用户已连接......");
            if (Event.EventType.None == watchedEvent.getType() && null == watchedEvent.getPath()) {
                connectedSemaphore.countDown();
            } else if (watchedEvent.getType() == Event.EventType.NodeDeleted) {
                logger.info("用户已退出......");
            } else if (watchedEvent.getType() == Event.EventType.NodeDeleted) {
                logger.info("用户数据被修改....");
            }
        }
    }
}
