package br.com.e2f.institutodepilar.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.e2f.institutodepilar.entity.PedidoServicoItem;
import br.com.e2f.institutodepilar.repository.PedidoServicoItemRepository;

@Service
@Transactional(readOnly = true)
public class PedidoServicoItemService {

	@Autowired
	private PedidoServicoItemRepository pedidoitemrepository;

	public Page<PedidoServicoItem> recuperarPedidoItemPaginado(int page, int limit) {

		Pageable pageRequest = new PageRequest(page, limit);

		return pedidoitemrepository.findAll(pageRequest);
	}

	public PedidoServicoItem recuperarPedidoItem(Long id) {
		return pedidoitemrepository.findOne(id);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public PedidoServicoItem incluirPedidoItem(@Valid PedidoServicoItem pedidoitem) {

		return pedidoitemrepository.save(pedidoitem);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public PedidoServicoItem atualizarPedidoItem(@Valid PedidoServicoItem pedidoitem) {
		return pedidoitemrepository.save(pedidoitem);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void excluirPedidoItem(Long id) {
		if (pedidoitemrepository.exists(id)){
			pedidoitemrepository.delete(id);
		}
	}
}
