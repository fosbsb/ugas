package br.com.e2f.institutodepilar.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.e2f.institutodepilar.entity.Mensagem;
import br.com.e2f.institutodepilar.service.MensagemService;


@RestController
@RequestMapping("/api/mensagem")
public class MensagemResource extends BasicResource {
	
	@Autowired
	private MensagemService mensagemService;
	
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Mensagem incluirMensagem(@RequestBody Mensagem mensagem) {

		return mensagemService.enviarPush(mensagem);
	}
	
	

}