package com.kevenpotter.student.service;

import com.kevenpotter.student.dao.DepartmentDao;
import com.kevenpotter.student.domain.entity.DepartmentEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2019-12-06 16:57:31
 * @description 系别服务层类
 */
@Service
public class DepartmentService {

    /*定义日志记录器，用来记录必要信息*/
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DepartmentDao departmentDao;

    /**
     * @return 返回全部[系别实体类]列表
     * @author KevenPotter
     * @date 2019-12-06 17:00:45
     * @description 返回全部[系别实体类]列表
     */
    public List<DepartmentEntity> getAllDepartments() {
        return departmentDao.getAllDepartments();
    }
}
