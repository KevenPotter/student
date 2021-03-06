package com.kevenpotter.student.service;

import com.kevenpotter.student.dao.MajorDao;
import com.kevenpotter.student.domain.dto.MajorDto;
import com.kevenpotter.student.domain.entity.MajorEntity;
import com.kevenpotter.student.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2019-12-09 09:53:25
 * @description 专业服务层类
 */
@Service
public class MajorService {

    /*定义日志记录器，用来记录必要信息*/
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MajorDao majorDao;

    /**
     * @return 返回全部[专业实体类]列表
     * @author KevenPotter
     * @date 2019-12-09 10:01:53
     * @description 返回全部[专业实体类]列表
     */
    public List<MajorEntity> getAllMajors() {
        return majorDao.getAllMajors();
    }

    /**
     * @param departmentId 系别编号
     * @return 根据[系别编号]返回[专业实体类]列表
     * @author KevenPotter
     * @date 2019-12-09 10:03:11
     * @description 根据[系别编号]返回[专业实体类]列表
     */
    public List<MajorEntity> getMajorsByDepartmentId(Integer departmentId) {
        if (null == departmentId) return null;
        return majorDao.getMajorsByDepartmentId(departmentId);
    }

    /**
     * @param majorId 专业编号
     * @return 根据[专业编号]返回[专业实体类]
     * @author KevenPotter
     * @date 2019-12-10 09:28:19
     * @description 根据[专业编号]返回[专业实体类]
     */
    public MajorEntity getMajorByMajorId(Integer majorId) {
        if (null == majorId) return null;
        return majorDao.getMajorByMajorId(majorId);
    }

    /**
     * @param majorName 专业名称
     * @return 根据[专业名称]返回[专业实体类]
     * @author KevenPotter
     * @date 2020-01-22 10:57:43
     * @description 根据[专业名称]返回[专业实体类]
     */
    public MajorEntity getMajorByMajorName(String majorName) {
        if (StringUtils.isEmpty(majorName)) return new MajorEntity();
        return majorDao.getMajorByMajorName(majorName);
    }

    /**
     * @param majorDto 专业数据传输类
     * @return 插入一条新的[专业实体]并返回该[专业实体]
     * @author KevenPotter
     * @date 2020-01-22 11:01:27
     * @description 插入一条新的[专业实体]并返回该[专业实体]
     */
    public MajorEntity addMajor(MajorDto majorDto) {
        if (null == majorDto) return null;
        majorDao.addMajor(majorDto);
        return majorDao.getMajorById(majorDto.getId());
    }
}
