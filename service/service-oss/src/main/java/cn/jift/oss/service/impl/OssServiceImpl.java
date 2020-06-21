package cn.jift.oss.service.impl;

import cn.jift.oss.service.OssService;
import cn.jift.oss.utils.ConstantPropertiesUtils;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

/**
 * 阿里云对象存储服务实现类
 */
@Service
public class OssServiceImpl implements OssService {
    @Override
    public String uploadFile(MultipartFile file) {
        String endpoint = ConstantPropertiesUtils.END_POINT;
        String accessKeyId = ConstantPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtils.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtils.BUCKET_NAME;
        String fileHost = ConstantPropertiesUtils.FILE_HOST;

        try {
            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            // 上传文件流。
            InputStream inputStream = file.getInputStream();
            //获取上传文件的原始名称
            String fileName = file.getOriginalFilename();
            //解决文件重名问题
            String uuid = UUID.randomUUID().toString();
            fileName = uuid + fileName;
            //文件分类处理
            String datePath = new DateTime().toString("yyyy/MM/dd");
            fileName = fileHost + "/" + datePath + "/" + fileName;
            //调用方法实现上传，第一个参数为bucketName，第二个参数为上传到oss中的文件名称（可以带路经，例如2020/6/21/file.jpg），第三个参数为上传文件的输入流
            ossClient.putObject(bucketName, fileName, inputStream);
            // 关闭OSSClient。
            ossClient.shutdown();
            //返回路径
            String url = "https://" + bucketName + "." + endpoint + "/" + fileName;
            return url;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
