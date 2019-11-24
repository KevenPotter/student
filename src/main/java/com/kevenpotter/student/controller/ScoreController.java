package com.kevenpotter.student.controller;

import com.kevenpotter.student.domain.dto.ScoreDto;
import com.kevenpotter.student.domain.entity.ScoreEntity;
import com.kevenpotter.student.result.ApiConstant;
import com.kevenpotter.student.result.ApiResult;
import com.kevenpotter.student.service.ScoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2019-11-23 18:17:37
 * @description 课程控制层类
 */
@RestController
@RequestMapping("score")
public class ScoreController {

    /*定义日志记录器，用来记录必要信息*/
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ScoreService scoreService;

    /**
     * @param courseId              课程编号
     * @param examinationCategoryId 考试场次编号
     * @param studentId             学生编号
     * @return 返回一个结果集
     * @author KevenPotter
     * @date 2019-11-24 23:38:37
     * @description 根据[课程编号]和[考试场次编号]和[学生编号]查询[分数实体]
     */
    @GetMapping("/score")
    public ApiResult getScore(@RequestParam(value = "courseId") Long courseId, @RequestParam(value = "examinationCategoryId") Long examinationCategoryId, @RequestParam(value = "studentId") Long studentId) {
        if (null == courseId && null == examinationCategoryId && null == studentId)
            return ApiResult.buildFailure(ApiConstant.CODE_1, "请求参数为空");
        ScoreEntity scoreEntity = scoreService.getScore(courseId, examinationCategoryId, studentId);
        if (null == scoreEntity) return ApiResult.buildFailure(ApiConstant.CODE_2, "未获取到分数信息");
        return ApiResult.buildSuccess(scoreEntity);
    }

    /**
     * @param scoreDto 分数数据传输类
     * @return 返回一个结果集
     * @author KevenPotter
     * @date 2019-11-24 23:40:57
     * @description 插入一条新的[分数实体]并返回该[分数实体]
     */
    @PostMapping("/score")
    public ApiResult addScore(@RequestBody ScoreDto scoreDto) {
        if (null == scoreDto) return ApiResult.buildFailure(ApiConstant.CODE_1, "请求参数为空");
        ScoreEntity scoreEntity = scoreService.addScore(scoreDto);
        if (null == scoreEntity) return ApiResult.buildFailure(ApiConstant.CODE_2, "未成功添加分数信息");
        return ApiResult.buildSuccess(scoreEntity);
    }

    /**
     * @param scoreDto 分数数据传输类
     * @return 返回一个结果集
     * @author KevenPotter
     * @date 2019-11-24 23:44:22
     * @description 更新[分数实体]并返回更新之前的[分数实体]
     */
    @PutMapping("/score")
    public ApiResult updateScore(@RequestBody ScoreDto scoreDto) {
        if (null == scoreDto) return ApiResult.buildFailure(ApiConstant.CODE_1, "请求参数为空");
        ScoreEntity scoreEntity = scoreService.updateScore(scoreDto);
        if (null == scoreEntity) return ApiResult.buildFailure(ApiConstant.CODE_2, "未成功更新分数信息");
        return ApiResult.buildSuccess(scoreEntity);
    }
}
