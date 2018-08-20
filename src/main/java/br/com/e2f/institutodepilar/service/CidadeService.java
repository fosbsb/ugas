package br.com.e2f.institutodepilar.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.e2f.institutodepilar.entity.Cidade;
import br.com.e2f.institutodepilar.repository.CidadeRepository;

@Service
@Transactional(readOnly = true)
public class CidadeService {

	@Autowired
	private CidadeRepository cidaderepository;

	public Page<Cidade> recuperarCidadePaginado(int page, int limit) {

		Pageable pageRequest = new PageRequest(page, limit);

		return cidaderepository.findAll(pageRequest);
	}

	public Cidade recuperarCidade(Long id) {
		return cidaderepository.findOne(id);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public Cidade incluirCidade(@Valid Cidade cidade) {

		return cidaderepository.save(cidade);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public Cidade atualizarCidade(@Valid Cidade cidade) {
		return cidaderepository.save(cidade);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void excluirCidade(Long id) {
		if (cidaderepository.exists(id)){
			cidaderepository.delete(id);
		}
	}
}
