package cn.jift.serviceedu.service.impl;

import cn.jift.serviceedu.entity.EduTeacher;
import cn.jift.serviceedu.entity.vo.TeacherVo;
import cn.jift.serviceedu.mapper.EduTeacherMapper;
import cn.jift.serviceedu.service.EduTeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author ldm
 * @since 2020-05-17
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

    //带条件分页讲师列表
    @Override
    public void pageTeacher(Page<EduTeacher> teacherPage, TeacherVo teacherVo) {
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        wrapper.orderByAsc("sort");

        if(teacherVo == null) {
            baseMapper.selectPage(teacherPage,null);
            return;
        }

        String name = teacherVo.getName();
        Integer level = teacherVo.getLevel();
        String begin = teacherVo.getBegin();
        String end = teacherVo.getEnd();

        if(!StringUtils.isEmpty(name)) {
            wrapper.like("name",name);
        }
        if(!StringUtils.isEmpty(level)) {
            wrapper.eq("level",level);
        }
        if(!StringUtils.isEmpty(begin)) {
            wrapper.ge("gmt_create",begin);
        }
        if(!StringUtils.isEmpty(end)) {
            wrapper.le("gmt_create",end);
        }

        baseMapper.selectPage(teacherPage,wrapper);
    }
}
