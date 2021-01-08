package com.kevenpotter.student.controller;

import com.kevenpotter.student.domain.dto.SystemRoleMenuModulePermissionDto;
import com.kevenpotter.student.domain.entity.SystemRoleMenuModulePermissionEntity;
import com.kevenpotter.student.result.ApiConstant;
import com.kevenpotter.student.result.ApiResult;
import com.kevenpotter.student.service.SystemRoleMenuModulePermissionService;
import com.kevenpotter.student.utils.ListUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 系统角色控制层类
 *
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2021-01-03 11:25:36
 */
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
@RestController
@RequestMapping("/roleMenuModulePermission")
public class SystemRoleMenuModulePermissionController {

    /*定义日志记录器，用来记录必要信息*/
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SystemRoleMenuModulePermissionService systemRoleMenuModulePermissionService;

    /**
     * 根据[角色编号]查询[全部系统角色-菜单-模块-权限数据传输类]
     *
     * @param roleId 角色编号
     * @return 返回一个结果集
     * @author KevenPotter
     * @date 2021-01-06 10:36:13
     */
    @ResponseBody
    @GetMapping("/roleMenuModulePermissions/{roleId}")
    public ApiResult getSystemRoleMenuModulePermissionByRoleId(@PathVariable Long roleId) {
        if (null == roleId) return ApiResult.buildFailure(ApiConstant.CODE_1, "请求参数为空");
        List<SystemRoleMenuModulePermissionDto> systemRoleMenuModulePermissionDtoList = systemRoleMenuModulePermissionService.getSystemRoleMenuModulePermissionByRoleId(roleId);
        if (ListUtils.isEmpty(systemRoleMenuModulePermissionDtoList)) return ApiResult.buildFailure(ApiConstant.CODE_3, "未成功获取系统角色-菜单-模块-权限信息");
        return ApiResult.buildSuccess(systemRoleMenuModulePermissionDtoList);
    }

    /**
     * 插入新的[系统角色-菜单-模块-权限实体]并返回该[系统角色-菜单-模块-权限实体]
     *
     * @param systemRoleMenuModulePermissions 系统角色-菜单-模块-权限数据传输类集合
     * @return 返回一个结果集
     * @author KevenPotter
     * @date 2021-01-06 14:43:00
     */
    @ResponseBody
    @PostMapping("/roleMenuModulePermissions")
    public ApiResult addRoleMenuModulePermissions(@RequestBody List<SystemRoleMenuModulePermissionDto> systemRoleMenuModulePermissions) {
        if (ListUtils.isEmpty(systemRoleMenuModulePermissions)) return ApiResult.buildFailure(ApiConstant.CODE_1, "请求参数为空");
        systemRoleMenuModulePermissionService.addSystemRoleMenuModulePermission(systemRoleMenuModulePermissions);
        return ApiResult.buildSuccess();
    }
}
