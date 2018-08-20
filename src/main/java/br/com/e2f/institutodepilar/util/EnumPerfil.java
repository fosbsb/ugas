package br.com.e2f.institutodepilar.util;

public enum EnumPerfil {

	ADMIN("ADMIN"), USUARIO("USUARIO");

	private String descricao;

	private EnumPerfil(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
