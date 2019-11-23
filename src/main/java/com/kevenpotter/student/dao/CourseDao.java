package com.kevenpotter.student.dao;

import com.kevenpotter.student.domain.dto.CourseDto;
import com.kevenpotter.student.domain.entity.CourseEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

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
     * @param name 课程名称
     * @return 返回一个[课程实体]
     * @author KevenPotter
     * @date 2019-11-23 12:45:47
     * @description 根据[课程名称]查询[课程实体]
     */
    @Select("SELECT * FROM course c WHERE c.name = #{name}")
    CourseEntity findCourseByName(@Param("name") String name);

    /**
     * @param courseId 课程编号
     * @return 返回一个[课程实体]
     * @author KevenPotter
     * @date 2019-11-23 12:47:00
     * @description 根据[课程编号]查询[课程实体]
     */
    @Select("SELECT * FROM course c WHERE c.id = #{courseId}")
    CourseEntity findCourseById(@Param("courseId") Long courseId);

    /**
     * @param courseId 课程编号
     * @param name     课程名称
     * @return 返回一个[课程实体]
     * @author KevenPotter
     * @date 2019-11-23 12:49:22
     * @description 根据[课程编号]和[课程名称]查询[课程实体]
     */
    @Select("SELECT * FROM course c WHERE c.id = #{courseId} AND c.name = #{name};")
    CourseEntity findCourseByIdAndName(@Param("courseId") Long courseId, @Param("name") String name);

    /**
     * @param courseDto 课程数据传输类
     * @author KevenPotter
     * @date 2019-11-23 12:56:21
     * @description 插入一条新的[课程实体]
     */
    @Insert("INSERT INTO `student`.`course` (`id`, `name`, `hour`, `credit`) VALUES (#{courseDto.id}, #{courseDto.name}, #{courseDto.hour}, #{courseDto.credit});")
    void addCourse(@Param("courseDto") CourseDto courseDto);

    /**
     * @param courseDto 课程数据传输类
     * @author KevenPotter
     * @date 2019-11-23 12:58:22
     * @description 更新[课程实体]
     */
    @Update("UPDATE `student`.`course` SET `id`=#{courseDto.id}, `name`=#{courseDto.name}, `hour`=#{courseDto.hour}, `credit`=#{courseDto.credit} WHERE (`id`=#{courseDto.id});")
    void updateCourse(@Param("courseDto") CourseDto courseDto);
}

