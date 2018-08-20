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

import br.com.e2f.institutodepilar.entity.PedidoServicoItem;
import br.com.e2f.institutodepilar.repository.PedidoServicoItemRepository;
import br.com.e2f.institutodepilar.service.PedidoServicoItemService;
import br.com.e2f.institutodepilar.vo.RetornoVO;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;

@RestController
@RequestMapping("/api/pedido-servico-item")
public class PedidoServicoItemResource extends BasicResource {

	@Autowired
	private PedidoServicoItemRepository pedidoitemRepository;

	@Autowired
	private PedidoServicoItemService pedidoitemService;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public RetornoVO recuperarPedidoItem(
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "sort", required = false, defaultValue = "pedido.id asc") String[] sorts,
			@RequestParam(value = "pedido.id", required = false) Long id,
			@And({ @Spec(path = "pedido.id", spec = Equal.class) }) Specification<PedidoServicoItem> spec) {

		MultiValueMap<String, String> headers = new HttpHeaders();
		List<PedidoServicoItem> pedidoitem = null;

		if (page != null || limit != null) {
			Pageable pageRequest = new PageRequest(page - 1, limit, buildSort(sorts));

			Page<PedidoServicoItem> pageResult = pedidoitemRepository.findAll(spec, pageRequest);
			pedidoitem = pageResult.getContent();

			headers.add("X-Total-Count", String.valueOf(pageResult.getTotalElements()));
		} else {
			pedidoitem = pedidoitemRepository.findAll(spec, buildSort(sorts));
			headers.add("X-Total-Count", String.valueOf(pedidoitem.size()));
		}

		return getRetornoVO(new ResponseEntity<List<PedidoServicoItem>>(pedidoitem, headers, HttpStatus.OK));
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public RetornoVO recuperarPedidoItem(@PathVariable("id") Long id) {
		PedidoServicoItem pedidoitem = pedidoitemService.recuperarPedidoItem(id);

		if (pedidoitem == null) {
			return getRetornoVO(new ResponseEntity<PedidoServicoItem>(HttpStatus.NOT_FOUND));
		}

		return getRetornoVO(new ResponseEntity<PedidoServicoItem>(pedidoitem, HttpStatus.OK));
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public RetornoVO incluirPedidoItem(@RequestBody PedidoServicoItem pedidoitem) {

		return getRetornoVO(pedidoitemService.incluirPedidoItem(pedidoitem));
	}

	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public RetornoVO atualizarPedidoItem(@RequestBody @Valid PedidoServicoItem pedidoitem) {
		return getRetornoVO(pedidoitemService.atualizarPedidoItem(pedidoitem));
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	public void excluirPedidoItem(@PathVariable("id") Long id) throws Exception {
		pedidoitemService.excluirPedidoItem(id);
	}
}