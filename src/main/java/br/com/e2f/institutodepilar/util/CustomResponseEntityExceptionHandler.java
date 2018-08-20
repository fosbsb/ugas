package br.com.e2f.institutodepilar.util;

import javax.ws.rs.InternalServerErrorException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.google.common.collect.Lists;

import br.com.e2f.institutodepilar.vo.RetornoVO;

@ControllerAdvice
class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(InternalServerErrorException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	RetornoVO handleBadErrorInternal(Exception e) {
		RetornoVO retornoVO = new RetornoVO();
		retornoVO.setResultado(null);
		retornoVO.setMensagens(Lists.newArrayList(e.getCause().getCause().getMessage()));
		retornoVO.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		return retornoVO;
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	RetornoVO handleBadRequest(Exception e) {
		RetornoVO retornoVO = new RetornoVO();
		retornoVO.setResultado(null);
		retornoVO.setMensagens(Lists.newArrayList(e.getCause().getCause().getMessage()));
		retornoVO.setHttpStatus(HttpStatus.BAD_REQUEST);

		return retornoVO;
	}
}
