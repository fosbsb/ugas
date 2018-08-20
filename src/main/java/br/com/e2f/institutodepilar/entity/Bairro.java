package br.com.e2f.institutodepilar.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the bairro database table.
 * 
 */
@Entity
@Table(name = "bairro")
@NamedQuery(name = "Bairro.findAll", query = "SELECT b FROM Bairro b")
public class Bairro extends AbstractEntity<Long> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String nome;

	// uni-directional many-to-one association to Cidade
	@ManyToOne
	private Cidade cidade;

	public Bairro() {
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

	public Cidade getCidade() {
		return this.cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

}