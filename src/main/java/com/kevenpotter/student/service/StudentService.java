package com.kevenpotter.student.service;

import com.github.pagehelper.Page;
import com.kevenpotter.student.dao.StudentDao;
import com.kevenpotter.student.domain.dto.StudentDto;
import com.kevenpotter.student.domain.dto.StudentProfileDto;
import com.kevenpotter.student.domain.dto.StudentSexStatisticsDto;
import com.kevenpotter.student.domain.entity.StudentEntity;
import com.kevenpotter.student.utils.ListUtils;
import com.kevenpotter.student.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2019-11-22 11:30:11
 * @description 学生服务层类
 */
@Service
public class StudentService {

    /*定义日志记录器，用来记录必要信息*/
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private StudentDao studentDao;

    /**
     * @param studentId 学生编号
     * @param name      学生姓名
     * @return 返回一个[学生实体]
     * @author KevenPotter
     * @date 2019-11-22 11:34:13
     * @description 根据[学生姓名]或[学生编号]查询[学生实体]
     */
    public Page<StudentEntity> getStudents(String studentId, String name, Integer departmentId, Integer majorId) {
        if (StringUtils.isEmpty(studentId)) studentId = null;
        else studentId = studentId.trim();
        if (StringUtils.isEmpty(name)) name = null;
        else name = name.trim();
        return studentDao.getStudents(studentId, name, departmentId, majorId);
    }

    /**
     * @param studentId 学生编号
     * @return 返回一个[学生详情数据传输类]
     * @author KevenPotter
     * @date 2020-01-03 14:54:49
     * @description 根据[学生编号]查询[学生详情数据传输类]
     */
    public StudentProfileDto getStudentProfileByStudentId(String studentId) {
        if (null == studentId) return null;
        return studentDao.getStudentProfileByStudentId(studentId);
    }

    /**
     * @param studentDto 学生数据传输类
     * @return 返回插入的学生实体
     * @author KevenPotter
     * @date 2019-11-22 15:37:30
     * @description 插入一条新的[学生实体]并返回该[学生实体]
     */
    public StudentEntity addStudent(StudentDto studentDto) {
        if (null == studentDto) return null;
        Page<StudentEntity> studentEntityList = this.getStudents(studentDto.getStudentId(), studentDto.getName(), studentDto.getDepartmentId(), studentDto.getMajorId());
        if (!ListUtils.isEmpty(studentEntityList)) return null;
        studentDao.addStudent(studentDto);
        return studentDao.getStudentById(studentDto.getId());
    }

    /**
     * @param studentDto 学生数据传输类
     * @return 返回更新之前的学生实体
     * @author KevenPotter
     * @date 2019-11-22 16:05:38
     * @description 更新[学生实体]并返回更新之前的[学生实体]
     */
    public StudentEntity updateStudent(StudentDto studentDto) {
        if (null == studentDto) return null;
        StudentEntity studentEntity = studentDao.getStudentByStudentId(studentDto.getStudentId());
        if (null == studentEntity) return null;
        studentDao.updateStudent(studentDto);
        return studentDao.getStudentById(studentDto.getId());
    }

    /**
     * @return 返回学生记录总条数
     * @author KevenPotter
     * @date 2019-12-20 16:48:03
     * @description 返回学生记录总条数
     */
    public Long getTheTotalNumberOfStudents() {
        return studentDao.getCount();
    }

    /**
     * @return 返回各系部男女学生人数
     * @author KevenPotter
     * @date 2020-01-02 14:18:13
     * @description 统计各系部男女学生人数并将其返回
     */
    public List<StudentSexStatisticsDto> getSexStatisticsByDepartment() {
        return studentDao.getSexStatisticsByDepartment();
    }

}
