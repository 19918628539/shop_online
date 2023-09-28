package com.example.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <pre>
 * - 返回结果不需要保证成统一格式结果
 * Author : J.L.Zhou
 * E-Mail : 2233875735@qq.com
 * Tel : 151 1104 7708
 * Date : 2021-07-29 19:01
 * Version : 1.0
 * Copyright 2021 jlzhou.top Inc. All rights reserved.
 * Warning: this content is only for internal circulation of the company.
 *          It is forbidden to divulge it or use it for other commercial purposes.
 * </pre>
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface IgnoreResponse {
}