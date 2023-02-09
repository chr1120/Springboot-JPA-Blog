package com.chr.blog.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.chr.blog.dto.ResponseDto;

@ControllerAdvice // 모든 Exception이 발생시, 이 클래스로 들어온다.
@RestController
public class GlobalExceptionHandler {
	
	// 이 함수에는 IllegalArgumentException만 들어온다.
	// 모든 Exception을 들어오게 하려면, Exception을 적어주면 된다.
	@ExceptionHandler(value=Exception.class)
	public ResponseDto<String> handleArgumentException(Exception e) {
		return new ResponseDto<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage());
	}
}
