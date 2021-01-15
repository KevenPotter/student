package com.kevenpotter.student.controller;

import com.kevenpotter.student.domain.dto.DashboardDto;
import com.kevenpotter.student.domain.dto.StudentSexStatisticsDto;
import com.kevenpotter.student.result.ApiConstant;
import com.kevenpotter.student.result.ApiResult;
import com.kevenpotter.student.service.IndexService;
import com.kevenpotter.student.service.StudentService;
import com.kevenpotter.student.service.SystemMenuService;
import com.kevenpotter.student.service.SystemUserRoleService;
import com.kevenpotter.student.utils.ListUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * 首页控制层类
 *
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2019-12-20 16:34:58
 */
@CrossOrigin
@RestController
@RequestMapping("/index")
public class IndexController {

    /*定义日志记录器，用来记录必要信息*/
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IndexService indexService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private SystemMenuService systemMenuService;
    @Autowired
    private SystemUserRoleService systemUserRoleService;

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
     * @date 2020-01-02 14:00:45
     * @description 统计各系部男女学生人数并将其返回
     */
    @GetMapping("/sexStatistics")
    public ApiResult getSexStatisticsByDepartment() {
        List<StudentSexStatisticsDto> sexStatisticsDtoList = studentService.getSexStatisticsByDepartment();
        if (ListUtils.isEmpty(sexStatisticsDtoList)) return ApiResult.buildFailure(ApiConstant.CODE_3, "未获取到各系部男女人数信息");
        return ApiResult.buildSuccess(sexStatisticsDtoList);
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

    /**
     * 获取所有[首页全部系统菜单数据传输类]
     *
     * @return 返回所有[首页全部系统菜单数据传输类]
     * @author KevenPotter
     * @date 2021-01-05 13:43:34
     */
    @ResponseBody
    @GetMapping("/all/menus")
    public ApiResult getAllMenusForIndex() {
        return ApiResult.buildSuccess(systemMenuService.getAllMenusForIndex());
    }

    /**
     * 根据[系统用户编号]获取该用户的[系统用户权限数据传输类]
     *
     * @return 根据[系统用户编号]获取该用户的[系统用户权限数据传输类]
     * @author KevenPotter
     * @date 2021-01-15 10:31:30
     */
    @ResponseBody
    @GetMapping("/permissions/{systemUserId}")
    public ApiResult getSystemUserPermissionByUserId(@PathVariable String systemUserId) {
        return ApiResult.buildSuccess(systemUserRoleService.getSystemUserPermissionByUserId(systemUserId));
    }
}
