package com.example.service.impl;

import cn.hutool.core.util.StrUtil;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.example.service.FileService;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service("aliyunOss")
public class FileServiceByAliyunImpl implements FileService {

  public final static String endpoint="";
  public final static String accessKeyId="";
  public final static String accessKeySecret="";
  public final static String bucket="";
  public final static String url_pre="";



  @Override
  public String save(InputStream in, String filename, String pre, boolean randomName) {
    if (StrUtil.hasBlank(pre)) {
      throw new RuntimeException("路径前缀不能为空");
    }
    if (!pre.matches("^[\\w\\/]+$")) {
      throw new RuntimeException("路径前缀只能由单词字符组成");
    }
    try {
      if (in == null || in.available() == 0) {
        throw new RuntimeException("输入流不能");
      }
      if (StrUtil.isEmpty(filename)) {
        throw new RuntimeException("文件名不能为空");
      }
      String uri = pre + "/" + generateURI("{yyyy}/{MM}/{dd}/{id36}" + (randomName ? ".{ext}" : "/{filename}.{ext}"), filename);


      OSS ossClient = null;
      try {
        // 创建OSSClient实例。
        ossClient = new OSSClientBuilder().build(endpoint, accessKeyId,
            accessKeySecret);
        // 上传文件。
        ossClient.putObject(bucket, uri, in);
        return url_pre + uri;
      } catch (Exception e) {
        throw new RuntimeException("上传到阿里云错误!", e);
      } finally {
        if (ossClient != null) {
          ossClient.shutdown();
        }
      }
    } catch (Exception e) {
      if(e instanceof RuntimeException) {
        throw (RuntimeException)e;
      }
      throw new RuntimeException("上传错误", e);
    }
  }

  @Override
  public void delete(String filename) {
    if (StrUtil.isEmpty(filename)) {
      throw new RuntimeException("文件名不能为空");
    }
    try {
      if (filename.startsWith(url_pre)) {
        filename = filename.substring(url_pre.length());
      }
      if (filename.indexOf("?") > -1) {
        filename = filename.substring(0, filename.indexOf("?"));
      }
      OSS ossClient = null;
      try {
        // 创建OSSClient实例。
        ossClient = new OSSClientBuilder().build(endpoint, accessKeyId,
            accessKeySecret);
        // 上传文件。
        ossClient.deleteObject(bucket, filename);

      } catch (Exception e) {
        throw new RuntimeException("删除阿里云上的文件失败", e);
      } finally {
        if (ossClient != null) {
          ossClient.shutdown();
        }
      }

    } catch (Exception e) {
      if(e instanceof RuntimeException) {
        throw (RuntimeException)e;
      }
      throw new RuntimeException("删除失败", e);
    }
  }


  
}
