package com.kevenpotter.student.controller;

import com.kevenpotter.student.domain.entity.MajorEntity;
import com.kevenpotter.student.result.ApiConstant;
import com.kevenpotter.student.result.ApiResult;
import com.kevenpotter.student.service.MajorService;
import com.kevenpotter.student.utils.ListUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2019-12-09 10:03:56
 * @description 专业控制层类
 */
@CrossOrigin
@RestController
@RequestMapping("major")
public class MajorController {

    /*定义日志记录器，用来记录必要信息*/
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MajorService majorService;

    /**
     * @return 返回一个结果集
     * @author KevenPotter
     * @date 2019-12-09 10:05:53
     * @description 返回全部[专业实体类]列表
     */
    @GetMapping("/majors")
    @ResponseBody
    public ApiResult getAllMajors() {
        List<MajorEntity> majorEntityList = majorService.getAllMajors();
        if (ListUtils.isEmpty(majorEntityList)) return ApiResult.buildFailure(ApiConstant.CODE_2, "未获取到专业信息");
        return ApiResult.buildSuccess(majorEntityList);
    }

    /**
     * @param departmentId 系别编号
     * @return 返回一个结果集
     * @author KevenPotter
     * @date 2019-12-09 10:07:08
     * @description 根据[系别编号]返回[专业实体类]列表
     */
    @GetMapping("/majors/{departmentId}")
    @ResponseBody
    public ApiResult getMajorsByDepartmentId(@PathVariable Integer departmentId) {
        if (null == departmentId) return ApiResult.buildFailure(ApiConstant.CODE_1, "请求参数为空");
        List<MajorEntity> majorEntityList = majorService.getMajorsByDepartmentId(departmentId);
        if (ListUtils.isEmpty(majorEntityList)) return ApiResult.buildFailure(ApiConstant.CODE_2, "未获取到专业信息");
        return ApiResult.buildSuccess(majorEntityList);
    }

    /**
     * @param majorId 专业编号
     * @return 返回一个结果集
     * @author KevenPotter
     * @date 2019-12-10 09:30:41
     * @description 根据[专业编号]返回[专业实体类]
     */
    @GetMapping("/major/{majorId}")
    @ResponseBody
    public ApiResult getMajorByMajorId(@PathVariable Integer majorId) {
        if (null == majorId) return ApiResult.buildFailure(ApiConstant.CODE_1, "请求参数为空");
        MajorEntity majorEntity = majorService.getMajorByMajorId(majorId);
        if (null == majorEntity) return ApiResult.buildFailure(ApiConstant.CODE_2, "未获取到专业信息");
        return ApiResult.buildSuccess(majorEntity);
    }
}
