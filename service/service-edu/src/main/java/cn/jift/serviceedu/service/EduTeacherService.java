package cn.jift.serviceedu.service;

import cn.jift.serviceedu.entity.EduTeacher;
import cn.jift.serviceedu.entity.vo.TeacherVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author ldm
 * @since 2020-05-17
 */
public interface EduTeacherService extends IService<EduTeacher> {

    //带条件分页讲师列表
    void pageTeacher(Page<EduTeacher> teacherPage, TeacherVo teacherVo);
}
