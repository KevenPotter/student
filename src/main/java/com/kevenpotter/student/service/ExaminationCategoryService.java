package com.kevenpotter.student.service;

import com.kevenpotter.student.dao.ExaminationCategoryDao;
import com.kevenpotter.student.domain.dto.ExaminationCategoryDto;
import com.kevenpotter.student.domain.entity.ExaminationCategoryEntity;
import com.kevenpotter.student.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2019-11-24 00:31:00
 * @description 考试类目服务层类
 */
@Service
public class ExaminationCategoryService {

    /*定义日志记录器，用来记录必要信息*/
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ExaminationCategoryDao examinationCategoryDao;


    /**
     * @param examinationCategoryId 考试场次编号
     * @param name                  考试场次名称
     * @return 返回一个[考试类目实体]
     * @author KevenPotter
     * @date 2019-11-24 00:40:33
     * @description 根据[考试场次编号]或[考试场次名称]查询[考试类目实体]
     */
    public ExaminationCategoryEntity getExaminationCategory(Long examinationCategoryId, String name) {
        if (null == examinationCategoryId && StringUtils.isEmpty(name)) return null;
        if (null == examinationCategoryId) return examinationCategoryDao.findExaminationCategoryByName(name);
        if (StringUtils.isEmpty(name)) return examinationCategoryDao.findExaminationCategoryById(examinationCategoryId);
        return examinationCategoryDao.findExaminationCategoryByIdAndName(examinationCategoryId, name);
    }

    /**
     * @param examinationCategoryDto 考试类目数据传输类
     * @return 返回插入的考试类目实体
     * @author KevenPotter
     * @date 2019-11-24 00:42:18
     * @description 插入一条新的[考试类目实体]并返回该[考试类目实体]
     */
    public ExaminationCategoryEntity addExaminationCategory(ExaminationCategoryDto examinationCategoryDto) {
        if (null == examinationCategoryDto) return null;
        examinationCategoryDao.addExaminationCategory(examinationCategoryDto);
        return this.getExaminationCategory(examinationCategoryDto.getId(), examinationCategoryDto.getName());
    }

    /**
     * @param examinationCategoryDto 考试类目数据传输类
     * @return 返回更新之前的考试类目实体
     * @author KevenPotter
     * @date 2019-11-24 00:44:32
     * @description 更新[考试类目实体]并返回更新之前的[考试类目实体]
     */
    public ExaminationCategoryEntity updateExaminationCategory(ExaminationCategoryDto examinationCategoryDto) {
        if (null == examinationCategoryDto) return null;
        ExaminationCategoryEntity examinationCategoryEntity = examinationCategoryDao.findExaminationCategoryById(examinationCategoryDto.getId());
        examinationCategoryDao.updateExaminationCategory(examinationCategoryDto);
        return examinationCategoryEntity;
    }
}
