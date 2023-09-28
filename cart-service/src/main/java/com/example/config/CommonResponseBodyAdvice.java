package com.example.config;

import com.example.R;
import com.example.annotations.IgnoreResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 * - 全局异常处理
 * Author : J.L.Zhou
 * E-Mail : 2233875735@qq.com
 * Tel : 151 1104 7708
 * Date : 2021-5-28 12:12:32
 * Version : 1.0
 * Copyright 2021 jlzhou.top Inc. All rights reserved.
 * Warning: this content is only for internal circulation of the company.
 *          It is forbidden to divulge it or use it for other commercial purposes.
 * </pre>
 */
@RestControllerAdvice
public class CommonResponseBodyAdvice implements ResponseBodyAdvice {

	private static final Logger log = LoggerFactory.getLogger(CommonResponseBodyAdvice.class);
	/**
	 * - 参数校验
	 * 
	 * @param ex
	 * @return
	 */
	@ResponseStatus(HttpStatus.OK)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public R<?> handleBindGetException(MethodArgumentNotValidException ex) {
		log.error("参数校验异常", ex);
		Map<String, R<?>> fieldError = new HashMap<>();
		if (ex.getBindingResult().getFieldErrorCount() > 0) {
			ex.getBindingResult().getFieldErrors().forEach(fe -> {

					fieldError.put(fe.getField(), R.error().setCode(0).setMessage(fe.getDefaultMessage()));

			});

		}

		R<?> r = fieldError.values().iterator().next();
		return R.error().setCode(r.getCode()).setMessage(r.getMessage()).setData(fieldError);
	}

	@ResponseStatus(HttpStatus.OK)
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public R<?> HandleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
		return R.error().setCode(1).setMessage(ex.getMessage())
				.setData(ex.getParameterName());
	}


	@ResponseStatus(HttpStatus.OK)
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public R<?> HandleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
		return R.error().setCode(2).setMessage(ex.getMessage())
				.setData(ex.getParameter().getParameterName());
	}

	/**
	 * @param ex
	 * @return
	 */
	@ResponseStatus(HttpStatus.OK)
	@ExceptionHandler(value = Exception.class)
	public R<?> handleException(Exception ex) {
		log.error("系统内部异常", ex);
		return R.error().setMessage(ex.getMessage());
	}

	@Override
	public boolean supports(MethodParameter returnType, Class converterType) {
		//方法上被标注，也不拦截
		if (returnType.getMethod().isAnnotationPresent(IgnoreResponse.class)) {
			return false;
		}
		return true;
	}

	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
		if(null == body ){
			return R.ok();
		}else if(body instanceof R){
			return body;
		}else{
			return R.ok().setData(body);
		}
	}


	@ResponseStatus(HttpStatus.OK)
	@ExceptionHandler(NullPointerException.class)
	public R<?> HandleNullPointerException(NullPointerException ex) {
		log.error("空指针异常", ex);
		return R.error().setCode(0).setMessage("空指针异常");
	}

}
