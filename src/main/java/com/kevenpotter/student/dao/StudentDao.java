package com.kevenpotter.student.dao;

import com.kevenpotter.student.domain.dto.StudentDto;
import com.kevenpotter.student.domain.entity.StudentEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

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
     * @param id 主键ID
     * @return 返回一个[学生实体]
     * @author KevenPotter
     * @date 2019-11-22 13:22:38
     * @description 根据[主键ID]查询[学生实体]
     */
    @Select("SELECT * FROM student s WHERE s.id = #{id}")
    StudentEntity getStudentById(@Param("id") Long id);

    /**
     * @param studentId 学生编号
     * @param name      学生姓名
     * @return 返回[学生实体]列表
     * @author KevenPotter
     * @date 2019-11-29 11:16:38
     * @description 根据给定的参数进行[学生实体]列表的查询
     */
    @Select("<script> " +
            "SELECT * FROM student s " +
            "WHERE 1=1 " +
            "<if test='studentId != null'> " +
            "AND s.student_id = #{studentId}  " +
            "</if> " +
            "<if test='name != null'> " +
            "AND s.name LIKE CONCAT('%',#{name},'%') " +
            "</if>" +
            "</script>")
    List<StudentEntity> getStudents(@Param("studentId") Long studentId, @Param("name") String name);

    /**
     * @param studentDto 学生数据传输类
     * @author KevenPotter
     * @date 2019-11-22 15:48:44
     * @description 插入一条新的[学生实体]
     */
    @Insert("INSERT INTO `student`.`student` (`student_id`, `department_id`, `grade`, `clazz`, `sex`, `name`, `age`, `address`, `addtime`) VALUES (#{studentDto.studentId}, #{studentDto.departmentId}, #{studentDto.grade}, #{studentDto.clazz}, #{studentDto.sex}, #{studentDto.name}, #{studentDto.age}, #{studentDto.address}, now());")
    @Options(useGeneratedKeys = true, keyProperty = "studentDto.id", keyColumn = "id")
    void addStudent(@Param("studentDto") StudentDto studentDto);

    /**
     * @param studentDto 学生数据传输类
     * @author KevenPotter
     * @date 2019-11-22 16:05:38
     * @description 更新[学生实体]
     */
    @Update("UPDATE `student`.`student` SET `student_id`=#{studentDto.studentId}, `department_id`=#{studentDto.departmentId}, `grade`=#{studentDto.grade}, `clazz`=#{studentDto.clazz}, `sex`=#{studentDto.sex}, `name`=#{studentDto.name}, `age`=#{studentDto.age}, `address`=#{studentDto.address} WHERE (`student_id`=#{studentDto.studentId});")
    @Options(useGeneratedKeys = true, keyProperty = "studentDto.id", keyColumn = "1")
    void updateStudent(@Param("studentDto") StudentDto studentDto);
}

