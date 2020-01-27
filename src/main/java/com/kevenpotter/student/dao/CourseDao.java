package com.kevenpotter.student.dao;

import com.kevenpotter.student.domain.dto.CourseDto;
import com.kevenpotter.student.domain.entity.CourseEntity;
import org.apache.ibatis.annotations.*;
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
     * @return 返回全部[课程实体]列表
     * @author KevenPotter
     * @date 220-01-26 23:33:43
     * @description 返回全部[课程实体]列表
     */
    @Select("SELECT * FROM course c ")
    List<CourseEntity> getAllCourses();

    /**
     * @param id 主键ID
     * @return 根据[主键ID]返回[课程实体类]
     * @author KevenPotter
     * @date 2020-01-27 22:31:55
     * @description 根据[主键ID]返回[课程实体类]
     */
    @Select("SELECT * FROM course c WHERE c.id=#{id}")
    CourseEntity getCourseById(@Param("id") Long id);

    /**
     * @param courseId 课程编号
     * @return 根据[课程编号]返回[课程实体类]
     * @author KevenPotter
     * @date 2020-01-27 20:17:53
     * @description 根据[课程编号]返回[课程实体类]
     */
    @Select("SELECT * FROM course c WHERE c.course_id=#{courseId}")
    CourseEntity getCourseByCourseId(@Param("courseId") Integer courseId);

    /**
     * @param courseName 课程名称
     * @return 根据[课程名称]返回[课程实体类]
     * @author KevenPotter
     * @date 2020-01-27 20:55:17
     * @description 根据[课程名称]返回[课程实体类]
     */
    @Select("SELECT * FROM course c WHERE c.course_name=#{courseName}")
    CourseEntity getCourseByCourseName(@Param("courseName") String courseName);

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

    /**
     * @param courseDto 课程数据传输类
     * @author KevenPotter
     * @date 2020-01-27 22:33:41
     * @description 插入一条新的[课程实体]并返回该[课程实体]
     */
    @Insert("INSERT INTO `student`.`course` (`course_id`, `course_name`, `hour`, `credit`) VALUES (#{courseDto.courseId},#{courseDto.courseName},#{courseDto.hour},#{courseDto.credit});")
    @Options(useGeneratedKeys = true, keyProperty = "courseDto.id", keyColumn = "id")
    void addCourse(@Param("courseDto") CourseDto courseDto);
}

