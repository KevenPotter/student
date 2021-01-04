package com.kevenpotter.student.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kevenpotter.student.domain.dto.SystemRoleDto;
import com.kevenpotter.student.domain.entity.SystemRoleEntity;
import com.kevenpotter.student.result.ApiConstant;
import com.kevenpotter.student.result.ApiResult;
import com.kevenpotter.student.service.SystemRoleService;
import com.kevenpotter.student.utils.ListUtils;
import com.kevenpotter.student.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * 系统角色控制层类
 *
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2021-01-03 11:25:36
 */
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
@RestController
@RequestMapping("/role")
public class SystemRoleController {

    /*定义日志记录器，用来记录必要信息*/
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SystemRoleService systemRoleService;

    /**
     * 获取[系统角色实体类]
     *
     * @param roleName   角色名称
     * @param roleStatus 角色状态
     * @param pageNo     当前页码
     * @param pageSize   分页大小
     * @return 返回一个结果集
     * @author KevenPotter
     * @date 2021-01-03 15:23:22
     */
    @ResponseBody
    @GetMapping("/roles")
    public ApiResult getRoles(
            @RequestParam(value = "roleName", required = false) String roleName,
            @RequestParam(value = "roleStatus", required = false) Integer roleStatus,
            @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        PageHelper.startPage(pageNo, pageSize);
        PageInfo<SystemRoleEntity> pageInfo = new PageInfo<SystemRoleEntity>(systemRoleService.getRoles(roleName, roleStatus));
        if (ListUtils.isEmpty(pageInfo.getList())) return ApiResult.buildFailure(ApiConstant.CODE_3, "未获取到角色信息");
        return ApiResult.buildSuccess(pageInfo);
    }

    /**
     * 插入一条新的[系统角色实体]并返回该[系统角色实体]
     *
     * @param systemRoleDto 系统角色数据传输类
     * @return 返回一个结果集
     * @author KevenPotter
     * @date 2021-01-04 09:22:36
     */
    @ResponseBody
    @PostMapping("/roles")
    public ApiResult addRole(@RequestBody SystemRoleDto systemRoleDto) {
        if (null == systemRoleDto) return ApiResult.buildFailure(ApiConstant.CODE_1, "请求参数为空");
        SystemRoleEntity systemRoleEntity = systemRoleService.addSystemRole(systemRoleDto);
        if (null == systemRoleEntity) return ApiResult.buildFailure(ApiConstant.CODE_4, "该角色已存在");
        return ApiResult.buildSuccess(systemRoleEntity);
    }

    /**
     * 更新[系统角色实体]并返回更新之前的[系统角色实体]
     *
     * @param systemRoleDto 系统角色数据传输类
     * @return 返回一个结果集
     * @author KevenPotter
     * @date 2021-01-04 09:37:36
     */
    @ResponseBody
    @PutMapping("/roles")
    public ApiResult updateRole(@RequestBody SystemRoleDto systemRoleDto) {
        if (null == systemRoleDto) return ApiResult.buildFailure(ApiConstant.CODE_1, "请求参数为空");
        SystemRoleEntity systemRoleEntity = systemRoleService.updateSystemRole(systemRoleDto);
        if (null == systemRoleEntity) return ApiResult.buildFailure(ApiConstant.CODE_3, "未成功更新系统角色信息,系统角色信息可能不存在");
        return ApiResult.buildSuccess(systemRoleEntity);
    }

    /**
     * 根据[角色名称]返回[系统角色实体]
     *
     * @param roleName 角色名称
     * @return 返回一个结果集
     * @author KevenPotter
     * @date 2021-01-04 10:05:02
     */
    @ResponseBody
    @GetMapping("/roleNa/{roleName}")
    public ApiResult getSystemRoleByRoleName(@PathVariable String roleName) throws UnsupportedEncodingException {
        if (StringUtils.isEmpty(roleName)) return ApiResult.buildFailure(ApiConstant.CODE_1, "[角色名称]为空");
        roleName = URLDecoder.decode(roleName, "utf-8").trim();
        SystemRoleEntity systemRoleEntity = systemRoleService.getRoleByRoleName(roleName);
        if (null == systemRoleEntity) return ApiResult.buildFailure(ApiConstant.CODE_3, "未获取到系统角色名称信息");
        return ApiResult.buildSuccess(systemRoleEntity);
    }
}
