package com.kevenpotter.student.domain.dto;

import lombok.Data;

/**
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2019-12-20 17:00:11
 * @description 首页仪表盘数据传输类
 */
@Data
public class DashboardDto {

    private Long totalNumberOfStudents;
    private Long totalNumberOfTeachers;
    private Long totalNumberOfAccounts;
    private Long totalNumberOfVisits;
}
