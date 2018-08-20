package br.com.e2f.institutodepilar.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.e2f.institutodepilar.entity.CartaoFidelidade;
import br.com.e2f.institutodepilar.repository.CartaoFidelidadeRepository;

@Service
@Transactional(readOnly = true)
public class CartaoFidelildadeService {

	@Autowired
	private CartaoFidelidadeRepository cartaoFidelidadeRepository;

	public Page<CartaoFidelidade> recuperarCartaoFidelidadePaginado(int page, int limit) {

		Pageable pageRequest = new PageRequest(page, limit);

		return cartaoFidelidadeRepository.findAll(pageRequest);
	}

	public CartaoFidelidade recuperarCartaoFidelidade(Long id) {
		return cartaoFidelidadeRepository.findOne(id);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public CartaoFidelidade incluirCartaoFidelidade(@Valid CartaoFidelidade cartaoFidelidade) {

		return cartaoFidelidadeRepository.save(cartaoFidelidade);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public CartaoFidelidade atualizarCartaoFidelidade(@Valid CartaoFidelidade cartaoFidelidade) {
		return cartaoFidelidadeRepository.save(cartaoFidelidade);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void excluirCartaoFidelidade(Long id) {
		if (cartaoFidelidadeRepository.exists(id)) {
			cartaoFidelidadeRepository.delete(id);
		}
	}
}
