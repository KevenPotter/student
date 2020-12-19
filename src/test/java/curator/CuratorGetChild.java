package curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.data.Stat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class CuratorGetChild {

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
    public void getChild1() throws Exception {
        // 读取子节点数据
        List<String> list = client.getChildren()
                // 节点的路径
                .forPath("/get");
        for (String string : list) {
            System.out.println(string);
        }
    }

    @Test
    public void getChild2() throws Exception {
        // 异步方式读取节点数据
        client.getChildren()
                .inBackground((client, event) -> {
                    System.out.println(event);
                })
                .forPath("/get");
        Thread.sleep(5000);
        System.out.println("结束");
    }
}
