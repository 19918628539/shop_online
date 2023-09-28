package com.example.service;
import cn.hutool.core.lang.UUID;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.Calendar;

/**
 * <pre>
 * - 文件服务
 * Author : J.L.Zhou
 * E-Mail : 2233875735@qq.com
 * Tel : 151 1104 7708
 * Date : 2021-6-1 11:48:16
 * Version : 1.0
 * Copyright 2021 jlzhou.top Inc. All rights reserved.
 * Warning: this content is only for internal circulation of the company.
 *          It is forbidden to divulge it or use it for other commercial purposes.
 * </pre>
 */
public interface FileService {

  /**
   * - 保存文件,文件名随机
   * @param in
   * @param filename - 原文件名
   * @param pre - 存放路径前缀
   * @return - 保存的文件URL
   * @throws 400-499
   */
  default String save(InputStream in,String filename,String pre) {
    return save(in, filename,pre, true);
  }

  /**
   *
   * @param in
   * @param filename - 原文件名
   * @param pre - 存放路径前缀
   * @param randomName - 是否随机文件名
   * @return
   * @throws 400-499
   */
  String save(InputStream in,String filename,String pre,boolean randomName);

  /**
   * - 保存图片
   * - 云存储本身可以支持图片大小的修改,则不要实现
   * @param in
   * @param filename - 原文件名
   * @param pre - 存放路径前缀
   * @param maxWidth
   * @param maxHeight
   * @return
   */
  default String saveImg(InputStream in,String filename,String pre,int maxWidth,int maxHeight) {
    return save(in,filename,pre);
  }

  /**
   * - 保存图片
   * - 云存储本身可以支持图片大小的修改和+水印,则不要实现
   * @param in
   * @param filename - 原文件名
   * @param pre - 存放路径前缀
   * @param maxWidth
   * @param maxHeight
   * @param watermark	- 水印
   * @return
   */
  default String saveImg(InputStream in,String filename,String pre,int maxWidth,int maxHeight,BufferedImage watermark) {
    return save(in,filename,pre);
  }

  /**
   * - 保存Office文档
   * - office文件可能需要在线转换,默认,只是保存
   * @param in
   * @param filename - 原文件名
   * @param pre - 存放路径前缀
   * @return
   */
  default String saveOffice(InputStream in,String filename,String pre) {
    return save(in,filename,pre);
  }

  /**
   * - 删除文件
   * @param file 文件的URL
   */
  void delete(String file);



  /**<pre>
   * - 根据存放规则和文件名生成存放的URI
   * - 支持
   * 	- {yyyy}/{MM}/{dd}/{HH}/{mm}/{ss} 年月日时分秒
   * 	- {UUID} 32位的唯一标志
   * 	- {id} int类型的唯一id,防止重复建议+年月日路径
   * 	- {id16} int类型的唯一id16进制表示,防止重复建议+年月日路径
   * 	- {id36} int类型的唯一id36进制表示,防止重复建议+年月日路径
   * 	- {filename} 文件基础名称
   * 	- {ext} 扩展名</pre>
   * @param rule
   * @param filename
   * @return
   */
  default String generateURI(String rule,String filename) {
    Calendar c = Calendar.getInstance();
    if(rule.contains("{yyyy}")) {
      rule = rule.replace("{yyyy}", ""+c.get(Calendar.YEAR));
    }
    if(rule.contains("{MM}")) {
      rule = rule.replace("{MM}", ""+(c.get(Calendar.MONTH)+1));
    }
    if(rule.contains("{dd}")) {
      rule = rule.replace("{dd}", ""+c.get(Calendar.DATE));
    }
    if(rule.contains("{HH}")) {
      rule = rule.replace("{HH}", ""+c.get(Calendar.HOUR_OF_DAY));
    }
    if(rule.contains("{mm}")) {
      rule = rule.replace("{mm}", ""+c.get(Calendar.MINUTE));
    }
    if(rule.contains("{ss}")) {
      rule = rule.replace("{ss}", ""+c.get(Calendar.SECOND));
    }
    if(rule.contains("{UUID}")) {
      rule = rule.replace("{UUID}", UUID.fastUUID().toString(true));
    }
    if(rule.contains("{id}")) {
      rule = rule.replace("{id}",Integer.toString(id.nextId()));
    }
    if(rule.contains("{id16}")) {
      rule = rule.replace("{id16}",Integer.toString(id.nextId(),16));
    }
    if(rule.contains("{id36}")) {
      rule = rule.replace("{id36}",Integer.toString(id.nextId(),36));
    }
    if(rule.contains("{filename}")) {
      String temp = null;
      if(filename.contains(".")) {
        temp = filename.substring(0,filename.lastIndexOf("."));
      }else {
        temp = filename;
      }
      rule = rule.replace("{filename}", temp);
    }

    if(rule.contains("{ext}")) {
      String temp = null;
      if(filename.contains(".")) {
        temp = filename.substring(filename.lastIndexOf(".")+1);
      }else {
        temp = "";
      }
      rule = rule.replace("{ext}", temp);
    }
    return rule;
  }
  Id id = new Id();
  static class Id {
    private long lastTimestamp = 0;
    private int sequence = 0;

    public synchronized int nextId() {
      long timestamp = System.currentTimeMillis();
      if (lastTimestamp == timestamp) {
        sequence = (sequence + 1) & 0xF;
        if (sequence == 0) {
          while (timestamp <= lastTimestamp) {
            timestamp = System.currentTimeMillis();
          }
        }
      } else {
        sequence = 0;
      }
      lastTimestamp = timestamp;
      return (int) (timestamp & 0xFFFF) << 1 | sequence;
    }
  }
}
