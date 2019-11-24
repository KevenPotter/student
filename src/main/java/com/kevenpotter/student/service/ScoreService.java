package com.kevenpotter.student.service;

import com.kevenpotter.student.dao.ScoreDao;
import com.kevenpotter.student.domain.dto.ScoreDto;
import com.kevenpotter.student.domain.entity.ScoreEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2019-11-24 22:57:12
 * @description 分数服务层类
 */
@Service
public class ScoreService {

    /*定义日志记录器，用来记录必要信息*/
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ScoreDao scoreDao;

    /**
     * @param courseId              课程编号
     * @param examinationCategoryId 考试场次编号
     * @param studentId             学生编号
     * @return 返回一个[分数实体]
     * @author KevenPotter
     * @date 2019-11-24 23:31:28
     * @description 根据[课程编号]和[考试场次编号]和[学生编号]查询[分数实体]
     */
    public ScoreEntity getScore(Long courseId, Long examinationCategoryId, Long studentId) {
        if (null == courseId && null == examinationCategoryId && null == studentId) return null;
        return scoreDao.findScoreByCourseIdAndExaminationCategoryIdAndStudentId(courseId, examinationCategoryId, studentId);
    }

    /**
     * @param scoreDto 分数数据传输类
     * @return 返回插入的分数实体
     * @author KevenPotter
     * @date 2019-11-24 23:33:07
     * @description 插入一条新的[分数实体]并返回该[分数实体]
     */
    public ScoreEntity addScore(ScoreDto scoreDto) {
        if (null == scoreDto) return null;
        scoreDao.addScore(scoreDto);
        return this.getScore(scoreDto.getCourseId(), scoreDto.getExaminationCategoryId(), scoreDto.getStudentId());
    }

    /**
     * @param scoreDto 分数数据传输类
     * @return 返回更新之前的分数实体
     * @author KevenPotter
     * @date 2019-11-24 23:34:46
     * @description 更新[分数实体]并返回更新之前的[分数实体]
     */
    public ScoreEntity updateScore(ScoreDto scoreDto) {
        if (null == scoreDto) return null;
        ScoreEntity scoreEntity = scoreDao.findScoreByCourseIdAndExaminationCategoryIdAndStudentId(scoreDto.getCourseId(), scoreDto.getExaminationCategoryId(), scoreDto.getStudentId());
        scoreDao.updateScore(scoreDto);
        return scoreEntity;
    }

}
