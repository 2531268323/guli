package cn.jift.serviceedu.controller;


import cn.jift.commonutils.R;
import cn.jift.serviceedu.entity.EduTeacher;
import cn.jift.serviceedu.entity.vo.TeacherVo;
import cn.jift.serviceedu.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author ldm
 * @since 2020-05-17
 */
@Api(description="讲师管理")
@RestController
@RequestMapping("/admin/edu/teacher")
@CrossOrigin
public class EduTeacherController {

    @Autowired
    private EduTeacherService eduTeacherService;

    /**
     * 查询所有讲师
     * @return
     */
    @ApiOperation(value = "查询所有讲师")
    @GetMapping("findAll")
    public R findAllTeacher() {
        List<EduTeacher> teacherList = eduTeacherService.list(null);
        return R.ok().data("teacherList",teacherList);
    }

    /**
     * 根据ID逻辑删除讲师
     * @param id
     * @return
     */
    @ApiOperation(value = "根据ID逻辑删除讲师")
    @DeleteMapping("{id}")
    public R deleteTeacherById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id) {
        boolean flag = eduTeacherService.removeById(id);
        if(flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    /**
     * 分页讲师列表
     * @param current 当前页
     * @param size 每页记录数
     * @return
     */
    @ApiOperation(value = "分页讲师列表")
    @GetMapping("pageTeacher/{current}/{size}")
    public R pageTeacher(
            @ApiParam(name = "current", value = "当前页码", required = true)
            @PathVariable Long current,
            @ApiParam(name = "size", value = "每页记录数", required = true)
            @PathVariable Long size) {
        //1.创建page对象
        Page<EduTeacher> teacherPage = new Page<>(current,size);
        //2.调用方法实现分页，把分页数据封装到teacherPage对象中
        eduTeacherService.page(teacherPage,null);
        long total = teacherPage.getTotal();//总记录数
        List<EduTeacher> records = teacherPage.getRecords();//每页数据集合
        return R.ok().data("total",total).data("rows",records);
    }

    /**
     * 带条件分页讲师列表
     * @param current 当前页
     * @param size 每页记录数
     * @param teacherVo 讲师查询对象
     * @return
     * 注意：使用@RequestBody(required = false)注解后，提交方式不能使用get提交，要使用post方式提交
     */
    @ApiOperation(value = "带条件分页讲师列表")
    @PostMapping("pageTeacherCondition/{current}/{size}")
    public R pageTeacherCondition(
            @ApiParam(name = "current", value = "当前页码", required = true)
            @PathVariable Long current,
            @ApiParam(name = "size", value = "每页记录数", required = true)
            @PathVariable Long size,
            @ApiParam(name = "teacherVo", value = "查询对象", required = false)
            @RequestBody(required = false) TeacherVo teacherVo) {
        //1.创建page对象
        Page<EduTeacher> teacherPage = new Page<>(current,size);
        //2.调用方法实现分页，把分页数据封装到teacherPage对象中
        eduTeacherService.pageTeacher(teacherPage,teacherVo);
        long total = teacherPage.getTotal();//总记录数
        List<EduTeacher> records = teacherPage.getRecords();//每页数据集合
        return R.ok().data("total",total).data("rows",records);
    }

    /**
     * 新增讲师
     * @param teacher
     * @return
     */
    @ApiOperation(value = "新增讲师")
    @PostMapping("addTeacher")
    public R addTeacher(
            @ApiParam(name = "teacher", value = "讲师对象", required = true)
            @RequestBody EduTeacher teacher) {
        boolean flag = eduTeacherService.save(teacher);
        if(flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    /**
     * 根据ID查询讲师
     * @param id 讲师ID
     * @return
     */
    @ApiOperation(value = "根据ID查询讲师")
    @GetMapping("getTeacher/{id}")
    public R getTeacherById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id) {
        EduTeacher teacher = eduTeacherService.getById(id);
        return R.ok().data("teacher",teacher);
    }

    /**
     * 根据ID修改讲师
     * @param eduTeacher 讲师对象
     * @return
     */
    @ApiOperation(value = "根据ID修改讲师")
    @PostMapping("updateTeacher")
    public R updateTeacher(
            @ApiParam(name = "teacher", value = "讲师对象", required = true)
            @RequestBody EduTeacher eduTeacher) {
        boolean flag = eduTeacherService.updateById(eduTeacher);
        if(flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }

}

