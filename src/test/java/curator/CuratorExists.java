package curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.data.Stat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CuratorExists {

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
    public void exists1() throws Exception {
        // 判断节点是否存在
        Stat stat = client.checkExists()
                // 节点路径
                .forPath("/node1");
        System.out.println(stat);
    }

    @Test
    public void exists2() throws Exception {
        // 异步方式判断节点是否存在
        Stat stat = client.checkExists()
                .inBackground((client, event) -> {
                    System.out.println(event.getType());
                    System.out.println(event.getPath());
                    System.out.println(event.getChildren());
                })
                // 节点路径
                .forPath("/node1");
        Thread.sleep(5000);
        System.out.println("结束");
    }
}
