package br.com.e2f.institutodepilar.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.e2f.institutodepilar.entity.Pedido;
import br.com.e2f.institutodepilar.repository.PedidoRepository;

@Service
@Transactional(readOnly = true)
public class PedidoService {

	@Autowired
	private PedidoRepository pedidorepository;

	public Page<Pedido> recuperarPedidoPaginado(int page, int limit) {

		Pageable pageRequest = new PageRequest(page, limit);

		return pedidorepository.findAll(pageRequest);
	}

	public Pedido recuperarPedido(Long id) {
		return pedidorepository.findOne(id);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public Pedido incluirPedido(@Valid Pedido pedido) {
		
		return pedidorepository.save(pedido);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public Pedido atualizarPedido(@Valid Pedido pedido) {
		return pedidorepository.save(pedido);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void excluirPedido(Long id) {
		if (pedidorepository.exists(id)){
			pedidorepository.delete(id);
		}
	}
}
