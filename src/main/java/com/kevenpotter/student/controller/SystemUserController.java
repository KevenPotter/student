package com.kevenpotter.student.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kevenpotter.student.domain.dto.SystemUserDto;
import com.kevenpotter.student.domain.dto.SystemUserWithoutImportantInformationDto;
import com.kevenpotter.student.domain.entity.SystemUserEntity;
import com.kevenpotter.student.result.ApiConstant;
import com.kevenpotter.student.result.ApiResult;
import com.kevenpotter.student.service.SystemUserService;
import com.kevenpotter.student.utils.ListUtils;
import com.kevenpotter.student.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 后台用户控制层类
 *
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2019-12-12 11:21:08
 */
@CrossOrigin
@RestController
@RequestMapping("/systemUser")
public class SystemUserController {

    /*定义日志记录器，用来记录必要信息*/
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SystemUserService systemUserService;

    /**
     * 获取[系统用户实体类]
     *
     * @param userId       用户编号
     * @param userNickname 用户昵称
     * @param pageNo       当前页码
     * @param pageSize     分页大小
     * @return 返回一个结果集
     * @author KevenPotter
     * @date 2021-01-11 10:06:45
     */
    @ResponseBody
    @GetMapping("/systemUsers")
    public ApiResult getSystemUsers(
            @RequestParam(value = "userId", required = false) Long userId,
            @RequestParam(value = "userNickName", required = false) String userNickname,
            @RequestParam(value = "userStatus", required = false) Integer userStatus,
            @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        PageHelper.startPage(pageNo, pageSize);
        PageInfo<SystemUserWithoutImportantInformationDto> pageInfo = new PageInfo<>(systemUserService.getSystemUsers(userId, userNickname, userStatus));
        if (ListUtils.isEmpty(pageInfo.getList())) return ApiResult.buildFailure(ApiConstant.CODE_3, "未获取到用户信息");
        return ApiResult.buildSuccess(pageInfo);
    }

    /**
     * @param systemUserDto 后台用户数据传输类
     * @return 返回一个结果集
     * @author KevenPotter
     * @date 2019-12-19 10:25:17
     * @description 插入一条新的[后台用户实体]并返回该[后台用户实体]
     */
    @ResponseBody
    @PostMapping("/systemUsers")
    public ApiResult addSystemUser(@RequestBody SystemUserDto systemUserDto) {
        if (null == systemUserDto) return ApiResult.buildFailure(ApiConstant.CODE_1, "请求参数为空");
        SystemUserEntity systemUserEntity = systemUserService.addSystemUser(systemUserDto);
        if (null == systemUserEntity) return ApiResult.buildFailure(ApiConstant.CODE_2, "未成功添加用户信息");
        return ApiResult.buildSuccess(systemUserEntity);
    }

    /**
     * 更新[系统用户实体]并返回更新之前的[系统用户实体]
     *
     * @param systemUserDto 系统用户数据传输类
     * @return 返回一个结果集
     * @author KevenPotter
     * @date 2021-01-11 14:02:31
     */
    @ResponseBody
    @PutMapping("/systemUsers")
    public ApiResult updateUser(@RequestBody SystemUserDto systemUserDto) {
        if (null == systemUserDto) return ApiResult.buildFailure(ApiConstant.CODE_1, "请求参数为空");
        SystemUserEntity systemUserEntity = systemUserService.updateSystemMenu(systemUserDto);
        if (null == systemUserEntity) return ApiResult.buildFailure(ApiConstant.CODE_3, "未成功更新系统用户信息,系统用户信息可能不存在");
        return ApiResult.buildSuccess(systemUserEntity);
    }

    /**
     * @param userNickname 用户昵称
     * @return 返回一个结果集
     * @author KevenPotter
     * @date 2019-12-18 15:44:58
     * @description 根据[用户昵称]返回[后台用户实体类]
     */
    @GetMapping("/systemUserNi/{userNickname}")
    public ApiResult getSystemUserByNickname(@PathVariable String userNickname) {
        if (StringUtils.isEmpty(userNickname)) return ApiResult.buildFailure(ApiConstant.CODE_1, "[用户昵称]为空");
        userNickname = userNickname.trim();
        SystemUserEntity systemUserEntity = systemUserService.getSystemUserByNickname(userNickname);
        if (null == systemUserEntity) return ApiResult.buildFailure(ApiConstant.CODE_2, "未获取到后台用户信息");
        return ApiResult.buildSuccess(systemUserEntity);
    }

    /**
     * @param userEmail 用户邮箱
     * @return 返回一个结果集
     * @author KevenPotter
     * @date 2019-12-18 15:48:34
     * @description 根据[用户邮箱]返回[后台用户实体类]
     */
    @GetMapping("/systemUserEm/{userEmail}")
    public ApiResult getSystemUserByEmail(@PathVariable String userEmail) {
        if (StringUtils.isEmpty(userEmail)) return ApiResult.buildFailure(ApiConstant.CODE_1, "[用户邮箱]为空");
        userEmail = userEmail.trim();
        SystemUserEntity systemUserEntity = systemUserService.getSystemUserByEmail(userEmail);
        if (null == systemUserEntity) return ApiResult.buildFailure(ApiConstant.CODE_2, "未获取到后台用户信息");
        return ApiResult.buildSuccess(systemUserEntity);
    }

    /**
     * @param userMobile 用户手机
     * @return 返回一个结果集
     * @author KevenPotter
     * @date 2019-12-18 15:49:24
     * @description 根据[用户手机]返回[后台用户实体类]
     */
    @GetMapping("/systemUserMo/{userMobile}")
    public ApiResult getSystemUserByMobile(@PathVariable Long userMobile) {
        if (null == userMobile) return ApiResult.buildFailure(ApiConstant.CODE_1, "[用户手机]为空");
        SystemUserEntity systemUserEntity = systemUserService.getSystemUserByMobile(userMobile);
        if (null == systemUserEntity) return ApiResult.buildFailure(ApiConstant.CODE_2, "未获取到后台用户信息");
        return ApiResult.buildSuccess(systemUserEntity);
    }
}
