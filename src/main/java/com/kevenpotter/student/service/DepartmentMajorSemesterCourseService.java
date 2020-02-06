package com.kevenpotter.student.service;

import com.kevenpotter.student.dao.DepartmentMajorSemesterCourseDao;
import com.kevenpotter.student.domain.dto.DepartmentMajorSemesterCourseDto;
import com.kevenpotter.student.domain.entity.DepartmentMajorSemesterCourseEntity;
import com.kevenpotter.student.utils.ListUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2020-02-04 09:58:57
 * @description 系部专业学期课程服务层类
 */
@Service
public class DepartmentMajorSemesterCourseService {

    /*定义日志记录器，用来记录必要信息*/
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DepartmentMajorSemesterCourseDao departmentMajorSemesterCourseDao;

    /**
     * @param departmentId 系别编号
     * @param majorId      专业编号
     * @param courseId     课程编号
     * @return 依据[系别编号]、[专业编号]和[课程编号]返回[系部专业学期课程]
     * @author KevenPotter
     * @date 2020-02-06 17:41:20
     * @description 依据[系别编号]、[专业编号]和[课程编号]返回[系部专业学期课程]
     */
    public DepartmentMajorSemesterCourseEntity getDepartmentMajorSemesterCourseByDepartmentIdAndMajorIdAndCourseId(Integer departmentId, Integer majorId, Integer courseId) {
        if (null == departmentId || null == majorId || null == courseId) return null;
        List<DepartmentMajorSemesterCourseEntity> departmentMajorSemesterCourseEntityList = departmentMajorSemesterCourseDao.getDepartmentMajorSemesterCourseByDepartmentIdAndMajorIdAndCourseId(departmentId, majorId, courseId);
        if (ListUtils.isEmpty(departmentMajorSemesterCourseEntityList)) return null;
        return departmentMajorSemesterCourseEntityList.get(0);
    }

    /**
     * @param departmentId 系别编号
     * @param majorId      专业编号
     * @param semester     学期
     * @param courseId     课程编号
     * @return 依据[系别编号]、[专业编号]、[学期]和[课程编号]返回[系部专业学期课程]
     * @author KevenPotter
     * @date 2020-02-05 23:42:43
     * @description 依据[系别编号]、[专业编号]、[学期]和[课程编号]返回[系部专业学期课程]
     */
    public DepartmentMajorSemesterCourseEntity getDepartmentMajorSemesterCourseByDepartmentIdAndMajorIdAndSemesterAndCourseId(Integer departmentId, Integer majorId, Integer semester, Integer courseId) {
        if (null == departmentId || null == majorId || null == semester || null == courseId) return null;
        List<DepartmentMajorSemesterCourseEntity> departmentMajorSemesterCourseEntityList = departmentMajorSemesterCourseDao.getDepartmentMajorSemesterCourseByDepartmentIdAndMajorIdAndSemesterAndCourseId(departmentId, majorId, semester, courseId);
        if (ListUtils.isEmpty(departmentMajorSemesterCourseEntityList)) return null;
        return departmentMajorSemesterCourseEntityList.get(0);
    }

    /**
     * @param departmentId 系别编号
     * @param majorId      专业编号
     * @param semester     学期
     * @param courseId     课程编号
     * @return 依据[系别编号]、[专业编号]、[学期]和[课程编号]判断是否已经存在了[系部专业学期课程]
     * @author KevenPotter
     * @date 2020-02-06 11:14:11
     * @description 依据[系别编号]、[专业编号]、[学期]和[课程编号]判断是否已经存在了[系部专业学期课程]
     */
    public boolean detectingDuplicateBindings(Integer departmentId, Integer majorId, Integer semester, String courseId) {
        DepartmentMajorSemesterCourseEntity departmentMajorSemesterCourseEntity = getDepartmentMajorSemesterCourseByDepartmentIdAndMajorIdAndSemesterAndCourseId(departmentId, majorId, semester, Integer.valueOf(courseId));
        return null == departmentMajorSemesterCourseEntity;
    }

    /**
     * @param departmentMajorSemesterCourseDtoList [系部专业学期课程数据传输类]列表
     * @return 批量插入[系部专业学期课程]并返回插入记录的数量
     * @author KevenPotter
     * @date 2020-02-06 20:52:24
     * @description 批量插入[系部专业学期课程]并返回插入记录的数量
     */
    public Integer batchAddDepartmentMajorSemesterCourse(List<DepartmentMajorSemesterCourseDto> departmentMajorSemesterCourseDtoList) {
        if (ListUtils.isEmpty(departmentMajorSemesterCourseDtoList)) return null;
        return departmentMajorSemesterCourseDao.batchAddDepartmentMajorSemesterCourse(departmentMajorSemesterCourseDtoList);
    }

}
