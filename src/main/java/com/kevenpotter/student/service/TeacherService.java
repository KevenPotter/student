package com.kevenpotter.student.service;

import com.github.pagehelper.Page;
import com.kevenpotter.student.dao.TeacherDao;
import com.kevenpotter.student.domain.dto.TeacherProfileDto;
import com.kevenpotter.student.domain.entity.TeacherEntity;
import com.kevenpotter.student.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2019-12-12 15:46:18
 * @description 教师服务层类
 */
@Service
public class TeacherService {

    /*定义日志记录器，用来记录必要信息*/
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TeacherDao teacherDao;

    /**
     * @return 返回教师记录总条数
     * @author KevenPotter
     * @date 2019-12-20 16:23:57
     * @description 返回教师记录总条数
     */
    public Long getTheTotalNumberOfTeachers() {
        return teacherDao.getCount();
    }

    /**
     * @param teacherId    教师编号
     * @param name         教师姓名
     * @param departmentId 系别编号
     * @param majorId      专业编号
     * @param professional 教师职称
     * @return 依据指定条件查找规定范围内的[教师实体]列表
     * @author KevenPotter
     * @date 2020-01-14 11:14:34
     * @description 依据指定条件查找规定范围内的[教师实体]列表
     */
    public Page<TeacherEntity> getTeachers(String teacherId, String name, Integer departmentId, Integer majorId, String professional) {
        if (StringUtils.isEmpty(teacherId)) teacherId = null;
        else teacherId = teacherId.trim();
        if (StringUtils.isEmpty(name)) name = null;
        else name = name.trim();
        if (StringUtils.isEmpty(professional)) professional = null;
        else professional = professional.trim();
        return teacherDao.getTeachers(teacherId, name, departmentId, majorId, professional);
    }

    /**
     * @param teacherId 教师编号
     * @return 返回一个[教师详情数据传输类]
     * @author KevenPotter
     * @date 2020-01-14 16:08:49
     * @description 根据[教师编号]查询[教师详情数据传输类]
     */
    public TeacherProfileDto getTeacherProfileByTeacherId(String teacherId) {
        if (null == teacherId) return null;
        return teacherDao.getTeacherProfileByTeacherId(teacherId);
    }
}
