package com.kevenpotter.student.dao;

import com.kevenpotter.student.domain.dto.CourseDto;
import com.kevenpotter.student.domain.dto.DepartmentMajorSemesterCourseDto;
import com.kevenpotter.student.domain.entity.DepartmentMajorSemesterCourseEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2020-02-04 10:00:33
 * @description 系部专业学期课程持久层类
 */
@Repository
@Mapper
public interface DepartmentMajorSemesterCourseDao {

    /**
     * @param departmentId 系别编号
     * @param majorId      专业编号
     * @param courseId     课程编号
     * @return 依据[系别编号]、[专业编号]和[课程编号]返回[系部专业学期课程]列表
     * @author KevenPotter
     * @date 2020-02-06 17:42:46
     * @description 依据[系别编号]、[专业编号]和[课程编号]返回[系部专业学期课程]列表
     */
    @Select("SELECT * FROM `department_major_semester_course` dmsc WHERE dmsc.department_id = #{departmentId} AND dmsc.major_id = #{majorId} AND dmsc.course_id = #{courseId} ")
    List<DepartmentMajorSemesterCourseEntity> getDepartmentMajorSemesterCourseByDepartmentIdAndMajorIdAndCourseId(@Param("departmentId") Integer departmentId, @Param("majorId") Integer majorId, @Param("courseId") Integer courseId);

    /**
     * @param departmentId 系别编号
     * @param majorId      专业编号
     * @param semester     学期
     * @param courseId     课程编号
     * @return 依据[系别编号]、[专业编号]、[学期]和[课程编号]返回[系部专业学期课程]列表
     * @author KevenPotter
     * @date 2020-02-05 23:44:17
     * @description 依据[系别编号]、[专业编号]、[学期]和[课程编号]返回[系部专业学期课程]列表
     */
    @Select("SELECT * FROM `department_major_semester_course` dmsc WHERE dmsc.department_id = #{departmentId} AND dmsc.major_id = #{majorId} AND dmsc.semester = #{semester} AND dmsc.course_id = #{courseId} ")
    List<DepartmentMajorSemesterCourseEntity> getDepartmentMajorSemesterCourseByDepartmentIdAndMajorIdAndSemesterAndCourseId(@Param("departmentId") Integer departmentId, @Param("majorId") Integer majorId, @Param("semester") Integer semester, @Param("courseId") Integer courseId);

    /**
     * @param courseDto 课程数据传输类
     * @author KevenPotter
     * @date 2020-01-27 22:33:41
     * @description 插入一条新的[课程实体]并返回该[课程实体]
     */
    @Insert("INSERT INTO `student`.`course` (`course_id`, `course_name`, `hour`, `credit`) VALUES (#{courseDto.courseId},#{courseDto.courseName},#{courseDto.hour},#{courseDto.credit});")
    @Options(useGeneratedKeys = true, keyProperty = "courseDto.id", keyColumn = "id")
    void addDepartmentMajorSemesterCourse(@Param("courseDto") CourseDto courseDto);

    /**
     * @param departmentMajorSemesterCourseDtoList [系部专业学期课程数据传输类]列表
     * @return 批量插入[系部专业学期课程]并返回插入记录的数量
     * @author KevenPotter
     * @date 2020-02-06 20:53:43
     * @description 批量插入[系部专业学期课程]并返回插入记录的数量
     */
    Integer batchAddDepartmentMajorSemesterCourse(List<DepartmentMajorSemesterCourseDto> departmentMajorSemesterCourseDtoList);
}

