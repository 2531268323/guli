package cn.jift.oss.controller;

import cn.jift.commonutils.R;
import cn.jift.oss.service.OssService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 阿里云对象存储控制器
 */
@RestController
@RequestMapping("/admin/oss/file")
@CrossOrigin
@Api("阿里云对象存储")
public class OssController {

    @Autowired
    private OssService ossService;

    @PostMapping("upload")
    public R uploadOssFile(MultipartFile file) {
        //上传文件，返回上传到oss的路径
        String url = ossService.uploadFile(file);
        return R.ok().data("url",url);
    }

}
