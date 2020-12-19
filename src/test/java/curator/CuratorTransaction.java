package curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CuratorTransaction {

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
                .namespace("create")
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
    public void tra1() throws Exception {
        // 错误的示例
        client.create().forPath("/node1", "node1".getBytes());
        client.setData().forPath("/node2", "node2".getBytes());
    }

    @Test
    public void tra2() throws Exception {
        // 开启事务
        client.inTransaction()
                .create().forPath("/node1", "node1".getBytes())
                .and()
                .setData().forPath("/node2", "node2".getBytes())
                .and()
                .commit();
    }
}
