package br.com.e2f.institutodepilar;


import java.io.Serializable;

/**
 * Interface domínios que possuem um ID.
 * 
 * @param <ID> Tipo do identifificador pode ser um Wrapper (Long, Integer, etc.) ou 
 * um objeto que represente uma chave composta.
 * 
 * @since 11/10/2012
 */
public interface EntityInterface<ID extends Serializable> {

	/**
	 * Obtém o identificador do domínio.
	 * 
	 * @return identificador do domínio
	 */
	ID getId();
	
    /**
     * Seta o identificador.
     * 
     * @param id identificador.
     */
    void setId(ID id);

    /**
     * Método utilitário para saber se o identificador foi setado ou não.
     * @return true se a identificador foi setado, false caso contrário
     */
    //boolean isIdSet();
    
	
	/**
	 * Obtém um código único do objeto com base na instância do objeto.
	 * 
	 * @return  código único para object
	 */
	String getUniqueCode();

}