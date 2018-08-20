package br.com.e2f.institutodepilar.util;

public enum EnumEmailTemplate {

	/************************************************************
	 * PARA CADA TEMPLATE É NECESSÁRIO CRIAR UM ARQUIVO .html 
	 * CORRESPONDENTE.
	 * 
	 * "/resources/templates/arquivo.html"
	 * 
	 ************************************************************/

	PADRAO("main"), 
	ESQUECI_SENHA("esqueciSenha"), 
	NOVO_CADASTRO("novo_cadastro");

	private String descricao;

	private EnumEmailTemplate(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
