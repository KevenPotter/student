package com.kevenpotter.student.service;

import com.github.pagehelper.Page;
import com.kevenpotter.student.dao.CourseDao;
import com.kevenpotter.student.dao.LoginDao;
import com.kevenpotter.student.domain.dto.CourseDto;
import com.kevenpotter.student.domain.entity.CourseEntity;
import com.kevenpotter.student.domain.entity.SystemUserEntity;
import com.kevenpotter.student.utils.ListUtils;
import com.kevenpotter.student.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2020-12-23 11:25:03
 * @description 登录服务层类
 */
@Service
public class LoginService {

    /*定义日志记录器，用来记录必要信息*/
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private LoginDao loginDao;

    /**
     * @return 返回全部[课程实体]列表
     * @author KevenPotter
     * @date 2020-01-24 09:08:20
     * @description 返回全部[课程实体]列表
     */
    public List<CourseEntity> getAllCourses() {
        return loginDao.getAllCourses();
    }

    /**
     * @param orderByParam 排序字段
     * @return 根据[排序字段]返回全部[课程实体]列表
     * @author KevenPotter
     * @date 2020-02-03 10:39:59
     * @description 根据[排序字段]返回全部[课程实体]列表
     */
    public List<CourseEntity> getAllCourses(String orderByParam) {
        List<CourseEntity> courseEntityList = loginDao.getAllCoursesByOrder(orderByParam);
        if (ListUtils.isEmpty(courseEntityList)) return null;
        return courseEntityList;
    }

    /**
     * @param departmentId 系别编号
     * @param majorId      专业编号
     * @param semester     学期
     * @return 依据指定条件查找规定范围内的[课程实体]列表
     * @author KevenPotter
     * @date 2020-01-29 23:57:08
     * @description 依据指定条件查找规定范围内的[课程实体]列表
     */
    public Page<CourseEntity> getCourses(Integer departmentId, Integer majorId, Integer semester) {
        if (null == departmentId && null == majorId && null == semester) return null;
        return loginDao.getCourses(departmentId, majorId, semester);
    }

    /**
     * 根据[用户编号]返回[后台用户实体类]
     *
     * @param userId 用户编号
     * @return 根据[用户编号]返回[后台用户实体类]
     * @author KevenPotter
     * @date 2020-12-23 11:34:46
     */
    public SystemUserEntity getSystemUserByUserId(Long userId) {
        if (null == userId) return null;
        return loginDao.getSystemUserByUserId(userId);
    }

    /**
     * @param majorId 专业编号
     * @return 根据[专业编号]返回[课程实体类]列表
     * @author KevenPotter
     * @date 2020-01-28 17:20:40
     * @description 根据[专业编号]返回[课程实体类]列表
     */
    public List<CourseEntity> getCoursesByMajorId(Integer majorId) {
        if (null == majorId) return null;
        return loginDao.getCoursesByMajorId(majorId);
    }

    /**
     * @param majorId  专业编号
     * @param semester 学期
     * @return 根据[专业编号]和[学期]返回[课程实体类]列表
     * @author KevenPotter
     * @date 2020-01-29 19:21:18
     * @description 根据[专业编号]和[学期]返回[课程实体类]列表
     */
    public List<CourseEntity> getCoursesByMajorIdAndSemester(Integer majorId, Integer semester) {
        if (null == majorId || null == semester) return null;
        return loginDao.getCoursesByMajorIdAndSemester(majorId, semester);
    }

    /**
     * @param courseName 课程名称
     * @return 根据[课程名称]返回[课程实体类]
     * @author KevenPotter
     * @date 2020-01-27 20:53:49
     * @description 根据[课程名称]返回[课程实体类]
     */
    public CourseEntity getCourseByMajorName(String courseName) {
        if (StringUtils.isEmpty(courseName)) return new CourseEntity();
        return loginDao.getCourseByCourseName(courseName);
    }

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
        return loginDao.getCoursesByDepartmentIdAndMajorIdAndSemester(departmentId, majorId, semester);
    }
}