package com.kevenpotter.student.dao;

import com.kevenpotter.student.domain.dto.ScoreDto;
import com.kevenpotter.student.domain.entity.ScoreEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2019-11-24 18:27:02
 * @description 分数持久层类
 */
@Repository
@Mapper
public interface ScoreDao {

    /**
     * @param courseId              课程编号
     * @param examinationCategoryId 考试场次编号
     * @param studentId             学生编号
     * @return 返回一个[分数实体]
     * @author KevenPotter
     * @date 2019-11-24 18:32:18
     * @description 根据[课程编号]和[考试场次编号]和[学生编号]查询[分数实体]
     */
    @Select("SELECT * FROM score s WHERE s.course_id = #{courseId} AND s.examination_category_id = #{examinationCategoryId} AND s.student_id = #{studentId}")
    ScoreEntity findScoreByCourseIdAndExaminationCategoryIdAndStudentId(@Param("courseId") Long courseId, @Param("examinationCategoryId") Long examinationCategoryId, @Param("studentId") Long studentId);

    /**
     * @param scoreDto 分数数据传输类
     * @author KevenPotter
     * @date 2019-11-24 18:39:25
     * @description 插入一条新的[分数实体]
     */
    @Insert("INSERT INTO `student`.`score` (`course_id`, `student_id`, `examination_category_id`, `score`) VALUES (#{scoreDto.courseId}, #{scoreDto.studentId}, #{scoreDto.examinationCategoryId}, #{scoreDto.score});")
    void addScore(@Param("scoreDto") ScoreDto scoreDto);

    /**
     * @param scoreDto 分数数据传输类
     * @author KevenPotter
     * @date 2019-11-24 18:44:17
     * @description 更新[分数实体]
     */
    @Update("UPDATE `student`.`score` SET `score`=#{scoreDto.score} WHERE (`course_id`=#{scoreDto.courseId}) AND (`student_id`=#{scoreDto.studentId}) AND (`examination_category_id`=#{scoreDto.examinationCategoryId});")
    void updateScore(@Param("scoreDto") ScoreDto scoreDto);
}

