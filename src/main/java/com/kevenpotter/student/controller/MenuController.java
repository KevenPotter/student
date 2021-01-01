package com.kevenpotter.student.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kevenpotter.student.domain.dto.SystemMenuDto;
import com.kevenpotter.student.domain.entity.SystemMenuEntity;
import com.kevenpotter.student.result.ApiConstant;
import com.kevenpotter.student.result.ApiResult;
import com.kevenpotter.student.service.MenuService;
import com.kevenpotter.student.utils.ListUtils;
import com.kevenpotter.student.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * 菜单控制层类
 *
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2020-12-28 15：36：09
 */
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
@RestController
@RequestMapping("/menu")
public class MenuController {

    /*定义日志记录器，用来记录必要信息*/
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MenuService menuService;

    /**
     * 获取[系统菜单实体类]
     *
     * @param menuName   菜单名称
     * @param menuStatus 菜单状态
     * @param pageNo     当前页码
     * @param pageSize   分页大小
     * @return 返回一个结果集
     * @author KevenPotter
     * @date 2020-12-28 15:42:02
     */
    @ResponseBody
    @GetMapping("/menus")
    public ApiResult getMenus(
            @RequestParam(value = "menuName", required = false) String menuName,
            @RequestParam(value = "menuStatus", required = false) Integer menuStatus,
            @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        PageHelper.startPage(pageNo, pageSize);
        PageInfo<SystemMenuEntity> pageInfo = new PageInfo<SystemMenuEntity>(menuService.getMenus(menuName, menuStatus));
        if (ListUtils.isEmpty(pageInfo.getList())) return ApiResult.buildFailure(ApiConstant.CODE_3, "未获取到菜单信息");
        return ApiResult.buildSuccess(pageInfo);
    }

    /**
     * 插入一条新的[系统菜单实体]并返回该[系统菜单实体]
     *
     * @param systemMenuDto 系统菜单数据传输类
     * @return 返回一个结果集
     * @author KevenPotter
     * @date 2020-12-30 10:02:23
     */
    @ResponseBody
    @PostMapping("/menus")
    public ApiResult addStudent(@RequestBody SystemMenuDto systemMenuDto) {
        if (null == systemMenuDto) return ApiResult.buildFailure(ApiConstant.CODE_1, "请求参数为空");
        SystemMenuEntity systemMenuEntity = menuService.addSystemMenu(systemMenuDto);
        if (null == systemMenuEntity) return ApiResult.buildFailure(ApiConstant.CODE_4, "该菜单已存在");
        return ApiResult.buildSuccess(systemMenuEntity);
    }

    /**
     * 更新[系统菜单实体]并返回更新之前的[系统菜单实体]
     *
     * @param systemMenuDto 系统菜单数据传输类
     * @return 返回一个结果集
     * @author KevenPotter
     * @date 2020-12-29 13:42:54
     */
    @ResponseBody
    @PutMapping("/menus")
    public ApiResult updateStudent(@RequestBody SystemMenuDto systemMenuDto) {
        if (null == systemMenuDto) return ApiResult.buildFailure(ApiConstant.CODE_1, "请求参数为空");
        SystemMenuEntity systemMenuEntity = menuService.updateSystemMenu(systemMenuDto);
        if (null == systemMenuEntity) return ApiResult.buildFailure(ApiConstant.CODE_3, "未成功更新系统菜单信息,系统菜单信息可能不存在");
        return ApiResult.buildSuccess(systemMenuEntity);
    }

    /**
     * 根据[菜单名称]返回[系统菜单实体]
     *
     * @param menuName 菜单名称
     * @return 返回一个结果集
     * @author KevenPotter
     * @date 2020-12-30 13:41:26
     */
    @ResponseBody
    @GetMapping("/menuNa/{menuName}")
    public ApiResult getSystemMenuByMenuName(@PathVariable String menuName) throws UnsupportedEncodingException {
        if (StringUtils.isEmpty(menuName)) return ApiResult.buildFailure(ApiConstant.CODE_1, "[菜单名称]为空");
        menuName = URLDecoder.decode(menuName, "utf-8").trim();
        SystemMenuEntity systemMenuEntity = menuService.getMenuByMenuName(menuName);
        if (null == systemMenuEntity) return ApiResult.buildFailure(ApiConstant.CODE_3, "未获取到系统菜单名称信息");
        return ApiResult.buildSuccess(systemMenuEntity);
    }

    /**
     * 根据[菜单连接]返回[系统菜单实体]
     *
     * @param menuLinkUrl 菜单连接
     * @return 返回一个结果集
     * @author KevenPotter
     * @date 2020-12-30 13:44:48
     */
    @ResponseBody
    @PostMapping("/menuLi")
    public ApiResult getSystemUserByEmail(@RequestParam("menuLinkUrl") String menuLinkUrl) throws UnsupportedEncodingException {
        if (StringUtils.isEmpty(menuLinkUrl)) return ApiResult.buildFailure(ApiConstant.CODE_1, "[菜单连接]为空");
        menuLinkUrl = URLDecoder.decode(menuLinkUrl, "utf-8").trim();
        SystemMenuEntity systemMenuEntity = menuService.getMenuByMenuLinkUrl(menuLinkUrl);
        if (null == systemMenuEntity) return ApiResult.buildFailure(ApiConstant.CODE_3, "未获取到系统菜单链接信息");
        return ApiResult.buildSuccess(systemMenuEntity);
    }

    /**
     * 根据[菜单图标]返回[系统菜单实体]
     *
     * @param menuIcon 菜单图标
     * @return 返回一个结果集
     * @author KevenPotter
     * @date 2020-12-30 13:47:29
     */
    @ResponseBody
    @GetMapping("/menuIc/{menuIcon}")
    public ApiResult getSystemUserByMobile(@PathVariable String menuIcon) throws UnsupportedEncodingException {
        if (StringUtils.isEmpty(menuIcon)) return ApiResult.buildFailure(ApiConstant.CODE_1, "[菜单图标]为空");
        menuIcon = URLDecoder.decode(menuIcon, "utf-8").trim();
        SystemMenuEntity systemMenuEntity = menuService.getMenuByMenuIcon(menuIcon);
        if (null == systemMenuEntity) return ApiResult.buildFailure(ApiConstant.CODE_3, "未获取到系统菜单图标信息");
        return ApiResult.buildSuccess(systemMenuEntity);
    }
}
