package com.kevenpotter.student.utils.zkLock;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class MyLock {

    // zk连接地址
    String IP = "10.5.1.131";
    // 计数器对象
    CountDownLatch countDownLatch = new CountDownLatch(1);
    // 基本配置信息
    ZooKeeper zooKeeper;
    private static final String LOCK_ROOT_PATH = "/Locks";
    private static final String LOCK_NODE_NAME = "Lock_";
    private String lockPath;

    // 打开Zookeeper连接
    public MyLock() {
        try {
            zooKeeper = new ZooKeeper(IP, 5000, new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    if (event.getType() == Event.EventType.None) {
                        if (event.getState() == Event.KeeperState.SyncConnected) {
                            System.out.println("连接成功");
                            countDownLatch.countDown();
                        }
                    }
                }
            });
            countDownLatch.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 获取锁
    public void acquireLock() throws Exception {
        // 创建锁节点
        createLock();
        // 尝试获取锁
        attemptLock();
    }

    // 创建锁节点
    private void createLock() throws Exception {
        // 判断Locks是否存在,不存在则创建
        Stat stat = zooKeeper.exists(LOCK_ROOT_PATH, false);
        if (stat == null) {
            zooKeeper.create(LOCK_ROOT_PATH, new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }
        // 创建临时有序节点
        lockPath = zooKeeper.create(LOCK_ROOT_PATH + "/" + LOCK_NODE_NAME, new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println("节点创建成功: " + lockPath);

    }

    // 创建Watcher监听器对象,监视上一个节点是否被删除
    Watcher watcher = event -> {
        if (event.getType() == Watcher.Event.EventType.NodeDeleted) {
            synchronized (this) {
                this.notifyAll();
            }
        }
    };

    // 尝试获取锁
    private void attemptLock() throws Exception {
        // 获取Locks节点下的所有子节点
        List<String> children = zooKeeper.getChildren(LOCK_ROOT_PATH, false);
        // 对子节点进行排序
        Collections.sort(children);
        // /Locks/Lock_0000000001
        int index = children.indexOf(lockPath.substring(LOCK_ROOT_PATH.length() + 1));
        if (index == 0) {
            System.out.println("获取锁成功");
            return;
        } else {
            // 获取上一个节点的路径
            String path = children.get(index - 1);
            Stat stat = zooKeeper.exists(LOCK_ROOT_PATH + "/" + path, watcher);
            // 这里判空的原因是因为执行完上两句代码的同时,可能我们上一条临时有序节点(分布式锁)已经执行完代码删除了节点,所以就会造成上一个节点为空的现象,这个时候我们
            // 当前的这个线程重新尝试获取锁(attemptLock)即可
            if (stat == null) {
                attemptLock();
            } else {
                synchronized (watcher) {
                    watcher.wait();
                }
                attemptLock();
            }
        }
    }

    // 释放锁
    public void releaseLock() throws Exception {
        // 删除临时有序节点
        zooKeeper.delete(this.lockPath, -1);
        zooKeeper.close();
        System.out.println("所已经释放" + this.lockPath);
    }

    public static void main(String[] args) throws Exception {
        MyLock myLock = new MyLock();
        myLock.createLock();
    }
}
