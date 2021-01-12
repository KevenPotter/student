package com.kevenpotter.student.controller;

import com.kevenpotter.student.domain.dto.SystemUserRoleDto;
import com.kevenpotter.student.domain.entity.SystemUserRoleEntity;
import com.kevenpotter.student.result.ApiConstant;
import com.kevenpotter.student.result.ApiResult;
import com.kevenpotter.student.service.SystemUserRoleService;
import com.kevenpotter.student.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * 系统用户-角色控制层类
 *
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2021-01-11 15:28:28
 */
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
@RestController
@RequestMapping("/userRole")
public class SystemUserRoleController {

    /*定义日志记录器，用来记录必要信息*/
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SystemUserRoleService systemUserRoleService;

    /**
     * 根据[用户编号]返回[系统用户角色实体]
     *
     * @param userId 用户编号
     * @return 返回一个结果集
     * @author KevenPotter
     * @date 2021-01-11 15:33:25
     */
    @ResponseBody
    @GetMapping("/userRole/{userId}")
    public ApiResult getSystemUserRoleByUserId(@PathVariable String userId) throws UnsupportedEncodingException {
        if (StringUtils.isEmpty(userId)) return ApiResult.buildFailure(ApiConstant.CODE_1, "[用户编号]为空");
        userId = URLDecoder.decode(userId, "utf-8").trim();
        SystemUserRoleEntity systemUserRoleEntity = systemUserRoleService.getSystemUserRoleByUserId(userId);
        if (null == systemUserRoleEntity) return ApiResult.buildFailure(ApiConstant.CODE_3, "未获取到系统用户角色名称信息");
        return ApiResult.buildSuccess(systemUserRoleEntity);
    }

    /**
     * 插入一条新的[系统用户角色实体]并返回该[系统用户角色实体]
     *
     * @param systemUserRoleDto 系统菜单数据传输类
     * @return 返回一个结果集
     * @author KevenPotter
     * @date 2021-01-11 16:17:14
     */
    @ResponseBody
    @PostMapping("/userRoles")
    public ApiResult addSystemUserRole(@RequestBody SystemUserRoleDto systemUserRoleDto) {
        if (null == systemUserRoleDto) return ApiResult.buildFailure(ApiConstant.CODE_1, "请求参数为空");
        SystemUserRoleEntity oldSystemUserRoleEntity = systemUserRoleService.getSystemUserRoleByUserId(systemUserRoleDto.getSystemUserId());
        if (oldSystemUserRoleEntity != null) {
            if (oldSystemUserRoleEntity.getSystemRoleId().equals(systemUserRoleDto.getSystemRoleId())) return ApiResult.buildFailure(ApiConstant.CODE_4, "重复的角色信息");
        }
        SystemUserRoleEntity systemUserRoleEntity = systemUserRoleService.addSystemUserRole(systemUserRoleDto.setUserRoleStatus(1));
        return ApiResult.buildSuccess(systemUserRoleEntity);
    }
}
