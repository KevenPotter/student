package com.kevenpotter.student.dao;

import com.kevenpotter.student.domain.dto.StudentDto;
import com.kevenpotter.student.domain.entity.StudentEntity;
import org.apache.ibatis.annotations.*;
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
    @Select("SELECT * FROM student s WHERE s.id = #{studentId} AND s.name = #{name};")
    StudentEntity findUserByIdAndName(@Param("studentId") Long studentId, @Param("name") String name);

    /**
     * @param studentDto 学生数据传输类
     * @author KevenPotter
     * @date 2019-11-22 15:48:44
     * @description 插入一条新的[学生实体]
     */
    @Insert("INSERT INTO `student`.`student` (`id`, `sex`, `name`, `age`, `address`, `addtime`, `department_id`) VALUES (#{studentDto.id}, #{studentDto.sex}, #{studentDto.name}, #{studentDto.age}, #{studentDto.address}, now(), #{studentDto.departmentId});")
    void addStudent(@Param("studentDto") StudentDto studentDto);

    /**
     * @param studentDto 学生数据传输类
     * @author KevenPotter
     * @date 2019-11-22 16:05:38
     * @description 更新[学生实体]
     */
    @Update("UPDATE `student`.`student` SET `id`=#{studentDto.id}, `sex`=#{studentDto.sex}, `name`=#{studentDto.name}, `age`=#{studentDto.age}, `address`=#{studentDto.address}, `department_id`=#{studentDto.departmentId} WHERE (`id`=#{studentDto.id});")
    void updateStudent(@Param("studentDto") StudentDto studentDto);
}

