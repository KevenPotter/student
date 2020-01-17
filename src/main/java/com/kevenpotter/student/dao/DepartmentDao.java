package com.kevenpotter.student.dao;

import com.kevenpotter.student.domain.dto.DepartmentDto;
import com.kevenpotter.student.domain.entity.DepartmentEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2019-12-06 16:54:26
 * @description 系别持久层类
 */
@Repository
@Mapper
public interface DepartmentDao {

    /**
     * @return 返回全部[系别实体类]列表
     * @author KevenPotter
     * @date 2019-12-06 16:56:46
     * @description 返回全部[系别实体类]列表
     */
    @Select("SELECT * FROM department d")
    List<DepartmentEntity> getAllDepartments();

    /**
     * @param id 主键ID
     * @return 根据[主键ID]返回[系别实体类]
     * @author KevenPotter
     * @date 2020-01-17 16:56:24
     * @description 根据[主键ID]返回[系别实体类]
     */
    @Select("SELECT * FROM department d WHERE d.id=#{id}")
    DepartmentEntity getDepartmentById(@Param("id") Long id);

    /**
     * @param departmentId 系别编号
     * @return 根据[系别编号]返回[系别实体类]
     * @author KevenPotter
     * @date 2019-12-06 21:53:10
     * @description 根据[系别编号]返回[系别实体类]
     */
    @Select("SELECT * FROM department d WHERE d.department_id=#{departmentId}")
    DepartmentEntity getDepartmentByDepartmentId(@Param("departmentId") Long departmentId);

    /**
     * @param departmentName 系部名称
     * @return 根据[系部名称]返回[系别实体类]
     * @author KevenPotter
     * @date 2020-01-17 16:05:16
     * @description 根据[系部名称]返回[系别实体类]
     */
    @Select("SELECT * FROM department d WHERE d.department_name=#{departmentName}")
    DepartmentEntity getDepartmentByDepartmentName(@Param("departmentName") String departmentName);

    /**
     * @param departmentDto 系别数据传输类
     * @return 插入一条新的[系别实体]并返回该[系别实体]
     * @author KevenPotter
     * @date 2020-01-17 16:58:59
     * @description 插入一条新的[系别实体]并返回该[系别实体]
     */
    @Insert("INSERT INTO `student`.`department` (`department_id`, `department_name`) VALUES (#{departmentDto.departmentId}, #{departmentDto.departmentName});")
    @Options(useGeneratedKeys = true, keyProperty = "departmentDto.id", keyColumn = "id")
    void addDepartment(@Param("departmentDto") DepartmentDto departmentDto);
}

