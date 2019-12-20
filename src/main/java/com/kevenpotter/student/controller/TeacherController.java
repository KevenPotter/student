package com.kevenpotter.student.controller;

import com.kevenpotter.student.service.TeacherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2019-12-12 11:21:08
 * @description 教师控制层类
 */
@CrossOrigin
@RestController
@RequestMapping("/teacher")
public class TeacherController {

    /*定义日志记录器，用来记录必要信息*/
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TeacherService teacherService;
}
