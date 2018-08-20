package br.com.e2f.institutodepilar.util;

public enum Mensagem {

	SUCESSO("Sucesso."), 
	NAO_ENCONTRADO("O Registro não foi encontrado."), 
	NAO_AUTORIZADO("Usuário não autorizado."),
	ERRO_DESCONHECIDO("Erro Desconhecido."),
	ERRO_SERVIDOR("Erro interno do Servidor.");
	
	private String value;

    Mensagem(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.getValue();
    }
}
