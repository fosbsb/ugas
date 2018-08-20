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

import br.com.e2f.institutodepilar.entity.Servico;
import br.com.e2f.institutodepilar.repository.ServicoRepository;
import br.com.e2f.institutodepilar.service.ServicoService;
import br.com.e2f.institutodepilar.util.EnumSexo;
import br.com.e2f.institutodepilar.vo.RetornoVO;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Or;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;

@RestController
@RequestMapping("/api/servico")
public class ServicoResource extends BasicResource {

	@Autowired
	private ServicoRepository servicoRepository;
	
	@Autowired
	private ServicoService servicoService;

	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public RetornoVO recuperarPedido(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "sort", required = false, defaultValue = "id asc") String[] sorts,
			@RequestParam(value = "nome", required = false) String nome,
			@RequestParam(value = "tipoServico", required = true) EnumSexo tipoServico,
			@Or({ @Spec(path = "nome", spec = Equal.class),
				  @Spec(path = "tipoServico", spec = Equal.class) }) Specification<Servico> spec) {

		MultiValueMap<String, String> headers = new HttpHeaders();
		List<Servico> servico = null;

		if (page != null || limit != null) {
			Pageable pageRequest = new PageRequest(page - 1, limit, buildSort(sorts));

			Page<Servico> pageResult = servicoRepository.findAll(spec, pageRequest);
			servico = pageResult.getContent();

			headers.add("X-Total-Count", String.valueOf(pageResult.getTotalElements()));
		} else {
			servico = servicoRepository.findAll(spec, buildSort(sorts));
			headers.add("X-Total-Count", String.valueOf(servico.size()));
		}

		return getRetornoVO(new ResponseEntity<List<Servico>>(servico, headers, HttpStatus.OK));
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public RetornoVO recuperarServico(@PathVariable("id") Long id) {
		Servico servico = servicoService.recuperarServico(id);

		if (servico == null) {
			return getRetornoVO(new ResponseEntity<Servico>(HttpStatus.NOT_FOUND));
		}

		return getRetornoVO(new ResponseEntity<Servico>(servico, HttpStatus.OK));
	}

}