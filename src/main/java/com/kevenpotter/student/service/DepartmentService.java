package com.kevenpotter.student.service;

import com.kevenpotter.student.dao.DepartmentDao;
import com.kevenpotter.student.domain.dto.DepartmentDto;
import com.kevenpotter.student.domain.dto.DepartmentNestedPiesDataDto;
import com.kevenpotter.student.domain.dto.DepartmentNestedPiesDto;
import com.kevenpotter.student.domain.entity.DepartmentEntity;
import com.kevenpotter.student.utils.ListUtils;
import com.kevenpotter.student.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2019-12-06 16:57:31
 * @description 系别服务层类
 */
@Service
public class DepartmentService {

    /*定义日志记录器，用来记录必要信息*/
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DepartmentDao departmentDao;

    /**
     * @return 返回全部[系别实体类]列表
     * @author KevenPotter
     * @date 2019-12-06 17:00:45
     * @description 返回全部[系别实体类]列表
     */
    public List<DepartmentEntity> getAllDepartments() {
        return departmentDao.getAllDepartments();
    }

    /**
     * @param id 主键ID
     * @return 根据[主键ID]返回[系别实体类]
     * @author KevenPotter
     * @date 2020-01-17 16:54:50
     * @description 根据[主键ID]返回[系别实体类]
     */
    public DepartmentEntity getDepartmentById(Long id) {
        if (null == id) return null;
        return departmentDao.getDepartmentById(id);
    }

    /**
     * @param departmentId 系别编号
     * @return 根据[系别编号]返回[系别实体类]
     * @author KevenPotter
     * @date 2019-12-06 21:56:40
     * @description 根据[系别编号]返回[系别实体类]
     */
    public DepartmentEntity getDepartmentByDepartmentId(Long departmentId) {
        if (null == departmentId) return new DepartmentEntity();
        return departmentDao.getDepartmentByDepartmentId(departmentId);
    }

    /**
     * @param departmentName 系部名称
     * @return 根据[系部名称]返回[系别实体类]
     * @author KevenPotter
     * @date 2020-01-17 15:58:58
     * @description 根据[系部名称]返回[系别实体类]
     */
    public DepartmentEntity getDepartmentByDepartmentName(String departmentName) {
        if (StringUtils.isEmpty(departmentName)) return new DepartmentEntity();
        return departmentDao.getDepartmentByDepartmentName(departmentName);
    }

    /**
     * @return 统计各系部专业数量并将其返回
     * @author KevenPotter
     * @date 2020-02-08 17:25:31
     * @description 统计各系部专业数量并将其返回
     */
    public DepartmentNestedPiesDto getDepartmentNestedPiesStatistics() {
        List<DepartmentNestedPiesDataDto> departmentNestedPiesDataDtoList = departmentDao.getDepartmentNestedPiesStatistics();
        if (ListUtils.isEmpty(departmentNestedPiesDataDtoList)) return null;
        ArrayList<String> majorNameList = new ArrayList<String>();
        ArrayList<DepartmentNestedPiesDataDto> departmentNestedPiesDataDtoArrayList = new ArrayList<DepartmentNestedPiesDataDto>();
        for (DepartmentNestedPiesDataDto departmentNestedPiesDataDto : departmentNestedPiesDataDtoList) {
            majorNameList.add(departmentNestedPiesDataDto.getName());
            departmentNestedPiesDataDtoArrayList.add(departmentNestedPiesDataDto);
        }
        DepartmentNestedPiesDto departmentNestedPiesDto = new DepartmentNestedPiesDto();
        departmentNestedPiesDto.setMajorNameList(majorNameList);
        departmentNestedPiesDto.setDepartmentNestedPiesDataDtoList(departmentNestedPiesDataDtoArrayList);
        return departmentNestedPiesDto;
    }

    /**
     * @param departmentDto 系别数据传输类
     * @return 插入一条新的[系别实体]并返回该[系别实体]
     * @author KevenPotter
     * @date 2020-01-17 16:39:22
     * @description 插入一条新的[系别实体]并返回该[系别实体]
     */
    public DepartmentEntity addDepartment(DepartmentDto departmentDto) {
        if (null == departmentDto) return null;
        departmentDao.addDepartment(departmentDto);
        return departmentDao.getDepartmentById(departmentDto.getId());
    }
}
