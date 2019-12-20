package com.kevenpotter.student.service;

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
public class IndexService {

    /*定义日志记录器，用来记录必要信息*/
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private SystemUserService systemUserService;

    /**
     * @author KevenPotter
     * @date 2019-12-20 16:57:08
     * @description 返回首页的仪表盘展示数据
     */
    public void getDashBoard() {
        studentService.getTheTotalNumberOfStudents();
        teacherService.getTheTotalNumberOfTeachers();
        systemUserService.getTheTotalNumberOfAccounts();
    }
}
