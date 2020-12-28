package com.kevenpotter.student.controller;

import com.kevenpotter.student.domain.dto.ScoreDto;
import com.kevenpotter.student.domain.dto.ScoreProfileDto;
import com.kevenpotter.student.domain.entity.ScoreEntity;
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
 * 课程控制层类
 *
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2019-11-23 18:17:37
 */
@CrossOrigin
@RestController
@RequestMapping("/score")
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
    @GetMapping("/scores/{studentId}/{semester}")
    @ResponseBody
    public ApiResult getScoreByStudentId(@PathVariable Long studentId, @PathVariable Integer semester) {
        if (null == studentId || null == semester) return ApiResult.buildFailure(ApiConstant.CODE_1, "请求参数为空");
        List<ScoreProfileDto> scoreProfileDtoList = scoreService.getScoreByStudentId(studentId, semester);
        if (ListUtils.isEmpty(scoreProfileDtoList)) return ApiResult.buildFailure(ApiConstant.CODE_2, "未获取到分数信息");
        return ApiResult.buildSuccess(scoreProfileDtoList);
    }

    /**
     * @return
     * @date 2020-01-10 13:53:06
     */
    @PostMapping("/score")
    @ResponseBody
    public ApiResult addScore(@RequestBody ScoreDto scoreDto) {
        if (null == scoreDto) return ApiResult.buildFailure(ApiConstant.CODE_1, "请求参数为空");
        ScoreEntity scoreEntity = scoreService.addScore(scoreDto);
        if (null == scoreEntity) return ApiResult.buildFailure(ApiConstant.CODE_3, "未成功添加成绩信息,成绩信息可能已重复");
        return ApiResult.buildSuccess(scoreEntity);
    }
}
