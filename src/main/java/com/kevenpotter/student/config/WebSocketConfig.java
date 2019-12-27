package com.kevenpotter.student.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

/**
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2019-12-27 10:47:21
 * @description WebSocket配置器
 */
@Configuration
public class WebSocketConfig extends ServerEndpointConfig.Configurator {

    /**
     * @return 返回一个[服务器端点生成器], 本质是向Spring容器进行注入操作
     * @author KevenPotter
     * @date 2019-12-27 15:15:16
     * @description Spring Boot项目初始化时注入ServerEndpointExporter类(如果采用了外置Tomcat则无需注入此类).这里进行注入是
     * 因为WebSocket注册的bean是由其自身管理的,并没有托付给Spring容器,所以,这层操作是为了将WebSocket的bean管理权限交于Spring
     * 容器.
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
        super.modifyHandshake(sec, request, response);
    }

    /**
     * @author KevenPotter
     * @compony https://github.com/KevenPotter/student
     * @date 2019:12:27 13:26:01
     * @description 因为WebSocketConfig注册的bean是Spring容器自己管理的, 所以需要使用配置交于Spring容器管理
     */
    public static class MySpringConfigurator extends ServerEndpointConfig.Configurator implements ApplicationContextAware {

        /*JavaBean容器管理*/
        public static volatile BeanFactory context;

        @Override
        public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
            MySpringConfigurator.context = applicationContext;
        }
    }
}
