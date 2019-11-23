package com.kevenpotter.student.dao;

import com.kevenpotter.student.domain.dto.ExaminationCategoryDto;
import com.kevenpotter.student.domain.entity.ExaminationCategoryEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2019-11-23 23:34:42
 * @description 考试类目持久层类
 */
@Repository
@Mapper
public interface ExaminationCategoryDao {

    /**
     * @param name 考试场次名称
     * @return 返回一个[考试类目实体]
     * @author KevenPotter
     * @date 2019-11-23 23:50:14
     * @description 根据[考试场次名称]查询[考试类目实体]
     */
    @Select("SELECT * FROM examination_category ec WHERE ec.name = #{name}")
    ExaminationCategoryEntity findExaminationCategoryByName(@Param("name") String name);

    /**
     * @param examinationCategoryId 考试场次编号
     * @return 返回一个[课程实体]
     * @author KevenPotter
     * @date 2019-11-24 00:19:09
     * @description 根据[考试场次编号]查询[考试类目实体]
     */
    @Select("SELECT * FROM examination_category ec WHERE ec.id = #{examinationCategoryId}")
    ExaminationCategoryEntity findExaminationCategoryById(@Param("examinationCategoryId") Long examinationCategoryId);

    /**
     * @param examinationCategoryId 考试场次编号
     * @param name                  考试场次名称
     * @return 返回一个[考试类目实体]
     * @author KevenPotter
     * @date 2019-11-24 00:20:15
     * @description 根据[考试场次编号]和[考试场次名称]查询[考试类目实体]
     */
    @Select("SELECT * FROM examination_category ec WHERE ec.id = #{examinationCategoryId} AND ec.name = #{name};")
    ExaminationCategoryEntity findExaminationCategoryByIdAndName(@Param("examinationCategoryId") Long examinationCategoryId, @Param("name") String name);

    /**
     * @param examinationCategoryDto 考试类目数据传输类
     * @author KevenPotter
     * @date 2019-11-24 00:21:12
     * @description 插入一条新的[考试类目实体]
     */
    @Insert("INSERT INTO `student`.`examination_category` (`id`, `name`, `ratio`) VALUES (#{examinationCategoryDto.id}, #{examinationCategoryDto.name}, #{examinationCategoryDto.ratio});")
    void addExaminationCategory(@Param("examinationCategoryDto") ExaminationCategoryDto examinationCategoryDto);

    /**
     * @param examinationCategoryDto 考试类目数据传输类
     * @author KevenPotter
     * @date 2019-11-24 00:23:12
     * @description 更新[考试类目实体]
     */
    @Update("UPDATE `student`.`examination_category` SET `id`=#{examinationCategoryDto.id}, `name`=#{examinationCategoryDto.name}, `ratio`=#{examinationCategoryDto.ratio} WHERE (`id`=#{examinationCategoryDto.id});")
    void updateExaminationCategory(@Param("examinationCategoryDto") ExaminationCategoryDto examinationCategoryDto);
}

