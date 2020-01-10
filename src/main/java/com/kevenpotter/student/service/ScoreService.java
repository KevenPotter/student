package com.kevenpotter.student.service;

import com.kevenpotter.student.dao.ScoreDao;
import com.kevenpotter.student.domain.dto.ScoreDto;
import com.kevenpotter.student.domain.dto.ScoreProfileDto;
import com.kevenpotter.student.domain.entity.ScoreEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
     * @param studentId 学生编号
     * @param semester  学期
     * @return 返回一个[分数详情数据传输类]集合
     * @author KevenPotter
     * @date 2020-01-06 13:53:09
     * @description 根据[学生编号]和[学期]查询[分数详情数据传输类]集合
     */
    public List<ScoreProfileDto> getScoreByStudentId(Long studentId, Integer semester) {
        if (null == studentId || null == semester) return null;
        return scoreDao.getScoreByStudentId(studentId, semester);
    }

    /**
     * @param scoreDto
     * @return
     * @date 2020-01-10 14:06:58
     */
    public ScoreEntity addScore(ScoreDto scoreDto) {
        if (null == scoreDto) return null;
        ScoreEntity existScoreEntity = scoreDao.getScoreByStudentIdAndCourseIdAndExaminationCategoryIdAndSemester(scoreDto.getStudentId(), scoreDto.getCourseId(), scoreDto.getExaminationCategoryId(), scoreDto.getSemester());
        if (null != existScoreEntity) return null;
        scoreDao.addScore(scoreDto);
        ScoreEntity scoreEntity = scoreDao.getScoreById(scoreDto.getId());
        if (null == scoreEntity) return null;
        return scoreEntity;
    }
}
