package com.kevenpotter.student.dao;

import com.kevenpotter.student.domain.entity.CourseEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2019-11-23 12:44:59
 * @description 课程持久层类
 */
@Repository
@Mapper
public interface CourseDao {

    /**
     * @param departmentId 系别编号
     * @param majorId      专业编号
     * @param semester     学期
     * @return 返回[课程实体]列表
     * @author KevenPotter
     * @date 2020-01-09 10:52:00
     * @description 依据[系别编号]、[专业编号]、[学期]返回[课程实体]列表
     */
    @Select("SELECT c.* FROM course c LEFT JOIN department_major_semester_course dmsc ON c.course_id = dmsc.course_id WHERE dmsc.department_id = #{departmentId} AND dmsc.major_id = #{majorId} AND dmsc.semester = #{semester};")
    List<CourseEntity> getCoursesByDepartmentIdAndMajorIdAndSemester(@Param("departmentId") Integer departmentId, @Param("majorId") Integer majorId, @Param("semester") Integer semester);
}

