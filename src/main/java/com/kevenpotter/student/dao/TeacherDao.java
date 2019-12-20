package com.kevenpotter.student.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2019-12-20 16:12:54
 * @description 教师持久层类
 */
@Repository
@Mapper
public interface TeacherDao {

    /**
     * @return 返回教师记录总条数
     * @author KevenPotter
     * @date 2019-12-20 16:16:16
     * @description 返回教师记录总条数
     */
    @Select("SELECT COUNT(*) FROM teacher;")
    Long getCount();
}

