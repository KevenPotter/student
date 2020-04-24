package curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.retry.RetryNTimes;
import org.apache.curator.retry.RetryOneTime;
import org.apache.curator.retry.RetryUntilElapsed;

public class CuratorConnection {

    public static void main(String[] args) {
        // session的重连策略
        RetryPolicy retryPolicy1 = new RetryOneTime(3000); // 3秒后重连一次,只重连一次
        RetryPolicy retryPolicy2 = new RetryNTimes(3, 3000); // 没3秒重连一次,重连3次
        RetryPolicy retryPolicy3 = new RetryUntilElapsed(10000, 3000); // 每3秒重连一次,总等待时间超过3秒后停止重连
        RetryPolicy retryPolicy4 = new ExponentialBackoffRetry(1000, 3); // baseSleepTimeMs * Math.max(1, random.nextInt(1 << (retryCount + 1)))
        // 创建连接对象
        CuratorFramework client = CuratorFrameworkFactory
                .builder()
                // IP地址和端口号
                .connectString("10.5.1.131:2181")
                // 会话超时时间
                .sessionTimeoutMs(5000)
                // 重连机制
                .retryPolicy(retryPolicy1)
                // 命名空间
                .namespace("create")
                // 构建对象
                .build();
        // 打开连接
        client.start();
        System.out.println(client.getState());
        // 关闭连接
        client.close();
    }
}
