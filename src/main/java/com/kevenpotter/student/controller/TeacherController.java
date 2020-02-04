package com.kevenpotter.student.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kevenpotter.student.domain.dto.TeacherProfileDto;
import com.kevenpotter.student.domain.entity.TeacherEntity;
import com.kevenpotter.student.result.ApiConstant;
import com.kevenpotter.student.result.ApiResult;
import com.kevenpotter.student.service.TeacherService;
import com.kevenpotter.student.utils.ListUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2019-12-12 11:21:08
 * @description 教师控制层类
 */
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
@RestController
@RequestMapping("/teacher")
public class TeacherController {

    /*定义日志记录器，用来记录必要信息*/
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TeacherService teacherService;

    /**
     * @param teacherId    教师编号
     * @param name         教师姓名
     * @param departmentId 系别编号
     * @param majorId      专业编号
     * @param professional 教师职称
     * @param pageNo       当前页码
     * @param pageSize     分页大小
     * @return 返回一个结果集
     * @author KevenPotter
     * @date 2020-01-14 11:07:18
     * @description 依据指定条件查找规定范围内的[教师实体]列表
     */
    @ResponseBody
    @GetMapping("/teachers")
    public ApiResult getTeachers(
            @RequestParam(value = "teacherId", required = false) String teacherId,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "departmentId", required = false) Integer departmentId,
            @RequestParam(value = "majorId", required = false) Integer majorId,
            @RequestParam(value = "professional", required = false) String professional,
            @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        PageInfo<TeacherEntity> pageInfo = new PageInfo<TeacherEntity>(teacherService.getTeachers(teacherId, name, departmentId, majorId, professional));
        if (ListUtils.isEmpty(pageInfo.getList())) return ApiResult.buildFailure(ApiConstant.CODE_2, "未获取到教师信息");
        return ApiResult.buildSuccess(pageInfo);
    }

    /**
     * @param teacherId 教师编号
     * @return 返回一个结果集
     * @author KevenPotter
     * @date 2020-01-14 15:46:52
     * @description 根据[教师编号]查询[教师详情数据传输类]
     */
    @ResponseBody
    @GetMapping("/teacher/{teacherId}")
    public ApiResult getTeacher(@PathVariable String teacherId) {
        if (null == teacherId) return ApiResult.buildFailure(ApiConstant.CODE_1, "请求参数为空");
        TeacherProfileDto teacherProfileDto = teacherService.getTeacherProfileByTeacherId(teacherId);
        if (null == teacherProfileDto) return ApiResult.buildFailure(ApiConstant.CODE_2, "未获取到教师信息");
        return ApiResult.buildSuccess(teacherProfileDto);
    }
}
