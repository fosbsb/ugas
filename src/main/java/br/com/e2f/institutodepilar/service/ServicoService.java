package br.com.e2f.institutodepilar.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.e2f.institutodepilar.entity.Servico;
import br.com.e2f.institutodepilar.repository.ServicoRepository;

@Service
@Transactional(readOnly = true)
public class ServicoService {

	@Autowired
	private ServicoRepository ServicoRepository;

	public Page<Servico> recuperarServicoPaginado(int page, int limit) {

		Pageable pageRequest = new PageRequest(page, limit);

		return ServicoRepository.findAll(pageRequest);
	}

	public Servico recuperarServico(Long id) {
		return ServicoRepository.findOne(id);
	}

//	@Transactional(propagation=Propagation.REQUIRED)
//	public Servico incluirServico(@Valid Servico servico) {
//
//		return ServicoRepository.save(servico);
//	}
//
//	@Transactional(propagation=Propagation.REQUIRED)
//	public Servico atualizarServico(@Valid Servico servico) {
//		return ServicoRepository.save(servico);
//	}
//
//	@Transactional(propagation=Propagation.REQUIRED)
//	public void excluirServico(Long id) {
//		if (ServicoRepository.exists(id)){
//			ServicoRepository.delete(id);
//		}
//	}
}
