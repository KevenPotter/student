package com.kevenpotter.student.controller;

import com.kevenpotter.student.config.WebSocketConfig;
import com.kevenpotter.student.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
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
        System.out.println("sessionId: " + session.getId());
        webSocketSet.add(this);
        IndexDashBoardController.addOnlineCount();
        try {
            session.getBasicRemote().sendText(String.valueOf(IndexDashBoardController.getOnlineCount()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnClose
    public void onClose() {
        webSocketSet.remove(this);
        IndexDashBoardController.subOnlineCount();
    }

    /**
     * @return 返回当前WebSocket连接数
     * @author KevenPotter
     * @date 2019-12-31 15:53:41
     * @description 获取当前WebSocket连接数, 也就是浏览网站的用户数
     */
    private static synchronized int getOnlineCount() {
        return IndexDashBoardController.onlineCount;
    }

    /**
     * @author KevenPotter
     * @date 2019-12-31 15:55:27
     * @description 对当前的WebSocket连接数进行增加, 也就是增加浏览网站的用户数
     */
    private static synchronized void addOnlineCount() {
        IndexDashBoardController.onlineCount++;
    }

    /**
     * @author KevenPotter
     * @date 2019-12-31 15:59:01
     * @description 对当前的WebSocket连接数进行减少, 也就是减少浏览网站的用户数
     */
    private static synchronized void subOnlineCount() {
        IndexDashBoardController.onlineCount--;
    }
}
