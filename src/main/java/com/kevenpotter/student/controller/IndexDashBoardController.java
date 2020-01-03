package com.kevenpotter.student.controller;

import com.kevenpotter.student.config.WebSocketConfig;
import com.kevenpotter.student.service.IndexService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    /*定义日志记录器，用来记录必要信息*/
    private static Logger logger = LoggerFactory.getLogger(IndexDashBoardController.class);

    /*静态变量,用来记录当前在线连接数.应该把它设计成线程安全的.*/
    private static int onlineCount = 0;
    /*与某个客户端的连接会话列表,需要通过它来给客户端广播数据.*/
    private static CopyOnWriteArraySet<Session> sessionSet = new CopyOnWriteArraySet<Session>();

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
        IndexDashBoardController.addOnlineCount();
        sessionSet.add(session);
        IndexDashBoardController.broadcast();
    }

    /**
     * @param session session为与某个客户端的连接会话,需要通过它来给客户端发送数据
     * @author KevenPotter
     * @date 2019-12-24 10:22:01
     * @description 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session) {
        sessionSet.remove(session);
        IndexDashBoardController.subOnlineCount();
        IndexDashBoardController.broadcast();
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

    /**
     * @author KevenPotter
     * @date 2020-01-01 00:00:01
     * @description 对信息进行广播, 也就是更新首页的visits(在线浏览)信息
     */
    private static void broadcast() {
        if (sessionSet.size() == 0) return;
        try {
            for (Session session : sessionSet) {
                session.getBasicRemote().sendText(String.valueOf(IndexDashBoardController.getOnlineCount()));
            }
        } catch (IOException e) {
            logger.error("在线浏览广播发生错误. 请检查 [IndexDashBoardController.java] 代码.");
            e.printStackTrace();
        }
    }
}
