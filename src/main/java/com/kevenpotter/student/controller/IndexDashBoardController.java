package com.kevenpotter.student.controller;

import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2019-12-23 16:16:56
 * @description 首页仪表盘控制层类
 */
@ServerEndpoint("/dashboard")
public class IndexDashBoardController {

    /*静态变量,用来记录当前在线连接数.应该把它设计成线程安全的.*/
    private static int onlineCount = 0;
    /*concurrent包的线程安全Set,用来存放每个客户端对应的MyWebSocket对象.若要实现服务端与单一客户端通信的话,可以使用Map来存放,其中Key可以为用户标识.*/
    private static CopyOnWriteArraySet<IndexDashBoardController> webSocketSet = new CopyOnWriteArraySet<IndexDashBoardController>();
    /*与某个客户端的连接会话,需要通过它来给客户端发送数据.*/
    private Session session;

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSocketSet.add(this);
    }
}
