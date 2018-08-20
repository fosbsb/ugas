package br.com.e2f.institutodepilar.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the pedido database table.
 * 
 */
@Entity
@Table(name = "pedido")
@NamedQuery(name = "Pedido.findAll", query = "SELECT p FROM Pedido p")
public class Pedido extends AbstractEntity<Long> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@OneToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	@OneToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data")
	private Date data;

	@OneToOne(optional = true)
	@JoinColumn(name = "cartao_fidelidade_id")
	private CartaoFidelidade cartaoFidelidade;

	// bi-directional many-to-one association to PedidoItem
	@OneToMany(mappedBy = "pedido")
	private List<PedidoServicoItem> pedidoItems;

	public Pedido() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public CartaoFidelidade getCartaoFidelidade() {
		return cartaoFidelidade;
	}

	public void setCartaoFidelidade(CartaoFidelidade cartaoFidelidade) {
		this.cartaoFidelidade = cartaoFidelidade;
	}

	public List<PedidoServicoItem> getPedidoItems() {
		return pedidoItems;
	}

	public void setPedidoItems(List<PedidoServicoItem> pedidoItems) {
		this.pedidoItems = pedidoItems;
	}

}