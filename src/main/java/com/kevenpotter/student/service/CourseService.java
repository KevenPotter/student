package com.kevenpotter.student.service;

import com.kevenpotter.student.dao.CourseDao;
import com.kevenpotter.student.domain.entity.CourseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2019-11-23 18:00:00
 * @description 课程服务层类
 */
@Service
public class CourseService {

    /*定义日志记录器，用来记录必要信息*/
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CourseDao courseDao;

    /**
     * @param departmentId 系别编号
     * @param majorId      专业编号
     * @param semester     学期
     * @return 返回[课程实体]列表
     * @author KevenPotter
     * @date 2020-01-09 10:53:22
     * @description 依据[系别编号]、[专业编号]、[学期]返回[课程实体]列表
     */
    public List<CourseEntity> getCoursesByDepartmentIdAndMajorIdAndSemester(Integer departmentId, Integer majorId, Integer semester) {
        if (null == departmentId || null == majorId || null == semester) return null;
        return courseDao.getCoursesByDepartmentIdAndMajorIdAndSemester(departmentId, majorId, semester);
    }
}
