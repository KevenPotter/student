package com.kevenpotter.student.service;

import com.kevenpotter.student.dao.CourseDao;
import com.kevenpotter.student.domain.dto.CourseDto;
import com.kevenpotter.student.domain.dto.MajorDto;
import com.kevenpotter.student.domain.entity.CourseEntity;
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
     * @return 返回全部[课程实体]列表
     * @author KevenPotter
     * @date 2020-01-24 09:08:20
     * @description 返回全部[课程实体]列表
     */
    public List<CourseEntity> getAllCourses() {
        return courseDao.getAllCourses();
    }

    /**
     * @param courseId 课程编号
     * @return 根据[课程编号]返回[课程实体类]
     * @author KevenPotter
     * @date 2020-01-27 20:11:07
     * @description 根据[课程编号]返回[课程实体类]
     */
    public CourseEntity getCourseByCourseId(Integer courseId) {
        if (null == courseId) return null;
        return courseDao.getCourseByCourseId(courseId);
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
        return courseDao.getCourseByCourseName(courseName);
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
        return courseDao.getCoursesByDepartmentIdAndMajorIdAndSemester(departmentId, majorId, semester);
    }

    /**
     * @param courseDto 课程数据传输类
     * @return 插入一条新的[课程实体]并返回该[课程实体]
     * @author KevenPotter
     * @date 2020-01-27 20:57:23
     * @description 插入一条新的[课程实体]并返回该[课程实体]
     */
    public CourseEntity addCourse(CourseDto courseDto) {
        if (null == courseDto) return null;
        courseDao.addCourse(courseDto);
        return courseDao.getCourseById(courseDto.getId());
    }
}
