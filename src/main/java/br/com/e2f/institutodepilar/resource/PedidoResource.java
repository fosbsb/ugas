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

import br.com.e2f.institutodepilar.entity.Pedido;
import br.com.e2f.institutodepilar.repository.PedidoRepository;
import br.com.e2f.institutodepilar.service.PedidoService;
import br.com.e2f.institutodepilar.vo.RetornoVO;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Or;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;

@RestController
@RequestMapping("/api/pedido")
public class PedidoResource extends BasicResource {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private PedidoService pedidoService;

	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public RetornoVO recuperarPedido(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "sort", required = false, defaultValue = "id asc") String[] sorts,
			@RequestParam(value = "cliente.id", required = false) Long idCliente,
			@RequestParam(value = "usuario.id", required = false) Long idUsuario,
			@Or({ @Spec(path = "cliente.id", spec = Equal.class),
					@Spec(path = "usuario.id", spec = Equal.class) }) Specification<Pedido> spec) {

		MultiValueMap<String, String> headers = new HttpHeaders();
		List<Pedido> pedido = null;

		if (page != null || limit != null) {
			Pageable pageRequest = new PageRequest(page - 1, limit, buildSort(sorts));

			Page<Pedido> pageResult = pedidoRepository.findAll(spec, pageRequest);
			pedido = pageResult.getContent();

			headers.add("X-Total-Count", String.valueOf(pageResult.getTotalElements()));
		} else {
			pedido = pedidoRepository.findAll(spec, buildSort(sorts));
			headers.add("X-Total-Count", String.valueOf(pedido.size()));
		}

		return getRetornoVO(new ResponseEntity<List<Pedido>>(pedido, headers, HttpStatus.OK));
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public RetornoVO recuperarPedido(@PathVariable("id") Long id) {
		Pedido pedido = pedidoService.recuperarPedido(id);

		if (pedido == null) {
			return getRetornoVO(new ResponseEntity<Pedido>(HttpStatus.NOT_FOUND));
		}

		return getRetornoVO(new ResponseEntity<Pedido>(pedido, HttpStatus.OK));
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public RetornoVO incluirPedido(@RequestBody Pedido pedido) {
		return getRetornoVO(pedidoService.incluirPedido(pedido));
	}

	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public RetornoVO atualizarPedido(@RequestBody @Valid Pedido pedido) {
		return getRetornoVO(pedidoService.atualizarPedido(pedido));
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	public void excluirPedido(@PathVariable("id") Long id) throws Exception {
		pedidoService.excluirPedido(id);
	}
}