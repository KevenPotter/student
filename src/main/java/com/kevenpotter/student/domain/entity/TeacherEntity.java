package com.kevenpotter.student.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 教师实体类
 *
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2019-12-20 15:27:16
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class TeacherEntity implements Serializable {

    /*主键ID*/
    private Long id;
    /*教师编号*/
    private String teacherId;
    /*教师所属系别*/
    private Integer departmentId;
    /*教师所属专业*/
    private Integer majorId;
    /*教师职称*/
    private String professional;
    /*教师姓名*/
    private String name;
    /*教师年龄*/
    private Integer age;
    /*教师性别*/
    private String sex;
    /*教师手机号码*/
    private Long mobile;
    /*教师家庭住址*/
    private String address;
    /*教师记录添加时间*/
    private LocalDateTime addTime;
}
