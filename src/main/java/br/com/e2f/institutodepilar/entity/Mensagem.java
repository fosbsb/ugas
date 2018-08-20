package br.com.e2f.institutodepilar.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Mensagem {

	private Notification notification;
	private String to;

	@JsonIgnore
	private String bodyMensagem;

	public Notification getNotification() {
		return notification;
	}

	public void setNotification(Notification notification) {
		this.notification = notification;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getBodyMensagem() {
		return bodyMensagem;
	}

	public void setBodyMensagem(String bodyMensagem) {
		this.bodyMensagem = bodyMensagem;
	}

}
