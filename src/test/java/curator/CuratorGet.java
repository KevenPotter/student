package curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.data.Stat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CuratorGet {

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
                .namespace("get")
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
    public void get1() throws Exception {
        // 读取节点数据
        byte[] bytes = client.getData()
                // 节点的路径
                .forPath("/node1");
        System.out.println(new String(bytes));
    }

    @Test
    public void get2() throws Exception {
        // 读取节点属性
        Stat stat = new Stat();
        client.getData()
                // 读取属性
                .storingStatIn(stat)
                .forPath("/node1");
        System.out.println(stat);
    }

    @Test
    public void get3() throws Exception {
        // 异步方式读取节点数据
        client.getData()
                .inBackground((client, event) -> {
                    System.out.println(event);
                })
                .forPath("/node1");
        Thread.sleep(5000);
        System.out.println("结束");
    }
}
