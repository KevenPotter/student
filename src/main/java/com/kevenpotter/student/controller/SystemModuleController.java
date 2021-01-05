package com.kevenpotter.student.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kevenpotter.student.domain.dto.SystemModuleDto;
import com.kevenpotter.student.domain.entity.SystemModuleEntity;
import com.kevenpotter.student.result.ApiConstant;
import com.kevenpotter.student.result.ApiResult;
import com.kevenpotter.student.service.SystemModuleService;
import com.kevenpotter.student.utils.ListUtils;
import com.kevenpotter.student.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * 系统模块控制层类
 *
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2021-01-04 23:17:37
 */
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
@RestController
@RequestMapping("/module")
public class SystemModuleController {

    /*定义日志记录器，用来记录必要信息*/
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SystemModuleService systemModuleService;

    /**
     * 获取[系统模块实体类]
     *
     * @param menuId       菜单编号
     * @param moduleName   模块名称
     * @param moduleStatus 模块状态
     * @param pageNo       当前页码
     * @param pageSize     分页大小
     * @return 返回一个结果集
     * @author KevenPotter
     * @date 2021-01-04 23:19:30
     */
    @ResponseBody
    @GetMapping("/modules")
    public ApiResult getModules(
            @RequestParam(value = "menuId", required = false) Long menuId,
            @RequestParam(value = "moduleName", required = false) String moduleName,
            @RequestParam(value = "moduleStatus", required = false) Integer moduleStatus,
            @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        PageHelper.startPage(pageNo, pageSize);
        PageInfo<SystemModuleEntity> pageInfo = new PageInfo<SystemModuleEntity>(systemModuleService.getModules(menuId, moduleName, moduleStatus));
        if (ListUtils.isEmpty(pageInfo.getList())) return ApiResult.buildFailure(ApiConstant.CODE_3, "未获取到模块信息");
        return ApiResult.buildSuccess(pageInfo);
    }

    /**
     * 获取所有[全部系统模块数据传输类]
     *
     * @return 返回所有[全部系统模块数据传输类]
     * @author KevenPotter
     * @date 2021-01-05 13:28:38
     */
    @ResponseBody
    @GetMapping("/all/modules")
    public ApiResult getAllModules() {
        return ApiResult.buildSuccess(systemModuleService.getAllModules());
    }

    /**
     * 插入一条新的[系统模块实体]并返回该[系统模块实体]
     *
     * @param systemModuleDto 系统模块数据传输类
     * @return 返回一个结果集
     * @author KevenPotter
     * @date 2021-01-04 23:20:10
     */
    @ResponseBody
    @PostMapping("/modules")
    public ApiResult addModule(@RequestBody SystemModuleDto systemModuleDto) {
        if (null == systemModuleDto) return ApiResult.buildFailure(ApiConstant.CODE_1, "请求参数为空");
        SystemModuleEntity systemModuleEntity = systemModuleService.addSystemModule(systemModuleDto);
        if (null == systemModuleEntity) return ApiResult.buildFailure(ApiConstant.CODE_4, "该模块已存在");
        return ApiResult.buildSuccess(systemModuleEntity);
    }

    /**
     * 更新[系统模块实体]并返回更新之前的[系统模块实体]
     *
     * @param systemModuleDto 系统模块数据传输类
     * @return 返回一个结果集
     * @author KevenPotter
     * @date 2021-01-04 23:21:27
     */
    @ResponseBody
    @PutMapping("/modules")
    public ApiResult updateModule(@RequestBody SystemModuleDto systemModuleDto) {
        if (null == systemModuleDto) return ApiResult.buildFailure(ApiConstant.CODE_1, "请求参数为空");
        SystemModuleEntity systemModuleEntity = systemModuleService.updateSystemModule(systemModuleDto);
        if (null == systemModuleEntity) return ApiResult.buildFailure(ApiConstant.CODE_3, "未成功更新系统模块信息,系统模块信息可能不存在");
        return ApiResult.buildSuccess(systemModuleEntity);
    }

    /**
     * 根据[模块名称]返回[系统模块实体]
     *
     * @param moduleName 角色名称
     * @return 返回一个结果集
     * @author KevenPotter
     * @date 2021-01-04 23:23:08
     */
    @ResponseBody
    @GetMapping("/moduleNa/{moduleName}")
    public ApiResult getSystemModuleByModuleName(@PathVariable String moduleName) throws UnsupportedEncodingException {
        if (StringUtils.isEmpty(moduleName)) return ApiResult.buildFailure(ApiConstant.CODE_1, "[模块名称]为空");
        moduleName = URLDecoder.decode(moduleName, "utf-8").trim();
        SystemModuleEntity systemModuleEntity = systemModuleService.getModuleByModuleName(moduleName);
        if (null == systemModuleEntity) return ApiResult.buildFailure(ApiConstant.CODE_3, "未获取到系统模块名称信息");
        return ApiResult.buildSuccess(systemModuleEntity);
    }
}
