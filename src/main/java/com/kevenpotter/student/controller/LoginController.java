package com.kevenpotter.student.controller;

import com.kevenpotter.student.result.ApiConstant;
import com.kevenpotter.student.result.ApiResult;
import com.kevenpotter.student.utils.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

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
@RestController
@RequestMapping("/")
public class LoginController {

    /*定义日志记录器，用来记录必要信息*/
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 登录操作
     *
     * @param username 用户名
     * @param password 密码
     * @return 返回登录是否成功状态
     * @author KevenPotter
     * @date 2020-12-23 11:56:18
     */
    @PostMapping("/login")
    @ResponseBody
    public ApiResult login(@RequestParam("username") String username, @RequestParam("password") String password) {
        if (StringUtils.isEmpty(username, password)) return ApiResult.buildFailure(ApiConstant.CODE_1, "请求参数为空");
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(new UsernamePasswordToken(username, password));
            return ApiResult.buildSuccess();
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            System.out.println("用户名错误");
        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            System.out.println("密码错误");
        }
        return ApiResult.buildFailure(ApiConstant.CODE_2, "未获取到用户信息");
    }

    /**
     * 退出登录操作
     *
     * @return 返回退出登录是否成功状态
     * @author KevenPotter
     * @date 2020-12-23 14:22:19
     */
    @GetMapping("/logout")
    @ResponseBody
    public ApiResult logout() {
        SecurityUtils.getSubject().logout();
        return ApiResult.buildSuccess();
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
