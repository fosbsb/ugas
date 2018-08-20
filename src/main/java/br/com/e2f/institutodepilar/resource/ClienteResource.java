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

import br.com.e2f.institutodepilar.entity.Cliente;
import br.com.e2f.institutodepilar.repository.ClienteRepository;
import br.com.e2f.institutodepilar.service.ClienteService;
import br.com.e2f.institutodepilar.vo.RetornoVO;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;

@RestController
@RequestMapping("/api/cliente")
public class ClienteResource extends BasicResource {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private ClienteService clienteService;

	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public RetornoVO recuperarCliente(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "sort", required = false, defaultValue = "nome asc") String[] sorts,
			@RequestParam(value = "cpf", required = false) String cpf,
			@RequestParam(value = "nome", required = false) String nome,
			@RequestParam(value = "email", required = false) String email,
			@And({ @Spec(path = "cpf", spec = Equal.class),
					@Spec(path = "nome", spec = LikeIgnoreCase.class),
					@Spec(path = "email", spec = LikeIgnoreCase.class) }) Specification<Cliente> spec) {

		MultiValueMap<String, String> headers = new HttpHeaders();
		List<Cliente> cliente = null;

		if (page != null || limit != null) {
			Pageable pageRequest = new PageRequest(page - 1, limit, buildSort(sorts));

			Page<Cliente> pageResult = clienteRepository.findAll(spec, pageRequest);
			cliente = pageResult.getContent();

			headers.add("X-Total-Count", String.valueOf(pageResult.getTotalElements()));
		} else {
			cliente = clienteRepository.findAll(spec, buildSort(sorts));
			headers.add("X-Total-Count", String.valueOf(cliente.size()));
		}

		return getRetornoVO(new ResponseEntity<List<Cliente>>(cliente, headers, HttpStatus.OK));
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public RetornoVO recuperarCliente(@PathVariable("id") Long id) {
		Cliente cliente = clienteService.recuperarCliente(id);

		if (cliente == null) {
			return getRetornoVO(new ResponseEntity<Cliente>(HttpStatus.NOT_FOUND));
		}

		return getRetornoVO(new ResponseEntity<Cliente>(cliente, HttpStatus.OK));
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Cliente incluirCliente(@RequestBody Cliente cliente) {
		
		return clienteService.incluirCliente(cliente);
	}

	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Cliente atualizarCliente(@RequestBody @Valid Cliente cliente) {
		return clienteService.atualizarCliente(cliente);
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	public void excluirCliente(@PathVariable("id") Long id) throws Exception {
		Cliente cliente = clienteService.recuperarCliente(id);
		clienteService.atualizarCliente(cliente);
	}

}