package br.com.e2f.institutodepilar.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the cartao_fidelidade database table.
 * 
 */
@Entity
@Table(name = "cartao_fidelidade")
@NamedQuery(name = "cartao_fidelidade.findAll", query = "SELECT c FROM CartaoFidelidade c")
public class CartaoFidelidade extends AbstractEntity<Long> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_cadastro")
	private Date dataUtilizacao;

	@OneToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	@OneToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	public CartaoFidelidade() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataUtilizacao() {
		return dataUtilizacao;
	}

	public void setDataUtilizacao(Date dataUtilizacao) {
		this.dataUtilizacao = dataUtilizacao;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}