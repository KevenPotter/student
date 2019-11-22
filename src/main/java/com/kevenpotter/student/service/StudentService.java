package com.kevenpotter.student.service;

import com.kevenpotter.student.dao.StudentDao;
import com.kevenpotter.student.mapper.StudentEntity;
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
     * @param name 学生姓名
     * @return 返回一个[学生实体]
     * @author KevenPotter
     * @date 2019-11-22 11:34:13
     * @description 根据[学生姓名]查询[学生实体]
     */
    public StudentEntity findByName(String name) {
        if (StringUtils.isEmpty(name)) return null;
        return studentDao.findUserByName(name);
    }
}
