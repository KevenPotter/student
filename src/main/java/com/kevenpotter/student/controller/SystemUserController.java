package com.kevenpotter.student.controller;

import com.kevenpotter.student.domain.dto.SystemUserDto;
import com.kevenpotter.student.domain.entity.SystemUserEntity;
import com.kevenpotter.student.result.ApiConstant;
import com.kevenpotter.student.result.ApiResult;
import com.kevenpotter.student.service.SystemUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2019-12-12 11:21:08
 * @description 后台用户控制层类
 */
@CrossOrigin
@RestController
@RequestMapping("systemUser")
public class SystemUserController {

    /*定义日志记录器，用来记录必要信息*/
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SystemUserService systemUserService;

    /**
     * @param systemUserDto 后台用户数据传输类
     * @return 返回一个结果集
     * @author KevenPotter
     * @date 2019-12-12 11:25:50
     * @description 根据[用户编号]或[用户名称]返回[后台用户实体类]
     */
    @GetMapping("/systemUser")
    public ApiResult getSystemUser(@RequestBody SystemUserDto systemUserDto) {
        if (null == systemUserDto) return ApiResult.buildFailure(ApiConstant.CODE_1, "请求参数为空");
        SystemUserEntity systemUserEntity = systemUserService.getSystemUser(systemUserDto);
        if (null == systemUserEntity) return ApiResult.buildFailure(ApiConstant.CODE_2, "未获取到后台用户信息");
        return ApiResult.buildSuccess(systemUserEntity);
    }
}