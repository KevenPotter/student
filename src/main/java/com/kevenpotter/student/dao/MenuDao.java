package com.kevenpotter.student.dao;

import com.github.pagehelper.Page;
import com.kevenpotter.student.domain.dto.StudentDto;
import com.kevenpotter.student.domain.dto.StudentProfileDto;
import com.kevenpotter.student.domain.dto.StudentSexStatisticsDto;
import com.kevenpotter.student.domain.entity.StudentEntity;
import com.kevenpotter.student.domain.entity.SystemMenuEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 菜单持久层类
 *
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2020-12-28 15:37:41
 */
@Repository
@Mapper
public interface MenuDao {

    /**
     * 根据给定的参数进行[系统菜单实体]列表的查询
     *
     * @return 返回根据给定的参数进行[系统菜单实体]列表的查询
     * @author KevenPotter
     * @date 2020-12-28 15:46:18
     */
    @Select("SELECT * FROM system_menu sm")
    Page<SystemMenuEntity> getMenus();

    /**
     * @param id 主键ID
     * @return 返回一个[学生实体]
     * @author KevenPotter
     * @date 2019-11-29 10:45:09
     * @description 根据[主键ID]查询[学生实体]
     */
    @Select("SELECT * FROM student s WHERE s.id = #{id}")
    StudentEntity getStudentById(@Param("id") Long id);

    /**
     * @param studentId 学生编号
     * @return 返回一个[学生实体]
     * @author KevenPotter
     * @date 2019-11-22 13:22:38
     * @description 根据[学生编号]查询[学生实体]
     */
    @Select("SELECT * FROM student s WHERE s.student_id = #{studentId}")
    StudentEntity getStudentByStudentId(@Param("studentId") String studentId);

    /**
     * @param studentId 学生编号
     * @return 返回一个[学生详情数据传输类]
     * @author KevenPotter
     * @date 2020-01-04 00:20:34
     * @description 根据[学生编号]查询[学生详情数据传输类]
     */
    StudentProfileDto getStudentProfileByStudentId(@Param("studentId") String studentId);

    /**
     * @param studentDto 学生数据传输类
     * @author KevenPotter
     * @date 2019-11-22 15:48:44
     * @description 插入一条新的[学生实体]
     */
    @Insert("INSERT INTO `student`.`student` (`student_id`, `department_id`, `major_id`, `grade`, `clazz`, `sex`, `name`, `age`, `address`, `addtime`) VALUES (#{studentDto.studentId}, #{studentDto.departmentId}, #{studentDto.majorId}, #{studentDto.grade}, #{studentDto.clazz}, #{studentDto.sex}, #{studentDto.name}, #{studentDto.age}, #{studentDto.address}, now());")
    @Options(useGeneratedKeys = true, keyProperty = "studentDto.id", keyColumn = "id")
    void addStudent(@Param("studentDto") StudentDto studentDto);

    /**
     * @param studentDto 学生数据传输类
     * @author KevenPotter
     * @date 2019-11-22 16:05:38
     * @description 更新[学生实体]
     */
    @Update("UPDATE `student`.`student` SET `student_id`=#{studentDto.studentId}, `department_id`=#{studentDto.departmentId}, `major_id`=#{studentDto.majorId}, `grade`=#{studentDto.grade}, `clazz`=#{studentDto.clazz}, `sex`=#{studentDto.sex}, `name`=#{studentDto.name}, `age`=#{studentDto.age}, `address`=#{studentDto.address} WHERE (`student_id`=#{studentDto.studentId});")
    @Options(useGeneratedKeys = true, keyProperty = "studentDto.id", keyColumn = "1")
    void updateStudent(@Param("studentDto") StudentDto studentDto);

    /**
     * @return 返回学生记录总条数
     * @author KevenPotter
     * @date 2019-12-20 16:45:46
     * @description 返回学生记录总条数
     */
    @Select("SELECT COUNT(*) FROM student;")
    Long getCount();

    /**
     * @return 返回各系部男女学生人数
     * @author KevenPotter
     * @date 2020-01-02 14:21:46
     * @description 统计各系部男女学生人数并将其返回
     */
    List<StudentSexStatisticsDto> getSexStatisticsByDepartment();
}

