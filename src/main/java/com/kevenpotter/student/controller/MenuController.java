package com.kevenpotter.student.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kevenpotter.student.domain.dto.StudentDto;
import com.kevenpotter.student.domain.dto.StudentProfileDto;
import com.kevenpotter.student.domain.entity.StudentEntity;
import com.kevenpotter.student.domain.entity.SystemMenuEntity;
import com.kevenpotter.student.result.ApiConstant;
import com.kevenpotter.student.result.ApiResult;
import com.kevenpotter.student.service.MenuService;
import com.kevenpotter.student.service.StudentService;
import com.kevenpotter.student.utils.ListUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 菜单控制层类
 *
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2020-12-28 15：36：09
 */
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
@RestController
@RequestMapping("/menu")
public class MenuController {

    /*定义日志记录器，用来记录必要信息*/
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private StudentService studentService;
    @Autowired
    private MenuService menuService;

    /**
     * 获取[系统菜单实体类]
     *
     * @param pageNo   当前页码
     * @param pageSize 分页大小
     * @return 返回一个结果集
     * @author KevenPotter
     * @date 2020-12-28 15:42:02
     */
    @ResponseBody
    @GetMapping("/menus")
    public ApiResult getMenus(
            @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        PageHelper.startPage(pageNo, pageSize);
        PageInfo<SystemMenuEntity> pageInfo = new PageInfo<SystemMenuEntity>(menuService.getMenus());
        if (ListUtils.isEmpty(pageInfo.getList())) return ApiResult.buildFailure(ApiConstant.CODE_2, "未获取到菜单信息");
        return ApiResult.buildSuccess(pageInfo);
    }

    /**
     * @param studentId 学生编号
     * @return 返回一个结果集
     * @author KevenPotter
     * @date 2020-01-03 14:56:47
     * @description 根据[学生编号]查询[学生实体]
     */
    @ResponseBody
    @GetMapping("/student/{studentId}")
    public ApiResult getStudent(@PathVariable String studentId) {
        if (null == studentId) return ApiResult.buildFailure(ApiConstant.CODE_1, "请求参数为空");
        StudentProfileDto studentProfileDto = studentService.getStudentProfileByStudentId(studentId);
        if (null == studentProfileDto) return ApiResult.buildFailure(ApiConstant.CODE_2, "未获取到学生信息");
        return ApiResult.buildSuccess(studentProfileDto);
    }

    /**
     * @param studentDto 学生数据传输类
     * @return 返回一个结果集
     * @author KevenPotter
     * @date 2019-11-22 13:34:21
     * @description 插入一条新的[学生实体]并返回该[学生实体]
     */
    @PostMapping("/student")
    public ApiResult addStudent(@RequestBody StudentDto studentDto) {
        if (null == studentDto) return ApiResult.buildFailure(ApiConstant.CODE_1, "请求参数为空");
        StudentEntity studentEntity = studentService.addStudent(studentDto);
        if (null == studentEntity) return ApiResult.buildFailure(ApiConstant.CODE_2, "未成功添加学生信息,学生信息可能已重复");
        return ApiResult.buildSuccess(studentEntity);
    }

    /**
     * @param studentDto 学生数据传输类
     * @return 返回一个结果集
     * @author KevenPotter
     * @date 2019-11-22 16:07:38
     * @description 更新[学生实体]并返回更新之前的[学生实体]
     */
    @PutMapping("/student")
    public ApiResult updateStudent(@RequestBody StudentDto studentDto) {
        if (null == studentDto) return ApiResult.buildFailure(ApiConstant.CODE_1, "请求参数为空");
        StudentEntity studentEntity = studentService.updateStudent(studentDto);
        if (null == studentEntity) return ApiResult.buildFailure(ApiConstant.CODE_2, "未成功更新学生信息,学生信息可能不存在");
        return ApiResult.buildSuccess(studentEntity);
    }
}
