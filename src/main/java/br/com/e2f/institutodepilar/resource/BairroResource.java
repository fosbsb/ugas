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

import br.com.e2f.institutodepilar.entity.Bairro;
import br.com.e2f.institutodepilar.repository.BairroRepository;
import br.com.e2f.institutodepilar.service.BairroService;
import br.com.e2f.institutodepilar.vo.RetornoVO;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;

@RestController
@RequestMapping("/api/bairro")
public class BairroResource extends BasicResource{

	@Autowired
	private BairroRepository bairroRepository;

	@Autowired
	private BairroService bairroService;

	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public RetornoVO recuperarBairro(
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "sort", required = false, defaultValue = "nome asc") String[] sorts,
			@RequestParam(value = "nome", required = false) String nome,
			@RequestParam(value = "cidade.id", required = false) Long cidade,
			@And({ @Spec(path = "nome", spec = LikeIgnoreCase.class),
				   @Spec(path = "cidade.id", spec=Equal.class)}) Specification<Bairro> spec){

		MultiValueMap<String, String> headers = new HttpHeaders();
		List<Bairro> bairro = null;

		if (page != null || limit != null){
			Pageable pageRequest = new PageRequest(page - 1, limit, buildSort(sorts) );

			Page<Bairro> pageResult = bairroRepository.findAll(spec, pageRequest);
			bairro = pageResult.getContent();

			headers.add("X-Total-Count", String.valueOf(pageResult.getTotalElements()));
		} else {
			bairro = bairroRepository.findAll(spec, buildSort(sorts));
			headers.add("X-Total-Count", String.valueOf(bairro.size()));
		}

		return getRetornoVO(new ResponseEntity<List<Bairro>>(bairro, headers, HttpStatus.OK));
	}
	
	@RequestMapping(path = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public RetornoVO recuperarBairro(@PathVariable("id") Long id) {
		Bairro bairro = bairroService.recuperarBairro(id);

		if (bairro == null){
			return  getRetornoVO(new ResponseEntity<Bairro>(HttpStatus.NOT_FOUND));
		}

		return getRetornoVO(new ResponseEntity<Bairro>(bairro, HttpStatus.OK));
	}
	
}