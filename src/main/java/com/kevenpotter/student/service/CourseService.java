package com.kevenpotter.student.service;

import com.kevenpotter.student.dao.CourseDao;
import com.kevenpotter.student.domain.dto.CourseDto;
import com.kevenpotter.student.domain.entity.CourseEntity;
import com.kevenpotter.student.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2019-11-23 18:00:00
 * @description 课程服务层类
 */
@Service
public class CourseService {

    @Autowired
    private CourseDao courseDao;

    /**
     * @param courseId 课程编号
     * @param name     课程名称
     * @return 返回一个[课程实体]
     * @author KevenPotter
     * @date 2019-11-23 18:09:50
     * @description 根据[课程编号]或[课程名称]查询[课程实体]
     */
    public CourseEntity getCourse(Long courseId, String name) {
        if (null == courseId && StringUtils.isEmpty(name)) return null;
        if (null == courseId) return courseDao.findCourseByName(name);
        if (StringUtils.isEmpty(name)) return courseDao.findCourseById(courseId);
        return courseDao.findCourseByIdAndName(courseId, name);
    }

    /**
     * @param courseDto 课程数据传输类
     * @return 返回插入的课程实体
     * @author KevenPotter
     * @date 2019-11-23 18:14:28
     * @description 插入一条新的[课程实体]并返回该[课程实体]
     */
    public CourseEntity addCourse(CourseDto courseDto) {
        if (null == courseDao) return null;
        courseDao.addCourse(courseDto);
        return this.getCourse(courseDto.getId(), courseDto.getName());
    }

    /**
     * @param courseDto 课程数据传输类
     * @return 返回更新之前的课程实体
     * @author KevenPotter
     * @date 2019-11-23 18:16:45
     * @description 更新[课程实体]并返回更新之前的[课程实体]
     */
    public CourseEntity updateStudent(CourseDto courseDto) {
        if (null == courseDto) return null;
        CourseEntity courseEntity = courseDao.findCourseById(courseDto.getId());
        courseDao.updateCourse(courseDto);
        return courseEntity;
    }

}
