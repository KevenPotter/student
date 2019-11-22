package com.kevenpotter.student.controller;

import com.kevenpotter.student.mapper.StudentEntity;
import com.kevenpotter.student.result.ApiConstant;
import com.kevenpotter.student.result.ApiResult;
import com.kevenpotter.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2019-11-22 11:26:36
 * @description 学生控制层类
 */
@RestController
@RequestMapping("student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    /**
     * @param name 学生姓名
     * @return 返回一个结果集
     * @author KevenPotter
     * @date 2019-11-22 11:34:46
     * @description 根据[学生姓名]查询[学生实体]
     */
    @GetMapping("/student")
    public ApiResult findByName(@RequestParam(value = "studentId", required = false) Long studentId, @RequestParam(value = "name", required = false) String name) {
        if (null == studentId && null == name) return ApiResult.buildFailure(ApiConstant.CODE_1, "请求参数为空");
        StudentEntity studentEntity = studentService.getStudent(studentId, name);
        if (studentEntity == null) return ApiResult.buildFailure(ApiConstant.CODE_2, "未获取到用户信息");
        return ApiResult.buildSuccess(studentEntity);
    }
}
