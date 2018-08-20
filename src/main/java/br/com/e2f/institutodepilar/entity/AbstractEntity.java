package br.com.e2f.institutodepilar.entity;

import java.io.Serializable;

import br.com.e2f.institutodepilar.EntityInterface;

/**
 * Classe base para dominios que possuem um ID. Sobrescreve corretamente os
 * métodos hashCode() e equals() com base no ID.
 * 
 * @param <ID>
 *            Tipo do identifificador pode ser um Wrapper (Long, Integer, etc.)
 *            ou um objeto que represente uma chave composta.
 * 
 * @since 28/12/2017
 */
public abstract class AbstractEntity<ID extends Serializable> implements EntityInterface<ID>, Serializable {

	/**
	 * Número utilizado para calcular o hash do objeto.
	 */
	private static final int HASH_MAGIC_NUMBER = 31;

	/**
	 * Atributo serial version.
	 */
	private static final long serialVersionUID = 6788690439749066592L;

	/**
	 * Retorna um hash code com base no identificador do dominio.
	 * 
	 * @return um hash code do objeto
	 */
	@Override
	public int hashCode() {
		int resultado;
		if (getId() == null) {
			resultado = 0;
		} else {
			resultado = HASH_MAGIC_NUMBER + getId().hashCode();
		}
		return resultado;
	}

	/**
	 * Obtem um código único do objeto com base na instancia do objeto.
	 * 
	 * @return codigo unico para object
	 */
	public String getUniqueCode() {
		String complemento = "";
		if (getId() != null) {
			complemento = getId().toString();
		}
		return String.valueOf(super.hashCode() + "-" + complemento);
	}

	/**
	 * Indica se um objeto é igual comparando com o identificador.
	 * 
	 * @param outroObjeto
	 *            outro objeto a ser comparado
	 * 
	 * @return true se os dois objetos possuem o mesmo identificador, e false caso
	 *         contrário.
	 */
	@Override
	public boolean equals(final Object outroObjeto) {
		return this == outroObjeto || (outroObjeto instanceof AbstractEntity<?>
				&& (this.getId() != null && this.getId().equals(((AbstractEntity<?>) outroObjeto).getId())));
	}

}