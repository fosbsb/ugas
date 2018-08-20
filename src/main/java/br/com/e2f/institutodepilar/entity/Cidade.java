package br.com.e2f.institutodepilar.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the cidade database table.
 * 
 */
@Entity
@Table(name = "cidade")
@NamedQuery(name = "Cidade.findAll", query = "SELECT c FROM Cidade c")
public class Cidade extends AbstractEntity<Long> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String nome;

	// uni-directional many-to-one association to Estado
	@ManyToOne
	private Estado estado;

	public Cidade() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Estado getEstado() {
		return this.estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

}