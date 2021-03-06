package curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CuratorSet {

    CuratorFramework client;

    @Before
    public void before() {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        // 创建连接对象
        client = CuratorFrameworkFactory
                .builder()
                // IP地址和端口号
                .connectString("10.5.1.131:2181")
                // 会话超时时间
                .sessionTimeoutMs(30000)
                // 重连机制
                .retryPolicy(retryPolicy)
                // 命名空间
                .namespace("set")
                // 构建对象
                .build();
        // 打开连接
        client.start();
    }

    @After
    public void after() {
        client.close();
    }

    @Test
    public void set1() throws Exception {
        // 更新节点
        client.setData()
                .forPath("/node1", "node11".getBytes());
        System.out.println("结束");
    }

    @Test
    public void set2() throws Exception {
        client.setData()
                // 指定版本号
                .withVersion(-1)
                .forPath("/node1", "node111".getBytes());
        System.out.println("结束");
    }

    @Test
    public void set3() throws Exception {
        // 异步方式修改节点
        client.setData()
                .withVersion(-1)
                .inBackground((client, event) -> {
                    System.out.println(event.getPath());
                    System.out.println(event.getType());
                })
                .forPath("/node1", "node1".getBytes());
        Thread.sleep(5000);
        System.out.println("结束");
    }
}
