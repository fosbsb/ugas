package br.com.e2f.institutodepilar.vo;

import br.com.e2f.institutodepilar.util.EnumEmailTemplate;

public class EmailVO {

	private String origem;
	private String destino;
	private String assunto;
	private String mensagem;
	private String nome;
	private String senhaGerada;
	private EnumEmailTemplate template;

	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public EnumEmailTemplate getTemplate() {
		return template;
	}

	public void setTemplate(EnumEmailTemplate template) {
		this.template = template;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenhaGerada() {
		return senhaGerada;
	}

	public void setSenhaGerada(String senhaGerada) {
		this.senhaGerada = senhaGerada;
	}

}
