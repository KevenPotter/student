package com.kevenpotter.student.service;

import com.kevenpotter.student.dao.StudentDao;
import com.kevenpotter.student.domain.dto.StudentDto;
import com.kevenpotter.student.domain.mapper.StudentEntity;
import com.kevenpotter.student.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2019-11-22 11:30:11
 * @description 学生服务层类
 */
@Service
public class StudentService {

    @Autowired
    private StudentDao studentDao;

    /**
     * @param name      学生姓名
     * @param studentId 学生编号
     * @return 返回一个[学生实体]
     * @author KevenPotter
     * @date 2019-11-22 11:34:13
     * @description 根据[学生姓名]或[学生编号]查询[学生实体]
     */
    public StudentEntity getStudent(Long studentId, String name) {
        if (null == studentId && StringUtils.isEmpty(name)) return null;
        if (null == studentId) return studentDao.findUserByName(name);
        if (StringUtils.isEmpty(name)) return studentDao.findUserById(studentId);
        return studentDao.findUserByIdAndName(studentId, name);
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
        studentDao.addStudent(studentDto);
        return this.getStudent(studentDto.getId(), studentDto.getName());
    }

}
