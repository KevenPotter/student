package com.kevenpotter.student.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2019-12-11 16:07:40
 * @description 登录控制层类
 */
@CrossOrigin
@Controller
public class LoginController {

    /*定义日志记录器，用来记录必要信息*/
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/login")
    public String showLogin() {
        return "login.html";
    }

    @RequestMapping(value = "/login/error")
    public void showError(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
            httpServletResponse.sendRedirect("http://localhost:8081/error.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
