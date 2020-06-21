package cn.jift.oss.service;

import org.springframework.web.multipart.MultipartFile; /**
 * 阿里云对象存储服务类
 */
public interface OssService {
    String uploadFile(MultipartFile file);
}
