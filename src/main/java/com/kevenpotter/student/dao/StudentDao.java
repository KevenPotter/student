package com.kevenpotter.student.dao;

import com.kevenpotter.student.mapper.StudentEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2019-11-22 11:25:12
 * @description 学生持久层类
 */
@Repository
@Mapper
public interface StudentDao {

    /**
     * @param name 学生姓名
     * @return 返回一个[学生实体]
     * @author KevenPotter
     * @date 2019-11-22 11:30:35
     * @description 根据[学生姓名]查询[学生实体]
     */
    @Select("SELECT * FROM student s WHERE s.name = #{name}")
    StudentEntity findUserByName(@Param("name") String name);

    /**
     * @param studentId 学生编号
     * @return 返回一个[学生实体]
     * @author KevenPotter
     * @date 2019-11-22 13:22:38
     * @description 根据[学生编号]查询[学生实体]
     */
    @Select("SELECT * FROM student s WHERE s.id = #{studentId}")
    StudentEntity findUserById(@Param("studentId") Long studentId);

    /**
     * @param studentId 学生编号
     * @param name      学生姓名
     * @return 返回一个[学生实体]
     * @author KevenPotter
     * @date 2019-11-22 11:26:26
     * @description 根据[学生编号]和[学生姓名]查询[学生实体]
     */
    @Select("SELECT * FROM student s WHERE s.id = #{studentId} AND s.name = #{name}")
    StudentEntity findUserByIdAndName(@Param("studentId") Long studentId, @Param("name") String name);
}

