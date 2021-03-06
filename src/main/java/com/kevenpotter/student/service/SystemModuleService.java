package com.kevenpotter.student.service;

import com.github.pagehelper.Page;
import com.kevenpotter.student.dao.SystemModuleDao;
import com.kevenpotter.student.domain.dto.SystemAllMenuDto;
import com.kevenpotter.student.domain.dto.SystemAllModuleDto;
import com.kevenpotter.student.domain.dto.SystemModuleDto;
import com.kevenpotter.student.domain.entity.SystemModuleEntity;
import com.kevenpotter.student.utils.ListUtils;
import com.kevenpotter.student.utils.NumericUtils;
import com.kevenpotter.student.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

/**
 * 系统模块服务层类
 *
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2021-01-04 23:07:42
 */
@Service
@Transactional
public class SystemModuleService {

    /*定义日志记录器，用来记录必要信息*/
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SystemMenuService systemMenuService;
    @Autowired
    private SystemModuleDao systemModuleDao;

    /**
     * 获取[系统模块实体]分页数据
     *
     * @param menuId       菜单编号
     * @param moduleName   模块名称
     * @param moduleStatus 模块状态
     * @return 返回获取[系统模块实体]分页数据
     * @author KevenPotter
     * @date 2021-01-04 23:08:57
     */
    public Page<SystemModuleEntity> getModules(Long menuId, String moduleName, Integer moduleStatus) {
        return systemModuleDao.getModules(menuId, moduleName, moduleStatus);
    }

    /**
     * 获取所有[全部系统模块数据传输类]
     *
     * @return 返回所有[全部系统模块数据传输类]
     * @author KevenPotter
     * @date 2021-01-05 13:30:04
     */
    public List<Map<Long, List<SystemAllModuleDto>>> getAllModules() {
        List<SystemAllMenuDto> systemAllMenuDtoList = systemMenuService.getAllMenus();                              // 获取所有菜单数据
        if (ListUtils.isEmpty(systemAllMenuDtoList)) return null;                                                   // 如果菜单数据为空，则返回空
        List<Map<Long, List<SystemAllModuleDto>>> list = new ArrayList<>();
        for (SystemAllMenuDto systemAllMenuDto : systemAllMenuDtoList) {
            List<SystemAllModuleDto> systemAllModuleDtoList = this.getModuleByMenuId(systemAllMenuDto.getMenuId());
            Map<Long, List<SystemAllModuleDto>> systemModuleMap = new TreeMap<>(Comparator.comparingInt(Long::intValue));
            systemModuleMap.put(systemAllMenuDto.getMenuId(), systemAllModuleDtoList);
            list.add(systemModuleMap);
        }
        return list;
    }

    /**
     * 根据[菜单编号]查询[全部系统模块数据传输类]
     *
     * @param menuId 菜单编号
     * @return 返回根据[菜单编号]查询[全部系统模块数据传输类]
     * @author KevenPotter
     * @date 2021-01-05 09:16:25
     */
    List<SystemAllModuleDto> getModuleByMenuId(Long menuId) {
        if (null == menuId) return null;
        return systemModuleDao.getModuleByMenuId(menuId);
    }

    /**
     * 根据[模块名称]查询[系统模块实体]
     *
     * @param moduleName 模块名称
     * @return 返回根据[模块名称]查询[系统模块实体]
     * @author KevenPotter
     * @date 2021-01-04 23:09:39
     */
    public SystemModuleEntity getModuleByModuleName(String moduleName) {
        if (StringUtils.isEmpty(moduleName)) return null;
        return systemModuleDao.getModuleByModuleName(moduleName);
    }

    /**
     * 根据[模块编号]查询[系统模块实体]
     *
     * @param moduleId 模块编号
     * @return 返回根据[模块编号]查询[系统模块实体]
     * @author KevenPotter
     * @date 2021-01-15 13:45:00
     */
    public SystemModuleEntity getModuleByModuleId(Long moduleId) {
        if (NumericUtils.longIsEmpty(moduleId)) return null;
        return systemModuleDao.getSystemModuleById(moduleId);
    }

    /**
     * 插入一条新的[系统模块实体]并返回该[系统模块实体]
     *
     * @param systemModuleDto 系统模块数据传输类
     * @return 返回插入的[系统模块实体]
     * @author KevenPotter
     * @date 2021-01-04 23:11:37
     */
    public SystemModuleEntity addSystemModule(SystemModuleDto systemModuleDto) {
        if (null == systemModuleDto) return null;
        SystemModuleEntity systemModuleEntity = this.getModuleByModuleName(systemModuleDto.getModuleName());
        if (null != systemModuleEntity) return null;
        systemModuleDao.addSystemModule(systemModuleDto);
        return systemModuleDao.getSystemModuleById(systemModuleDto.getId());
    }

    /**
     * 更新[系统模块实体]并返回更新之前的[系统模块实体]
     *
     * @param systemModuleDto 系统模块数据传输类
     * @return 返回更新之前的系统模块实体
     * @author KevenPotter
     * @date 2021-01-04 23:14:50
     */
    public SystemModuleEntity updateSystemModule(SystemModuleDto systemModuleDto) {
        if (null == systemModuleDto) return null;
        SystemModuleEntity systemModuleEntity = systemModuleDao.getSystemModuleById(systemModuleDto.getId());
        if (null == systemModuleEntity) return null;
        systemModuleDao.updateSystemModule(systemModuleDto.setModuleUpdateTime(LocalDateTime.now()));
        return systemModuleDao.getSystemModuleById(systemModuleDto.getId());
    }
}
