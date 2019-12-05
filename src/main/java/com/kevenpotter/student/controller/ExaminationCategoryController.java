package com.kevenpotter.student.controller;

import com.kevenpotter.student.domain.dto.ExaminationCategoryDto;
import com.kevenpotter.student.domain.entity.ExaminationCategoryEntity;
import com.kevenpotter.student.result.ApiConstant;
import com.kevenpotter.student.result.ApiResult;
import com.kevenpotter.student.service.ExaminationCategoryService;
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
@CrossOrigin
@RestController
@RequestMapping("examinationCategory")
public class ExaminationCategoryController {

    /*定义日志记录器，用来记录必要信息*/
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ExaminationCategoryService examinationCategoryService;

    /**
     * @param examinationCategoryId 考试场次编号
     * @param name                  考试场次名称
     * @return 返回一个结果集
     * @author KevenPotter
     * @date 2019-11-24 00:47:55
     * @description 根据[考试场次编号]或[考试场次名称]查询[考试类目实体]
     */
    @GetMapping("/examinationCategory")
    public ApiResult getExaminationCategory(@RequestParam(value = "examinationCategoryId", required = false) Long examinationCategoryId, @RequestParam(value = "name", required = false) String name) {
        if (null == examinationCategoryId && null == name) return ApiResult.buildFailure(ApiConstant.CODE_1, "请求参数为空");
        ExaminationCategoryEntity examinationCategoryEntity = examinationCategoryService.getExaminationCategory(examinationCategoryId, name);
        if (null == examinationCategoryEntity) return ApiResult.buildFailure(ApiConstant.CODE_2, "未获取到考试类目信息");
        return ApiResult.buildSuccess(examinationCategoryEntity);
    }

    /**
     * @param examinationCategoryDto 考试类目数据传输类
     * @return 返回一个结果集
     * @author KevenPotter
     * @date 2019-11-24 00:50:19
     * @description 插入一条新的[考试类目实体]并返回该[考试类目实体]
     */
    @PostMapping("/examinationCategory")
    public ApiResult addExaminationCategory(@RequestBody ExaminationCategoryDto examinationCategoryDto) {
        if (null == examinationCategoryDto) return ApiResult.buildFailure(ApiConstant.CODE_1, "请求参数为空");
        ExaminationCategoryEntity examinationCategoryEntity = examinationCategoryService.addExaminationCategory(examinationCategoryDto);
        if (null == examinationCategoryEntity)
            return ApiResult.buildFailure(ApiConstant.CODE_2, "未成功添加考试类目信息,考试类目信息可能已重复");
        return ApiResult.buildSuccess(examinationCategoryEntity);
    }

    /**
     * @param examinationCategoryDto 考试类目数据传输类
     * @return 返回一个结果集
     * @author KevenPotter
     * @date 2019-11-24 00:54:48
     * @description 更新[考试类目实体]并返回更新之前的[考试类目实体]
     */
    @PutMapping("/examinationCategory")
    public ApiResult updateExaminationCategory(@RequestBody ExaminationCategoryDto examinationCategoryDto) {
        if (null == examinationCategoryDto) return ApiResult.buildFailure(ApiConstant.CODE_1, "请求参数为空");
        ExaminationCategoryEntity examinationCategoryEntity = examinationCategoryService.updateExaminationCategory(examinationCategoryDto);
        if (null == examinationCategoryEntity) return ApiResult.buildFailure(ApiConstant.CODE_2, "未成功更新考试类目信息");
        return ApiResult.buildSuccess(examinationCategoryEntity);
    }
}
