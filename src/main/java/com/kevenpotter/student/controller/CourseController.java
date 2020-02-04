package com.kevenpotter.student.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kevenpotter.student.domain.dto.CourseDto;
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
     * @return 返回一个结果集
     * @author KevenPotter
     * @date 2020-02-03 10:36:00
     * @description 返回全部的[课程实体]列表
     */
    @ResponseBody
    @GetMapping("/courses")
    public ApiResult getAllCourses() {
        List<CourseEntity> courseEntityList = courseService.getAllCourses("course_name");
        if (ListUtils.isEmpty(courseEntityList)) return ApiResult.buildFailure(ApiConstant.CODE_3, "未获取到课程信息");
        return ApiResult.buildSuccess(courseEntityList);
    }

    /**
     * @param pageNo   当前页码
     * @param pageSize 分页大小
     * @return 返回一个结果集
     * @author KevenPotter
     * @date 2020-01-24 09:04:50
     * @description 返回[课程实体]列表
     */
    @ResponseBody
    @PatchMapping("/courses/{majorId}/{semester}")
    public ApiResult getCourses(
            @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            @PathVariable Integer majorId,
            @PathVariable Integer semester) {
        PageHelper.startPage(pageNo, pageSize);
        PageInfo<CourseEntity> pageInfo;
        if (-1 == majorId && 0 == semester) {
            pageInfo = new PageInfo<CourseEntity>(courseService.getAllCourses());
        } else if (-1 != majorId && 0 == semester) {
            pageInfo = new PageInfo<CourseEntity>(courseService.getCourses(null, majorId, null));
        } else {
            pageInfo = new PageInfo<CourseEntity>(courseService.getCoursesByMajorIdAndSemester(majorId, semester));
        }
        if (ListUtils.isEmpty(pageInfo.getList())) return ApiResult.buildFailure(ApiConstant.CODE_3, "未获取到课程信息");
        return ApiResult.buildSuccess(pageInfo);
    }

    /**
     * @param departmentId 系别编号
     * @param majorId      专业编号
     * @param semester     学期
     * @return 返回一个结果集
     * @author KevenPotter
     * @date 2020-01-09 10:47:41
     * @description 依据[系别编号]、[专业编号]、[学期]返回[课程实体]列表
     */
    @ResponseBody
    @GetMapping("/courses/{departmentId}/{majorId}/{semester}")
    public ApiResult getCoursesByDepartmentIdAndMajorIdAndSemester(@PathVariable Integer departmentId, @PathVariable Integer majorId, @PathVariable Integer semester) {
        if (null == departmentId || null == majorId || null == semester)
            return ApiResult.buildFailure(ApiConstant.CODE_1, "请求参数为空");
        List<CourseEntity> courseEntityList = courseService.getCoursesByDepartmentIdAndMajorIdAndSemester(departmentId, majorId, semester);
        if (ListUtils.isEmpty(courseEntityList)) return ApiResult.buildFailure(ApiConstant.CODE_3, "未获取到课程列表信息");
        return ApiResult.buildSuccess(courseEntityList);
    }

    /**
     * @param courseDto 课程数据传输类
     * @return 返回一个结果集
     * @author KevenPotter
     * @date 2020-01-27 20:04:50
     * @description 添加课程
     */
    @PostMapping("/courses")
    @ResponseBody
    public ApiResult addCourse(@RequestBody CourseDto courseDto) {
        if (null == courseDto)
            return ApiResult.buildFailure(ApiConstant.CODE_1, "请求参数为空");
        CourseEntity courseEntityByCourseId = courseService.getCourseByCourseId(courseDto.getCourseId());
        if (null != courseEntityByCourseId)
            return ApiResult.buildFailure(ApiConstant.CODE_4, "课程编号重复,请更换课程编号");
        CourseEntity courseEntityByCourseName = courseService.getCourseByMajorName(courseDto.getCourseName().trim());
        if (null != courseEntityByCourseName)
            return ApiResult.buildFailure(ApiConstant.CODE_4, "课程名称重复,请更换课程名称");
        CourseEntity courseEntity = courseService.addCourse(courseDto);
        if (null == courseEntity) return ApiResult.buildFailure(ApiConstant.CODE_3, "未成功添加课程信息");
        return ApiResult.buildSuccess(courseEntity);
    }

}
