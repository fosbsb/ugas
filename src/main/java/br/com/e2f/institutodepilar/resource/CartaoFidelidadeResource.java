package br.com.e2f.institutodepilar.resource;

import java.util.List;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.e2f.institutodepilar.entity.CartaoFidelidade;
import br.com.e2f.institutodepilar.repository.CartaoFidelidadeRepository;
import br.com.e2f.institutodepilar.service.CartaoFidelildadeService;
import br.com.e2f.institutodepilar.vo.RetornoVO;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;

@RestController
@RequestMapping("/api/cartaofidelidade")
public class CartaoFidelidadeResource extends BasicResource {

	@Autowired
	private CartaoFidelidadeRepository cartaoFidelidadeRepository;

	@Autowired
	private CartaoFidelildadeService cartaoFidelidadeService;

	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public RetornoVO recuperarCartaoFidelidade(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "sort", required = false, defaultValue = "nome asc") String[] sorts,
			@RequestParam(value = "cartaoNumero", required = false) String cartaoNumero,
			@RequestParam(value = "cliente.id", required = false) Long clienteId,
			@And({ @Spec(path = "cartaoNumero", spec = LikeIgnoreCase.class),
					@Spec(path = "cliente.id", spec = Equal.class) }) Specification<CartaoFidelidade> spec) {

		MultiValueMap<String, String> headers = new HttpHeaders();
		List<CartaoFidelidade> CartaoFidelidade = null;

		if (page != null || limit != null) {
			Pageable pageRequest = new PageRequest(page - 1, limit, buildSort(sorts));

			Page<CartaoFidelidade> pageResult = cartaoFidelidadeRepository.findAll(spec, pageRequest);
			CartaoFidelidade = pageResult.getContent();

			headers.add("X-Total-Count", String.valueOf(pageResult.getTotalElements()));
		} else {
			CartaoFidelidade = cartaoFidelidadeRepository.findAll(spec, buildSort(sorts));
			headers.add("X-Total-Count", String.valueOf(CartaoFidelidade.size()));
		}

		return getRetornoVO(new ResponseEntity<List<CartaoFidelidade>>(CartaoFidelidade, headers, HttpStatus.OK));
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public RetornoVO recuperarCartaoFidelidade(@PathVariable("id") Long id) {
		CartaoFidelidade CartaoFidelidade = cartaoFidelidadeService.recuperarCartaoFidelidade(id);

		if (CartaoFidelidade == null) {
			return getRetornoVO(new ResponseEntity<CartaoFidelidade>(HttpStatus.NOT_FOUND));
		}

		return getRetornoVO(new ResponseEntity<CartaoFidelidade>(CartaoFidelidade, HttpStatus.OK));
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public RetornoVO incluirCartaoFidelidade(@RequestBody CartaoFidelidade CartaoFidelidade) {

		return getRetornoVO(cartaoFidelidadeService.incluirCartaoFidelidade(CartaoFidelidade));
	}

	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public RetornoVO atualizarCartaoFidelidade(@RequestBody @Valid CartaoFidelidade CartaoFidelidade) {
		return getRetornoVO(cartaoFidelidadeService.atualizarCartaoFidelidade(CartaoFidelidade));
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	public void excluirCartaoFidelidade(@PathVariable("id") Long id) throws Exception {
		cartaoFidelidadeService.excluirCartaoFidelidade(id);
	}
}