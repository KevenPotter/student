package com.kevenpotter.student.service;

import com.kevenpotter.student.domain.dto.DashboardDto;
import com.kevenpotter.student.utils.ZKUtils;
import com.kevenpotter.student.zookeeper.watcher.OnlineUsersWatcher;
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

    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private SystemUserService systemUserService;

    @Value("${zookeeper.address}")
    private String zookeeperAddress;
    private ZooKeeper zooKeeper;

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
        dashboardDto.setTotalNumberOfVisits(0L);
        return dashboardDto;
    }

    /**
     * @return 返回创建好后的ZooKeeper用户路径
     * @throws IOException          抛出IO异常
     * @throws InterruptedException 抛出中断异常
     * @author KevenPotter
     * @date 2019-12-20 18:34:10
     * @description 此方法旨在创建ZooKeeper用户路径, 主要用途是当用户浏览网站时, 会相应的创建该用户的节点信息, 以进行网站在线人数
     * 的统计
     */
    public String updateUserCounts() throws IOException, InterruptedException {
        CountDownLatch connectedSemaphore = new CountDownLatch(1);
        // 连接zookeeper并且注册一个默认的监听器
        zooKeeper = new ZooKeeper(zookeeperAddress, 2000, new OnlineUsersWatcher(zooKeeper, connectedSemaphore, new Stat()));
        // 等待zk连接成功的通知
        connectedSemaphore.await();
        // 获取path目录节点的配置数据,并注册默认的监听器
        String path = ZKUtils.PATH = "/visions/user";
        return ZKUtils.createEphemeralSequentialNode(zooKeeper, path, "hello");
    }
}
