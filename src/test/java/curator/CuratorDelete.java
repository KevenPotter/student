package curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CuratorDelete {

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
                .namespace("delete")
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
    public void delete1() throws Exception {
        // 删除节点
        client.delete()
                // 节点的路径
                .forPath("/node1");
        System.out.println("结束");
    }

    @Test
    public void delete2() throws Exception {
        client.delete()
                // 指定版本号
                .withVersion(-1)
                .forPath("/node1");
        System.out.println("结束");
    }

    @Test
    public void delete3() throws Exception {
        // 删除包含子节点的节点
        client.delete()
                .deletingChildrenIfNeeded()
                .withVersion(-1)
                .forPath("/node1");
        System.out.println("结束");
    }

    @Test
    public void delete4() throws Exception {
        // 异步方式删除节点
        client.delete()
                .deletingChildrenIfNeeded()
                .withVersion(-1)
                .inBackground((client, event) -> {
                    System.out.println(event.getPath());
                    System.out.println(event.getType());
                })
                .forPath("/node1");
        Thread.sleep(5000);
        System.out.println("结束");
    }
}
