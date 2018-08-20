package br.com.e2f.institutodepilar.vo;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

public class RetornoVO {

	private HttpStatus httpStatus;
	private HttpHeaders httpHeaders;
	private List<String> mensagens;
	private Object resultado;

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public HttpHeaders getHttpHeaders() {
		return httpHeaders;
	}

	public void setHttpHeaders(HttpHeaders httpHeaders) {
		this.httpHeaders = httpHeaders;
	}

	public List<String> getMensagens() {
		return mensagens;
	}

	public void setMensagens(List<String> mensagens) {
		this.mensagens = mensagens;
	}

	public Object getResultado() {
		return resultado;
	}

	public void setResultado(Object resultado) {
		this.resultado = resultado;
	}
}
