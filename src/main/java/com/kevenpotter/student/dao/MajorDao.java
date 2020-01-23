package com.kevenpotter.student.dao;

import com.kevenpotter.student.domain.dto.MajorDto;
import com.kevenpotter.student.domain.entity.MajorEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2019-12-09 09:40:57
 * @description 专业持久层类
 */
@Repository
@Mapper
public interface MajorDao {

    /**
     * @return 返回全部[专业实体类]列表
     * @author KevenPotter
     * @date 2019-12-09 09:52:49
     * @description 返回全部[专业实体类]列表
     */
    @Select("SELECT * FROM major m ")
    List<MajorEntity> getAllMajors();

    /**
     * @param departmentId 系别编号
     * @return 根据[系别编号]返回[专业实体类]列表
     * @author KevenPotter
     * @date 2019-12-09 10:00:26
     * @description 根据[系别编号]返回[系别实体类]列表
     */
    @Select("SELECT * FROM major m WHERE m.department_id=#{departmentId}")
    List<MajorEntity> getMajorsByDepartmentId(@Param("departmentId") Integer departmentId);

    /**
     * @param id 主键ID
     * @return 根据[主键ID]返回[专业实体类]
     * @author KevenPotter
     * @date 2020-01-22 11:35:29
     * @description 根据[主键ID]返回[专业实体类]
     */
    @Select("SELECT * FROM major m WHERE m.id=#{id}")
    MajorEntity getMajorById(@Param("id") Long id);

    /**
     * @param majorId 专业编号
     * @return 根据[专业编号]返回[专业实体类]
     * @author KevenPotter
     * @date 2019-12-10 09:26:28
     * @description 根据[专业编号]返回[专业实体类]
     */
    @Select("SELECT * FROM major m WHERE m.major_id=#{majorId}")
    MajorEntity getMajorByMajorId(@Param("majorId") Integer majorId);

    /**
     * @param majorName 专业名称
     * @return 根据[专业名称]返回[专业实体类]
     * @author KevenPotter
     * @date 2020-01-22 10:59:53
     * @description 根据[专业名称]返回[专业实体类]
     */
    @Select("SELECT * FROM major m WHERE m.major_name=#{majorName}")
    MajorEntity getMajorByMajorName(@Param("majorName") String majorName);

    /**
     * @param majorDto 专业数据传输类
     * @author KevenPotter
     * @date 2020-01-22 11:38:57
     * @description 插入一条新的[专业实体]并返回该[专业实体]
     */
    @Insert("INSERT INTO `student`.`major` (`major_id`, `major_name`, `department_id`) VALUES (#{majorDto.majorId}, #{majorDto.majorName}, #{majorDto.departmentId});")
    @Options(useGeneratedKeys = true, keyProperty = "majorDto.id", keyColumn = "id")
    void addMajor(@Param("majorDto") MajorDto majorDto);
}

