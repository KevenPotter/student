package com.kevenpotter.student.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @GetMapping(value = "/")
    public String getIndexHtml() {
        return "index.html";
    }

    /**
     * @return
     * @author KevenPotter
     * @date 2019-12-12 16:09:37
     * @description
     */
    @GetMapping(value = "/login")
    public String getLoginHtml() {
        return "login.html";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseBody
    public String getPage1Html() {
        return "ROLE_ADMIN";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ROLE_USER')")
    @ResponseBody
    public String getPage2Html() {
        return "ROLE_USER";
    }
}
