package curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CuratorWatcher {

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
    public void exists1() throws Exception {
        // 监视某个节点的数据变化
        // arg1:连接对象 arg2:监听的节点路径
        final NodeCache nodeCache = new NodeCache(client, "/watcher1");
        // 启动监视器对象
        nodeCache.start();
        nodeCache.getListenable().addListener(
                // 节点变化时回调的方法
                () -> {
                    System.out.println(nodeCache.getCurrentData().getPath());
                    System.out.println(new String(nodeCache.getCurrentData().getData()));
                });
        Thread.sleep(100000);
        System.out.println("监听结束");
        // 关闭监视器对象
        nodeCache.close();
    }

    @Test
    public void exists2() throws Exception {
        // 监听子节点的变化
        // arg1:连接对象 arg2:监视的节点路径 arg3:事件中是否可以获取节点的数据
        PathChildrenCache pathChildrenCache = new PathChildrenCache(client, "/watcher1", true);
        pathChildrenCache.start();
        pathChildrenCache.getListenable().addListener(
                // 当子节点发生变化时,回调的方法
                (curatorFramework, pathChildrenCacheEvent) -> {
                    System.out.println(pathChildrenCacheEvent.getType());
                    System.out.println(pathChildrenCacheEvent.getData().getPath());
                    System.out.println(new String(pathChildrenCacheEvent.getData().getData()));
                });
        Thread.sleep(100000);
        System.out.println("结束");
        pathChildrenCache.close();
    }
}
