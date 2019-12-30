package com.kevenpotter.student.controller;

import com.kevenpotter.student.domain.dto.DashboardDto;
import com.kevenpotter.student.result.ApiConstant;
import com.kevenpotter.student.result.ApiResult;
import com.kevenpotter.student.service.IndexService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

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
     * @description 返回首页的仪表盘展示数据
     */
    @GetMapping("/counts")
    public ApiResult getDashBoard() {
        DashboardDto dashboardDto = indexService.getDashBoard();
        return ApiResult.buildSuccess(dashboardDto);
    }

    /**
     * @return 返回一个结果集
     * @author KevenPotter
     * @date 2019-12-20 17:02:22
     * @description
     */
    @GetMapping("/visits")
    public ApiResult updateUserCounts() {
        try {
            return ApiResult.buildSuccess(indexService.updateUserCounts());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ApiResult.buildFailure(ApiConstant.CODE_3, "未获取到用户登录记录");
    }
}
