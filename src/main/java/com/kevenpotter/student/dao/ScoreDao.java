package com.kevenpotter.student.dao;

import com.kevenpotter.student.domain.dto.ScoreProfileDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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
     * @param studentId 学生编号
     * @param semester  学期
     * @return 返回一个[分数详情数据传输类]集合
     * @author KevenPotter
     * @date 2020-01-06 13:34:37
     * @description 根据[学生编号]和[学期]查询[分数详情数据传输类]集合
     */
    List<ScoreProfileDto> getScoreByStudentId(@Param("studentId") Long studentId, @Param("semester") Integer semester);
}

