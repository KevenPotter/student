package com.kevenpotter.student.controller;

import com.kevenpotter.student.config.WebSocketConfig;
import com.kevenpotter.student.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2019-12-23 16:16:56
 * @description 首页仪表盘控制层类
 */
@ServerEndpoint(value = "/dashboard", configurator = WebSocketConfig.MySpringConfigurator.class)
@RestController
public class IndexDashBoardController {

    /*静态变量,用来记录当前在线连接数.应该把它设计成线程安全的.*/
    private static int onlineCount = 0;
    /*concurrent包的线程安全Set,用来存放每个客户端对应的MyWebSocket对象.若要实现服务端与单一客户端通信的话,可以使用Map来存放,其中Key可以为用户标识.*/
    private static CopyOnWriteArraySet<IndexDashBoardController> webSocketSet = new CopyOnWriteArraySet<IndexDashBoardController>();
    /*与某个客户端的连接会话,需要通过它来给客户端发送数据.*/
    private Session session;

    private static IndexService indexService;

    @Autowired
    public void setIndexService(IndexService indexService) {
        IndexDashBoardController.indexService = indexService;
    }

    /**
     * @param session session为与某个客户端的连接会话,需要通过它来给客户端发送数据
     * @author KevenPotter
     * @date 2019-12-24 09:06:58
     * @description 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSocketSet.add(this);
        addOnlineCount();
        try {
            indexService.updateUserCounts();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());
    }

    @OnClose
    public void onClose() {
        webSocketSet.remove(this);
        subOnlineCount();
        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("来自客户端的消息:" + message);
        for (IndexDashBoardController indexDashBoardController : webSocketSet) {
            try {
                indexDashBoardController.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }

    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        IndexDashBoardController.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        IndexDashBoardController.onlineCount--;
    }
}
