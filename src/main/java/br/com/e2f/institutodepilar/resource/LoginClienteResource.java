package br.com.e2f.institutodepilar.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.e2f.institutodepilar.entity.Cliente;
import br.com.e2f.institutodepilar.repository.ClienteRepository;
import br.com.e2f.institutodepilar.service.ClienteService;
import br.com.e2f.institutodepilar.util.EnumEmailTemplate;
import br.com.e2f.institutodepilar.util.GeradorSenha;
import br.com.e2f.institutodepilar.util.SenhaUtil;
import br.com.e2f.institutodepilar.util.SenhaUtil.Algoritmo;
import br.com.e2f.institutodepilar.vo.EmailVO;
import br.com.e2f.institutodepilar.vo.RetornoVO;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;

@RestController
@RequestMapping("/api/login-cliente")
public class LoginClienteResource extends BasicResource {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private ClienteService clienteService;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public RetornoVO recuperarLogin(@RequestParam(value = "email", required = false) String email,
			@And({ @Spec(path = "email", spec = Equal.class) }) Specification<Cliente> spec) {

		@SuppressWarnings("unchecked")
		Cliente login = (Cliente) clienteRepository.findOne(spec);

		if (login == null) {
			return getRetornoVO(new ResponseEntity<Cliente>(login, HttpStatus.NO_CONTENT));
		}

		return getRetornoVO(new ResponseEntity<Cliente>(login, HttpStatus.OK));

	}

	@RequestMapping(path = "/esqueci-senha/{email}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public RetornoVO esqueciMinhaSenha(@RequestParam(value = "email", required = false) String email,
			@And({ @Spec(path = "email", spec = Equal.class) }) Specification<Cliente> spec) {

		@SuppressWarnings("unchecked")
		Cliente login = (Cliente) clienteRepository.findOne(spec);

		if (login == null) {
			return getRetornoVO(new ResponseEntity<Cliente>(login, HttpStatus.NO_CONTENT));
		} else {
			try {
				String senhaGerada = GeradorSenha.getSenha(6);
				login.setSenha(senhaGerada);
				login = clienteService.atualizarCliente(login);
				
				EmailVO emailVo = new EmailVO();
				emailVo.setAssunto("Esqueci Minha Senha :: Instituto Depilar");
				emailVo.setDestino(login.getEmail());
				emailVo.setSenhaGerada(senhaGerada);
				emailVo.setNome(login.getNome());
				emailVo.setMensagem("Solicitação de recuperação de Senha");
				emailVo.setTemplate(EnumEmailTemplate.ESQUECI_SENHA);

				return getRetornoVO(clienteService.enviarEmail(emailVo));

			} catch (Exception e) {
				return getRetornoVO(new ResponseEntity<Cliente>(login, HttpStatus.INTERNAL_SERVER_ERROR));
			}
		}

	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public RetornoVO recuperarLogin(@PathVariable("id") Long id) {
		Cliente login = clienteService.recuperarCliente(id);

		if (login == null) {
			return getRetornoVO(new ResponseEntity<Cliente>(HttpStatus.NOT_FOUND));
		}
		return getRetornoVO(new ResponseEntity<Cliente>(login, HttpStatus.OK));
	}

	@SuppressWarnings("unused")
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public RetornoVO alterarSenhaLogin(@RequestParam(name = "id") Long id,
			@RequestParam(name = "senhaAntiga") String senhaAntiga,
			@RequestParam(name = "novaSenha") String novaSenha) {

	
		Cliente login = clienteService.recuperarCliente(id);
		String hashPassword = SenhaUtil.gerarHash(novaSenha, login.getEmail(), Algoritmo.SHA256);
		
		if (login != null) {
			if (login.getSenha().equals(SenhaUtil.gerarHash(senhaAntiga,login.getEmail(),Algoritmo.SHA256))) {
				login.setSenha(hashPassword);
				login = clienteService.atualizarCliente(login);
				return getRetornoVO(new ResponseEntity<Cliente>(login, HttpStatus.OK));
			} else {
				return getRetornoVO(new ResponseEntity<Cliente>(HttpStatus.UNAUTHORIZED));
			}
		}

		return getRetornoVO(new ResponseEntity<Cliente>(HttpStatus.NOT_FOUND));
	}

}