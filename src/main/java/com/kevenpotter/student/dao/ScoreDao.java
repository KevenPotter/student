package com.kevenpotter.student.dao;

import com.kevenpotter.student.domain.dto.ScoreDto;
import com.kevenpotter.student.domain.dto.ScoreProfileDto;
import com.kevenpotter.student.domain.entity.ScoreEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

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
     * @param id
     * @return
     * @date 2020-01-10 14:14:57
     */
    @Select("SELECT * FROM score s WHERE s.id = #{id}")
    ScoreEntity getScoreById(@Param("id") Long id);

    /**
     * @return
     * @date 2020-01-10 14:56:51
     */
    @Select("SELECT * FROM score s WHERE s.student_id = #{studentId} AND s.course_id = #{courseId} AND s.examination_category_id = #{examinationCategoryId} AND s.semester = #{semester}")
    ScoreEntity getScoreByStudentIdAndCourseIdAndExaminationCategoryIdAndSemester(@Param("studentId") Long studentId, @Param("courseId") Integer courseId, @Param("examinationCategoryId") Integer examinationCategoryId, @Param("semester") Integer semester);

    /**
     * @param studentId 学生编号
     * @param semester  学期
     * @return 返回一个[分数详情数据传输类]集合
     * @author KevenPotter
     * @date 2020-01-06 13:34:37
     * @description 根据[学生编号]和[学期]查询[分数详情数据传输类]集合
     */
    List<ScoreProfileDto> getScoreByStudentId(@Param("studentId") Long studentId, @Param("semester") Integer semester);

    /**
     * @param scoreDto
     * @return
     * @date 2020-01-10 14:09:44
     */
    @Update("INSERT INTO `student`.`score` (`student_id`, `course_id`, `examination_category_id`, `score`, `semester`) VALUES (#{scoreDto.studentId}, #{scoreDto.courseId}, #{scoreDto.examinationCategoryId}, #{scoreDto.score}, #{scoreDto.semester});")
    @Options(useGeneratedKeys = true, keyProperty = "scoreDto.id", keyColumn = "id")
    void addScore(@Param("scoreDto") ScoreDto scoreDto);
}

