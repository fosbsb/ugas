package br.com.e2f.institutodepilar.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.e2f.institutodepilar.entity.Estado;
import br.com.e2f.institutodepilar.repository.EstadoRepository;

@Service
@Transactional(readOnly = true)
public class EstadoService {

	@Autowired
	private EstadoRepository estadorepository;

	public Page<Estado> recuperarEstadoPaginado(int page, int limit) {

		Pageable pageRequest = new PageRequest(page, limit);

		return estadorepository.findAll(pageRequest);
	}

	public Estado recuperarEstado(Long id) {
		return estadorepository.findOne(id);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public Estado incluirEstado(@Valid Estado estado) {

		return estadorepository.save(estado);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public Estado atualizarEstado(@Valid Estado estado) {
		return estadorepository.save(estado);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void excluirEstado(Long id) {
		if (estadorepository.exists(id)){
			estadorepository.delete(id);
		}
	}
}
