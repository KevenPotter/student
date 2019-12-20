package com.kevenpotter.student.service;

import com.kevenpotter.student.dao.TeacherDao;
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
}
