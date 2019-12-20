package com.kevenpotter.student.controller;

import com.kevenpotter.student.result.ApiResult;
import com.kevenpotter.student.service.IndexService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2019-12-20 16:34:58
 * @description 首页控制层类
 */
@CrossOrigin
@RestController
@RequestMapping("/index")
public class IndexController {

    /*定义日志记录器，用来记录必要信息*/
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IndexService indexService;

    /**
     * @return 返回一个结果集
     * @author KevenPotter
     * @date 2019-12-20 16:29:09
     * @description 返回教师记录总条数
     */
    @GetMapping("/counts")
    public ApiResult getDashBoard() {
        indexService.getDashBoard();
        return null;
    }
}