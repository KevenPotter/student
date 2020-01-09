package com.kevenpotter.student.controller;

import com.kevenpotter.student.domain.entity.CourseEntity;
import com.kevenpotter.student.result.ApiConstant;
import com.kevenpotter.student.result.ApiResult;
import com.kevenpotter.student.service.CourseService;
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
@RequestMapping("/course")
public class CourseController {

    /*定义日志记录器，用来记录必要信息*/
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CourseService courseService;

    /**
     * @param departmentId 系别编号
     * @param majorId      专业编号
     * @param semester     学期
     * @return 返回一个结果集
     * @author KevenPotter
     * @date 2020-01-09 10:47:41
     * @description 依据[系别编号]、[专业编号]、[学期]返回[课程实体]列表
     */
    @GetMapping("/courses/{departmentId}/{majorId}/{semester}")
    public ApiResult getCoursesByDepartmentIdAndMajorIdAndSemester(@PathVariable Integer departmentId, @PathVariable Integer majorId, @PathVariable Integer semester) {
        if (null == departmentId || null == majorId || null == semester)
            return ApiResult.buildFailure(ApiConstant.CODE_1, "请求参数为空");
        List<CourseEntity> courseEntityList = courseService.getCoursesByDepartmentIdAndMajorIdAndSemester(departmentId, majorId, semester);
        if (ListUtils.isEmpty(courseEntityList)) return ApiResult.buildFailure(ApiConstant.CODE_3, "未获取到课程列表信息");
        return ApiResult.buildSuccess(courseEntityList);
    }

}
