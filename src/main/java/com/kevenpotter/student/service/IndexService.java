package com.kevenpotter.student.service;

import com.kevenpotter.student.domain.dto.DashboardDto;
import com.kevenpotter.student.utils.ZKUtils;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2019-12-12 15:46:18
 * @description 教师服务层类
 */
@Service
@ResponseBody
public class IndexService {

    /*定义日志记录器，用来记录必要信息*/
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);
    private static ZooKeeper zk = null;
    private static Stat stat = new Stat();

    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private SystemUserService systemUserService;

    @Value("${zookeeper.address}")
    private String zookeeperAddress;

    /**
     * @author KevenPotter
     * @date 2019-12-20 16:57:08
     * @description 返回首页的仪表盘展示数据
     */
    public DashboardDto getDashBoard() {
        DashboardDto dashboardDto = new DashboardDto();
        dashboardDto.setTotalNumberOfStudents(studentService.getTheTotalNumberOfStudents());
        dashboardDto.setTotalNumberOfTeachers(teacherService.getTheTotalNumberOfTeachers());
        dashboardDto.setTotalNumberOfAccounts(systemUserService.getTheTotalNumberOfAccounts());
        dashboardDto.setTotalNumberOfVisits(111L);
        return dashboardDto;
    }

    public String updateUserCounts() throws IOException, InterruptedException {
        // 连接zookeeper并且注册一个默认的监听器
        zk = new ZooKeeper(zookeeperAddress, Integer.MAX_VALUE, new Visits());
        // 等待zk连接成功的通知
        connectedSemaphore.await();
        // 获取path目录节点的配置数据,并注册默认的监听器
        String path = ZKUtils.PATH = "/visions/user";
        return ZKUtils.createEphemeralSequentialNode(zk, path, "hello");
    }

    public class Visits implements Watcher {

        @Override
        public void process(WatchedEvent watchedEvent) {
            if (Event.KeeperState.SyncConnected == watchedEvent.getState()) { // zk连接成功通知事件
                System.out.println("连接成功");
                if (Event.EventType.None == watchedEvent.getType() && null == watchedEvent.getPath()) {
                    connectedSemaphore.countDown();
                } else if (watchedEvent.getType() == Event.EventType.NodeDataChanged) { // zk目录节点数据编号通知事件
                    try {
                        System.out.println("配置已修改，新值为：" + new String(zk.getData(watchedEvent.getPath(), true, stat)));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
