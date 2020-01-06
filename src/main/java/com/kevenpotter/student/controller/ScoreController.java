package com.kevenpotter.student.controller;

import com.kevenpotter.student.domain.dto.ScoreProfileDto;
import com.kevenpotter.student.result.ApiConstant;
import com.kevenpotter.student.result.ApiResult;
import com.kevenpotter.student.service.ScoreService;
import com.kevenpotter.student.utils.ListUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2019-11-23 18:17:37
 * @description 课程控制层类
 */
@CrossOrigin
@RestController
@RequestMapping("score")
public class ScoreController {

    /*定义日志记录器，用来记录必要信息*/
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ScoreService scoreService;

    /**
     * @param studentId 学生编号
     * @return 返回一个结果集
     * @author KevenPotter
     * @date 2020-01-06 13:55:12
     * @description 根据[学生编号]和[学期]查询[分数详情数据传输类]集合
     */
    @GetMapping("/score/{studentId}/{semester}")
    public ApiResult getScoreByStudentId(@PathVariable Long studentId, @PathVariable Integer semester) {
        if (null == studentId || null == semester) return ApiResult.buildFailure(ApiConstant.CODE_1, "请求参数为空");
        List<ScoreProfileDto> scoreProfileDtoList = scoreService.getScoreByStudentId(studentId, semester);
        if (ListUtils.isEmpty(scoreProfileDtoList)) return ApiResult.buildFailure(ApiConstant.CODE_2, "未获取到分数信息");
        return ApiResult.buildSuccess(scoreProfileDtoList);
    }
}
