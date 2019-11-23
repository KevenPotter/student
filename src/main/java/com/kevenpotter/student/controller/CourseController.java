package com.kevenpotter.student.controller;

import com.kevenpotter.student.domain.dto.CourseDto;
import com.kevenpotter.student.domain.entity.CourseEntity;
import com.kevenpotter.student.result.ApiConstant;
import com.kevenpotter.student.result.ApiResult;
import com.kevenpotter.student.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2019-11-23 18:17:37
 * @description 课程控制层类
 */
@RestController
@RequestMapping("course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    /**
     * @param courseId 课程编号
     * @param name     课程名称
     * @return 返回一个结果集
     * @author KevenPotter
     * @date 2019-11-23 18:18:08
     * @description 根据[课程名称]查询[课程实体]
     */
    @GetMapping("/course")
    public ApiResult getCourse(@RequestParam(value = "courseId", required = false) Long courseId, @RequestParam(value = "name", required = false) String name) {
        if (null == courseId && null == name) return ApiResult.buildFailure(ApiConstant.CODE_1, "请求参数为空");
        CourseEntity courseEntity = courseService.getCourse(courseId, name);
        if (null == courseEntity) return ApiResult.buildFailure(ApiConstant.CODE_2, "未获取到课程信息");
        return ApiResult.buildSuccess(courseEntity);
    }

    /**
     * @param courseDto 课程数据传输类
     * @return 返回一个结果集
     * @author KevenPotter
     * @date 2019-11-23 18:22:21
     * @description 插入一条新的[课程实体]并返回该[课程实体]
     */
    @PostMapping("/course")
    public ApiResult addCourse(@RequestBody CourseDto courseDto) {
        if (null == courseDto) return ApiResult.buildFailure(ApiConstant.CODE_1, "请求参数为空");
        CourseEntity courseEntity = courseService.addCourse(courseDto);
        if (null == courseEntity) return ApiResult.buildFailure(ApiConstant.CODE_2, "未成功添加课程信息");
        return ApiResult.buildSuccess(courseEntity);
    }

    /**
     * @param courseDto 课程数据传输类
     * @return 返回一个结果集
     * @author KevenPotter
     * @date 2019-11-23 18:23:29
     * @description 更新[课程实体]并返回更新之前的[课程实体]
     */
    @PutMapping("/course")
    public ApiResult updateCourse(@RequestBody CourseDto courseDto) {
        if (null == courseDto) return ApiResult.buildFailure(ApiConstant.CODE_1, "请求参数为空");
        CourseEntity courseEntity = courseService.updateStudent(courseDto);
        if (null == courseEntity) return ApiResult.buildFailure(ApiConstant.CODE_2, "未成功更新课程信息");
        return ApiResult.buildSuccess(courseEntity);
    }
}
