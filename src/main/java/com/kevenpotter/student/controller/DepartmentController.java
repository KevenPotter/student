package com.kevenpotter.student.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kevenpotter.student.domain.dto.DepartmentDto;
import com.kevenpotter.student.domain.dto.DepartmentNestedPiesDto;
import com.kevenpotter.student.domain.entity.DepartmentEntity;
import com.kevenpotter.student.result.ApiConstant;
import com.kevenpotter.student.result.ApiResult;
import com.kevenpotter.student.service.DepartmentService;
import com.kevenpotter.student.utils.ListUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 系别控制层类
 *
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2019-12-06 17:01:34
 */
@CrossOrigin
@RestController
@RequestMapping("/department")
public class DepartmentController {

    /*定义日志记录器，用来记录必要信息*/
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DepartmentService departmentService;

    /**
     * @param pageNo   当前页码
     * @param pageSize 分页大小
     * @return 返回一个结果集
     * @author KevenPotter
     * @date 2020-01-16 16:12:28
     * @description
     */
    @ResponseBody
    @PatchMapping("/departments")
    public ApiResult getDepartments(
            @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        PageInfo<DepartmentEntity> pageInfo = new PageInfo<DepartmentEntity>(departmentService.getAllDepartments());
        if (ListUtils.isEmpty(pageInfo.getList())) return ApiResult.buildFailure(ApiConstant.CODE_3, "未获取到系部信息");
        return ApiResult.buildSuccess(pageInfo);
    }

    /**
     * @return 返回一个结果集
     * @author KevenPotter
     * @date 2019-12-06 17:12:33
     * @description 返回全部[系别实体类]列表
     */
    @ResponseBody
    @GetMapping("/departments")
    public ApiResult getAllDepartments() {
        List<DepartmentEntity> departmentEntityList = departmentService.getAllDepartments();
        if (ListUtils.isEmpty(departmentEntityList)) return ApiResult.buildFailure(ApiConstant.CODE_2, "未获取到系别信息");
        return ApiResult.buildSuccess(departmentEntityList);
    }

    /**
     * @param departmentId 系别编号
     * @return 返回一个结果集
     * @author KevenPotter
     * @date 2019-12-06 22:01:08
     * @description 根据[系别编号]返回[系别实体类]
     */
    @GetMapping("/department/{departmentId}")
    @ResponseBody
    public ApiResult getDepartmentById(@PathVariable Long departmentId) {
        if (null == departmentId) return ApiResult.buildFailure(ApiConstant.CODE_1, "请求参数为空");
        DepartmentEntity departmentEntity = departmentService.getDepartmentByDepartmentId(departmentId);
        if (null == departmentEntity) return ApiResult.buildFailure(ApiConstant.CODE_2, "未获取到系别信息");
        return ApiResult.buildSuccess(departmentEntity);
    }

    /**
     * @return 返回一个结果集
     * @author KevenPotter
     * @date 2020-02-08 17:12:26
     * @description 统计各系部专业数量并将其返回
     */
    @GetMapping("/departmentNestedPiesStatistics")
    @ResponseBody
    public ApiResult getDepartmentNestedPiesStatistics() {
        DepartmentNestedPiesDto departmentNestedPiesDto = departmentService.getDepartmentNestedPiesStatistics();
        if (null == departmentNestedPiesDto) return ApiResult.buildFailure(ApiConstant.CODE_3, "未获取到各系部专业数量信息");
        return ApiResult.buildSuccess(departmentNestedPiesDto);
    }

    /**
     * @param departmentDto 系别数据传输类
     * @return 返回一个结果集
     * @author KevenPotter
     * @date 2020-01-17 16:02:44
     * @description 添加系部
     */
    @PostMapping("/departments")
    @ResponseBody
    public ApiResult addDepartment(@RequestBody DepartmentDto departmentDto) {
        if (null == departmentDto)
            return ApiResult.buildFailure(ApiConstant.CODE_1, "请求参数为空");
        DepartmentEntity departmentEntityByDepartmentId = departmentService.getDepartmentByDepartmentId(departmentDto.getDepartmentId());
        if (null != departmentEntityByDepartmentId)
            return ApiResult.buildFailure(ApiConstant.CODE_4, "系部编号重复,请更换系部编号");
        DepartmentEntity departmentEntityByDepartmentName = departmentService.getDepartmentByDepartmentName(departmentDto.getDepartmentName().trim());
        if (null != departmentEntityByDepartmentName)
            return ApiResult.buildFailure(ApiConstant.CODE_4, "系部名称重复,请更换系部名称");
        DepartmentEntity departmentEntity = departmentService.addDepartment(departmentDto);
        if (null == departmentEntity) return ApiResult.buildFailure(ApiConstant.CODE_3, "未成功添加系部信息");
        return ApiResult.buildSuccess(departmentEntity);
    }
}
