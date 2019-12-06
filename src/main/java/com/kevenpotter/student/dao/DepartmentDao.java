package com.kevenpotter.student.dao;

import com.kevenpotter.student.domain.entity.DepartmentEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2019-12-06 16:54:26
 * @description 系别持久层类
 */
@Repository
@Mapper
public interface DepartmentDao {

    /**
     * @return 返回全部[系别实体类]列表
     * @author KevenPotter
     * @date 2019-12-06 16:56:46
     * @description 返回全部[系别实体类]列表
     */
    @Select("SELECT * FROM department d;")
    List<DepartmentEntity> getAllDepartments();
}

