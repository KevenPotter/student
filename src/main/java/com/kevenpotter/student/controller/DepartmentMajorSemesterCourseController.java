package com.kevenpotter.student.controller;

import com.kevenpotter.student.domain.dto.DepartmentMajorSemesterCourseDto;
import com.kevenpotter.student.domain.entity.DepartmentMajorSemesterCourseEntity;
import com.kevenpotter.student.domain.entity.MajorEntity;
import com.kevenpotter.student.result.ApiConstant;
import com.kevenpotter.student.result.ApiResult;
import com.kevenpotter.student.service.DepartmentMajorSemesterCourseService;
import com.kevenpotter.student.service.MajorService;
import com.kevenpotter.student.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2020-02-04 09:57:07
 * @description 系部专业学期课程控制层类
 */
@CrossOrigin
@RestController
@RequestMapping("/departmentMajorSemesterCourse")
public class DepartmentMajorSemesterCourseController {

    /*定义日志记录器，用来记录必要信息*/
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DepartmentMajorSemesterCourseService departmentMajorSemesterCourseService;
    @Autowired
    private MajorService majorService;

    /**
     * @param majorId                    专业编号
     * @param majorAddCoursesArrayString 课程编号数组字符串
     * @return 返回一个结果集
     * @author KevenPotter
     * @date 2020-02-06 17:38:50
     * @description 依据[专业编号]和[课程编号]获取[系部专业学期课程]
     */
    @GetMapping("/departmentMajorSemesterCourses/{majorId}/{majorAddCoursesArrayString}")
    @ResponseBody
    public ApiResult getDepartmentMajorSemesterCourse(@PathVariable("majorId") Integer majorId, @PathVariable("majorAddCoursesArrayString") String majorAddCoursesArrayString) {
        if (null == majorId || StringUtils.isEmpty(majorAddCoursesArrayString))
            return ApiResult.buildFailure(ApiConstant.CODE_1, "请求参数为空");
        MajorEntity majorEntity = majorService.getMajorByMajorId(majorId);
        Integer departmentId = majorEntity.getDepartmentId();
        String[] courseIdsArray = majorAddCoursesArrayString.split(",");
        List<DepartmentMajorSemesterCourseEntity> existDepartmentMajorSemesterCourseEntityList = new ArrayList<DepartmentMajorSemesterCourseEntity>();
        for (String courseId : courseIdsArray) {
            DepartmentMajorSemesterCourseEntity departmentMajorSemesterCourseEntity = departmentMajorSemesterCourseService.getDepartmentMajorSemesterCourseByDepartmentIdAndMajorIdAndCourseId(departmentId, majorId, Integer.valueOf(courseId));
            if (null != departmentMajorSemesterCourseEntity) {
                existDepartmentMajorSemesterCourseEntityList.add(departmentMajorSemesterCourseEntity);
            }
        }
        return ApiResult.buildSuccess(existDepartmentMajorSemesterCourseEntityList);
    }

    /**
     * @param majorId                    专业编号
     * @param majorAddCoursesArrayString 课程编号数组字符串
     * @param semester                   学期
     * @return 返回一个结果集
     * @author KevenPotter
     * @date 2020-02-04 23:36:54
     * @description 添加系部专业学期课程
     */
    @PostMapping("/departmentMajorSemesterCourses")
    @ResponseBody
    public ApiResult addDepartmentMajorSemesterCourse(@RequestParam("majorId") Integer majorId, @RequestParam("majorAddCoursesArrayString") String majorAddCoursesArrayString, @RequestParam("semester") Integer semester) {
        if (null == majorId || StringUtils.isEmpty(majorAddCoursesArrayString) || null == semester)
            return ApiResult.buildFailure(ApiConstant.CODE_1, "请求参数为空");
        String[] majorAddCoursesArray = majorAddCoursesArrayString.split(",");
        MajorEntity majorEntity = majorService.getMajorByMajorId(majorId);
        Integer departmentId = majorEntity.getDepartmentId();
        ArrayList<DepartmentMajorSemesterCourseDto> departmentMajorSemesterCourseDtoArrayList = new ArrayList<DepartmentMajorSemesterCourseDto>();
        for (String courseId : majorAddCoursesArray) {
            if (departmentMajorSemesterCourseService.detectingDuplicateBindings(departmentId, majorId, semester, courseId)) {
                DepartmentMajorSemesterCourseDto departmentMajorSemesterCourseDto = new DepartmentMajorSemesterCourseDto();
                departmentMajorSemesterCourseDto.setDepartmentId(departmentId);
                departmentMajorSemesterCourseDto.setMajorId(majorId);
                departmentMajorSemesterCourseDto.setSemester(semester);
                departmentMajorSemesterCourseDto.setCourseId(Integer.valueOf(courseId));
                departmentMajorSemesterCourseDtoArrayList.add(departmentMajorSemesterCourseDto);
            } else {
                continue;
            }
        }
        Integer recordCounts = departmentMajorSemesterCourseService.batchAddDepartmentMajorSemesterCourse(departmentMajorSemesterCourseDtoArrayList);
        if (recordCounts > 0) {
            return ApiResult.buildSuccess(recordCounts);
        } else {
            return ApiResult.buildFailure(ApiConstant.CODE_5, "系部专业学期课程绑定失败,请稍后重试...");
        }
    }
}
