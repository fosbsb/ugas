package br.com.e2f.institutodepilar.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.e2f.institutodepilar.entity.Cidade;
import br.com.e2f.institutodepilar.repository.CidadeRepository;
import br.com.e2f.institutodepilar.service.CidadeService;
import br.com.e2f.institutodepilar.vo.RetornoVO;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;

@RestController
@RequestMapping("/api/cidade")
public class CidadeResource extends BasicResource {

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private CidadeService cidadeService;

	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public RetornoVO recuperarCidade(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "sort", required = false, defaultValue = "nome asc") String[] sorts,
			@RequestParam(value = "nome", required = false) String nome,
			@And(@Spec(path = "nome", spec = LikeIgnoreCase.class)) Specification<Cidade> spec) {

		MultiValueMap<String, String> headers = new HttpHeaders();
		List<Cidade> cidade = null;

		if (page != null || limit != null) {
			Pageable pageRequest = new PageRequest(page - 1, limit, buildSort(sorts));

			Page<Cidade> pageResult = cidadeRepository.findAll(spec, pageRequest);
			cidade = pageResult.getContent();

			headers.add("X-Total-Count", String.valueOf(pageResult.getTotalElements()));
		} else {
			cidade = cidadeRepository.findAll(spec, buildSort(sorts));
			headers.add("X-Total-Count", String.valueOf(cidade.size()));
		}

		return getRetornoVO(new ResponseEntity<List<Cidade>>(cidade, headers, HttpStatus.OK));
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public RetornoVO recuperarCidade(@PathVariable("id") Long id) {
		Cidade cidade = cidadeService.recuperarCidade(id);

		if (cidade == null) {
			return getRetornoVO(new ResponseEntity<Cidade>(HttpStatus.NOT_FOUND));
		}

		return getRetornoVO(new ResponseEntity<Cidade>(cidade, HttpStatus.OK));
	}

}