package com.kevenpotter.student.service;

import com.github.pagehelper.Page;
import com.kevenpotter.student.dao.SystemMenuDao;
import com.kevenpotter.student.domain.dto.SystemAllMenuDto;
import com.kevenpotter.student.domain.dto.SystemAllMenuForIndexDto;
import com.kevenpotter.student.domain.dto.SystemMenuDto;
import com.kevenpotter.student.domain.entity.SystemMenuEntity;
import com.kevenpotter.student.utils.StringUtils;
import org.apache.ibatis.annotations.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 菜单服务层类
 *
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2019-11-22 11:30:11
 */
@Service
public class SystemMenuService {

    /*定义日志记录器，用来记录必要信息*/
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SystemMenuDao systemMenuDao;

    /**
     * 获取[系统菜单实体]分页数据
     *
     * @param menuName   菜单名称
     * @param menuStatus 菜单状态
     * @return 返回获取[系统菜单实体]分页数据
     * @author KevenPotter
     * @date 2020-12-28 15:44:03
     */
    public Page<SystemMenuEntity> getMenus(String menuName, Integer menuStatus) {
        return systemMenuDao.getMenus(menuName, menuStatus);
    }

    /**
     * 获取所有[全部系统菜单数据传输类]
     *
     * @return 返回所有[全部系统菜单数据传输类]
     * @author KevenPotter
     * @date 2021-01-05 09:48:16
     */
    public List<SystemAllMenuDto> getAllMenus() {
        return systemMenuDao.getAllMenus();
    }

    /**
     * 获取所有[首页全部系统菜单数据传输类]
     *
     * @return 返回所有[首页全部系统菜单数据传输类]
     * @author KevenPotter
     * @date 2021-01-05 13:44:00
     */
    public List<SystemAllMenuForIndexDto> getAllMenusForIndex() {
        return systemMenuDao.getAllMenusForIndex();
    }

    /**
     * 依据[菜单编号]获取[首页全部系统菜单数据传输类]
     *
     * @return 返回依据[菜单编号]获取[首页全部系统菜单数据传输类]
     * @author KevenPotter
     * @date 2021-01-15 11:04:41
     */
    public SystemAllMenuForIndexDto getMenusByMenuId(Long menuId) {
        return systemMenuDao.getMenusByMenuId(menuId);
    }

    /**
     * 根据[菜单名称]查询[系统菜单实体]
     *
     * @param menuName 菜单名称
     * @return 返回根据[菜单名称]查询[系统菜单实体]
     * @author KevenPotter
     * @date 2020-12-30 11:17:12
     */
    public SystemMenuEntity getMenuByMenuName(String menuName) {
        if (StringUtils.isEmpty(menuName)) return null;
        return systemMenuDao.getMenuByMenuName(menuName);
    }

    /**
     * 根据[菜单连接]查询[系统菜单实体]
     *
     * @param menuLinkUrl 菜单连接
     * @return 返回根据[菜单连接]查询[系统菜单实体]
     * @author KevenPotter
     * @date 2020-12-30 11:19:01
     */
    @Select("SELECT * FROM system_menu sm WHERE sm.menu_link_url = #{menuLinkUrl}")
    public SystemMenuEntity getMenuByMenuLinkUrl(String menuLinkUrl) {
        if (StringUtils.isEmpty(menuLinkUrl)) return null;
        return systemMenuDao.getMenuByMenuLinkUrl(menuLinkUrl);
    }

    /**
     * 根据[菜单图标]查询[系统菜单实体]
     *
     * @param menuIcon 菜单图标
     * @return 返回根据[菜单图标]查询[系统菜单实体]
     * @author KevenPotter
     * @date 2020-12-30 11:20:36
     */
    public SystemMenuEntity getMenuByMenuIcon(String menuIcon) {
        if (StringUtils.isEmpty(menuIcon)) return null;
        return systemMenuDao.getMenuByMenuIcon(menuIcon);
    }

    /**
     * 插入一条新的[系统菜单实体]并返回该[系统菜单实体]
     *
     * @param systemMenuDto 系统菜单数据传输类
     * @return 返回插入的[系统菜单实体]
     * @author KevenPotter
     * @date 2020-12-30 10:03:19
     */
    public SystemMenuEntity addSystemMenu(SystemMenuDto systemMenuDto) {
        if (null == systemMenuDto) return null;
        SystemMenuEntity systemMenuEntity = this.getMenuByMenuName(systemMenuDto.getMenuName());
        if (null != systemMenuEntity) return null;
        systemMenuDao.addMenu(systemMenuDto);
        return systemMenuDao.getSystemMenuById(systemMenuDto.getId());
    }

    /**
     * 更新[系统菜单实体]并返回更新之前的[系统菜单实体]
     *
     * @param systemMenuDto 系统菜单数据传输类
     * @return 返回更新之前的系统菜单实体
     * @author KevenPotter
     * @date 2020-12-29 13:43:24
     */
    public SystemMenuEntity updateSystemMenu(SystemMenuDto systemMenuDto) {
        if (null == systemMenuDto) return null;
        SystemMenuEntity systemMenuEntity = systemMenuDao.getSystemMenuById(systemMenuDto.getId());
        if (null == systemMenuEntity) return null;
        systemMenuDao.updateSystemMenu(systemMenuDto.setMenuUpdateTime(LocalDateTime.now()));
        return systemMenuDao.getSystemMenuById(systemMenuDto.getId());
    }

}
