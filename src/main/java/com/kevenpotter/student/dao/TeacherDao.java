package com.kevenpotter.student.dao;

import com.github.pagehelper.Page;
import com.kevenpotter.student.domain.entity.TeacherEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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

    /**
     * @param teacherId    教师编号
     * @param name         教师姓名
     * @param departmentId 系别编号
     * @param majorId      专业编号
     * @param professional 教师职称
     * @return 依据指定条件查找规定范围内的[教师实体]列表
     * @author KevenPotter
     * @date 2020-01-14 11:19:01
     * @description 依据指定条件查找规定范围内的[教师实体]列表
     */
    Page<TeacherEntity> getTeachers(@Param("teacherId") Long teacherId, @Param("name") String name, @Param("departmentId") Integer departmentId, @Param("majorId") Integer majorId, @Param("professional") String professional);
}

