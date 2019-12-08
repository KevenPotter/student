package com.kevenpotter.student.controller;

import com.kevenpotter.student.domain.entity.DepartmentEntity;
import com.kevenpotter.student.result.ApiConstant;
import com.kevenpotter.student.result.ApiResult;
import com.kevenpotter.student.service.DepartmentService;
import com.kevenpotter.student.utils.ListUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2019-12-06 17:01:34
 * @description 系别控制层类
 */
@CrossOrigin
@RestController
@RequestMapping("department")
public class DepartmentController {

    /*定义日志记录器，用来记录必要信息*/
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DepartmentService departmentService;

    /**
     * @return 返回全部[系别实体类]列表
     * @author KevenPotter
     * @date 2019-12-06 17:12:33
     * @description 返回全部[系别实体类]列表
     */
    @GetMapping("/departments")
    public ApiResult getAllDepartments() {
        List<DepartmentEntity> departmentEntityList = departmentService.getAllDepartments();
        if (ListUtils.isEmpty(departmentEntityList)) return ApiResult.buildFailure(ApiConstant.CODE_2, "未获取到系别信息");
        return ApiResult.buildSuccess(departmentEntityList);
    }

    /**
     * @param departmentId 系别编号
     * @return 根据[系别编号]返回[系别实体类]
     * @author KevenPotter
     * @date 2019-12-06 22:01:08
     * @description 根据[系别编号]返回[系别实体类]
     */
    @GetMapping("/department/{departmentId}")
    @ResponseBody
    public ApiResult getDepartmentById(@PathVariable Long departmentId) {
        if (null == departmentId) return ApiResult.buildFailure(ApiConstant.CODE_1, "请求参数为空");
        DepartmentEntity departmentEntity = departmentService.getDepartmentById(departmentId);
        if (null == departmentEntity) return ApiResult.buildFailure(ApiConstant.CODE_2, "未获取到系别信息");
        return ApiResult.buildSuccess(departmentEntity);
    }
}
