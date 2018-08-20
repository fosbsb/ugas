package br.com.e2f.institutodepilar.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.e2f.institutodepilar.entity.Bairro;
import br.com.e2f.institutodepilar.repository.BairroRepository;

@Service
@Transactional(readOnly = true)
public class BairroService {

	@Autowired
	private BairroRepository bairrorepository;

	public Page<Bairro> recuperarBairroPaginado(int page, int limit) {

		Pageable pageRequest = new PageRequest(page, limit);

		return bairrorepository.findAll(pageRequest);
	}

	public Bairro recuperarBairro(Long id) {
		return bairrorepository.findOne(id);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public Bairro incluirBairro(@Valid Bairro bairro) {

		return bairrorepository.save(bairro);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public Bairro atualizarBairro(@Valid Bairro bairro) {
		return bairrorepository.save(bairro);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void excluirBairro(Long id) {
		if (bairrorepository.exists(id)){
			bairrorepository.delete(id);
		}
	}
}
